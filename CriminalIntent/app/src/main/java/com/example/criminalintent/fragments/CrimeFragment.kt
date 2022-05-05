@file:Suppress("DEPRECATION")

package com.example.criminalintent.fragments


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criminalintent.Crime
import com.example.criminalintent.R
import com.example.criminalintent.viewmodels.CrimeDetailViewModel
import com.example.criminalintent.databinding.FragmentCrimeBinding
import java.util.*

private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val DATE_FORMAT = "EEE, MMM, dd"
private const val REQUEST_CONTACT = 1
private const val REQUEST_PHONE_NUMBER = 2

class CrimeFragment : Fragment(), DatePickerFragment.CallBacks {

    private lateinit var crime: Crime
    private lateinit var binding: FragmentCrimeBinding
    private val crimeDetailViewModel: CrimeDetailViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()

//  -----------------------------------------------
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
//  --------Get Id of Crime from Bundle----------

        crimeDetailViewModel.loadCrime(crimeId)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner
//------------Observer need to be here, but Android Studio has other thoughts...
        ) { crime ->
            crime?.let {
                this.crime = crime
                updateUI()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrimeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {

            }
        }
        binding.editTextCrimeTitle.addTextChangedListener(titleWatcher)
        binding.checkBoxCrimeSolved.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }
        binding.buttonCrimeDate.setOnClickListener {
                DatePickerFragment.newInstance(crime.date).apply {
                    setTargetFragment(this@CrimeFragment, REQUEST_DATE) // <--- Deprecated, need to find replacement
                    show(this@CrimeFragment.requireFragmentManager(), DIALOG_DATE)
                }
            }
        binding.crimeReportButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    getString(R.string.crime_report_subject))
            }.also {
                startActivity(Intent.createChooser(
                    it,
                    getString(R.string.send_report)
                ))
            }
        }

        binding.crimeSuspectButton.apply{
            val pickContactIntent =
            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

            setOnClickListener {
                startActivityForResult(pickContactIntent, REQUEST_CONTACT)

                //  --------Checking for address book on phone -----
                val packageManager: PackageManager = requireActivity().packageManager
                val resolvedActivity: ResolveInfo? =
                    packageManager.resolveActivity(pickContactIntent,
                        PackageManager.MATCH_DEFAULT_ONLY)
                if(resolvedActivity == null){
                    isEnabled = false
                }
                //  ------------------------------------------------
            }
        }
        binding.callSuspectButton.setOnClickListener {
            val phoneContactContract = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))
            startActivity(phoneContactContract)
        }
    }


    companion object {
        fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
    }

    private fun updateUI() {
        binding.editTextCrimeTitle.setText(crime.title)
        binding.buttonCrimeDate.text = crime.date.toString()
        binding.checkBoxCrimeSolved.apply {
            isChecked = crime.isSolved
           // jumpDrawablesToCurrentState() <--- Deprecated in API Level 27.1.0
           // Drawable.jumpToCurrentState() <--- Does not work
            jumpDrawablesToCurrentState()
        }
        if(crime.suspect.isNotEmpty()){
            binding.crimeSuspectButton.text = crime.suspect
        }
        if(crime.suspect.isBlank()){
            binding.crimeSuspectButton.visibility = View.VISIBLE
        }
    }

    private fun getCrimeReport(): String{
        val solvedString = if (crime.isSolved){
            getString(R.string.crime_report_solved)
        }else{
            getString(R.string.crime_report_unsolved)
        }

        val dateString = android.text.format.DateFormat.format(DATE_FORMAT, crime.date)
        val suspect = if (crime.suspect.isBlank()){
            getString(R.string.crime_report_no_suspect)
        }else{
            getString(R.string.crime_report_suspect, crime.suspect)
        }
        return getString(R.string.crime_report,
        crime.title, dateString, solvedString, suspect)
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when{
            resultCode != Activity.RESULT_OK -> return

            requestCode == REQUEST_CONTACT && data != null -> {
                val contactUri: Uri? = data.data
                // for what field it have to return
                val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                val cursor = contactUri?.let {
                    requireActivity().contentResolver
                        .query(it, queryFields, null, null, null)
                }
                cursor?.use {
                    //at least one result
                    if(it.count == 0){
                        return
                    }
                //first column of the table is name
                it.moveToFirst()
                    val gotSuspect = it.getString(0)
                    crime.suspect = gotSuspect
                    crimeDetailViewModel.saveCrime(crime)
                    binding.crimeSuspectButton.text = gotSuspect
                }
            }
            requestCode == REQUEST_PHONE_NUMBER && data != null ->{

            }
        }
    }

}