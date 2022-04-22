package com.example.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.criminalintent.databinding.FragmentCrimeBinding

class CrimeFragment: Fragment() {

    private lateinit var crime: Crime
    private lateinit var binding: FragmentCrimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCrimeBinding.inflate(inflater, container, false)
        binding.buttonCrimeDate.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(sequence: CharSequence?,
                                           start: Int,
                                           count: Int,
                                           after: Int)
            {

            }

            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int,
                                       count: Int,
                                       after: Int)
            {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?)
            {

            }
        }
        binding.editTextCrimeTitle.addTextChangedListener(titleWatcher)
        binding.checkBoxCrimeSolved.apply {
            setOnCheckedChangeListener {
                    _, isChecked ->
                crime.isSolved = isChecked
            }
        }
    }
}