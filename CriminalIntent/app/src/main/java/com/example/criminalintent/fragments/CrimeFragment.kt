@file:Suppress("DEPRECATION")

package com.example.criminalintent.fragments


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criminalintent.Crime
import com.example.criminalintent.R
import com.example.criminalintent.viewmodels.CrimeDetailViewModel
import com.example.criminalintent.databinding.FragmentCrimeBinding
import com.example.criminalintent.getScaledBitmap
import java.io.File
import java.util.*

private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val DATE_FORMAT = "EEE, MMM, dd"
private const val REQUEST_CONTACT = 1
private const val REQUEST_PHONE_NUMBER = 2
private const val REQUEST_PHOTO = 3

class CrimeFragment : Fragment(), DatePickerFragment.CallBacks {

    private lateinit var crime: Crime
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

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
                photoFile = crimeDetailViewModel.getPhotoFile(crime)
                photoUri = FileProvider.getUriForFile(requireActivity(),
                "com.example.criminalintent.fileprovider",
                photoFile)
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
                setTargetFragment(
                    this@CrimeFragment,
                    REQUEST_DATE
                ) // <--- Deprecated, need to find replacement
                show(this@CrimeFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
        binding.crimeReportButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    getString(R.string.crime_report_subject)
                )
            }.also {
                startActivity(
                    Intent.createChooser(
                        it,
                        getString(R.string.send_report)
                    )
                )
            }
        }

        binding.crimeSuspectButton.apply {
            val pickContactIntent =
                Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

            setOnClickListener {
                startActivityForResult(pickContactIntent, REQUEST_CONTACT)

                //  --------Checking for address book on phone -----
                val packageManager: PackageManager = requireActivity().packageManager
                val resolvedActivity: ResolveInfo? =
                    packageManager.resolveActivity(
                        pickContactIntent,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )
                if (resolvedActivity == null) {
                    isEnabled = false
                }
                //  ------------------------------------------------
            }
        }

        binding.buttonCamera.apply {
            val packageManager = requireActivity().packageManager

            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage,
                PackageManager.MATCH_DEFAULT_ONLY)
            if (resolvedActivity == null){
                isEnabled = false
            }

            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)

                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)

                for (cameraActivity in cameraActivities){
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }

                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }
        //--------------------FOR CALLS----------------------------------------(start)--------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------------------------

        binding.callSuspectButton.setOnClickListener {
            if (checkAndRequestPermissions()) {
                val phoneContactContract =
                    Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))
                startActivity(phoneContactContract)
            }
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val call =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
        val listPermissionsNeeded = ArrayList<String>()
        if (call != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                listPermissionsNeeded.toTypedArray(),
                REQUEST_PHONE_NUMBER
            )
            return false
        }
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,          // <----- Deprecated! Need to find replacement
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d("in fragment on request", "Permission callback called-------")
        when (requestCode) {
            REQUEST_PHONE_NUMBER -> {

                val perms = HashMap<String, Int>()
                // Initialize the map with both permissions
                perms[Manifest.permission.CALL_PHONE] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.isNotEmpty()) {
                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]
                    // Check for both permissions
                    if (perms[Manifest.permission.CALL_PHONE] == PackageManager.PERMISSION_GRANTED
                    ) {
                        print("Storage permissions are required")
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        /*Log.d("in fragment on request", "Some permissions are not granted ask again ")
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
                        //                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            ||
                            ActivityCompat.shouldShowRequestPermissionRationale(requireContext(), Manifest.permission.CAMERA)
                            ||
                            ActivityCompat.shouldShowRequestPermissionRationale(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            ||
                            ActivityCompat.shouldShowRequestPermissionRationale(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {

                        } else {
                            Toast.makeText(requireContext(), "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                .show()
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }//permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false*/
                    }
                }
            }
        }

    }

    //--------------------FOR CALLS----------------------------------------(end)--------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------


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
        if (crime.suspect.isNotEmpty()) {
            binding.crimeSuspectButton.text = crime.suspect
        }
        if (crime.suspect.isBlank()) {
            binding.crimeSuspectButton.visibility = View.VISIBLE
        }
        updatePhotoView()
    }

    private fun getCrimeReport(): String {
        val solvedString = if (crime.isSolved) {
            getString(R.string.crime_report_solved)
        } else {
            getString(R.string.crime_report_unsolved)
        }

        val dateString = android.text.format.DateFormat.format(DATE_FORMAT, crime.date)
        val suspect = if (crime.suspect.isBlank()) {
            getString(R.string.crime_report_no_suspect)
        } else {
            getString(R.string.crime_report_suspect, crime.suspect)
        }
        return getString(
            R.string.crime_report,
            crime.title, dateString, solvedString, suspect
        )
    }

    //----------------------For downloading bitmap to ImageView------------(start)
    private fun updatePhotoView(){
        if(photoFile.exists()){
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            binding.imageViewPhoto.setImageBitmap(bitmap)
            binding.imageViewPhoto.setBackgroundColor(Color.rgb(255,255,255))
        }else{
            binding.imageViewPhoto.setImageBitmap(null)
        }
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
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
                    if (it.count == 0) {
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
            requestCode == REQUEST_PHONE_NUMBER && data != null -> {

            }
            requestCode == REQUEST_PHOTO -> {
                requireActivity().revokeUriPermission(photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                updatePhotoView()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().revokeUriPermission(photoUri,
        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    }

}