package com.example.fragmenttest2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmenttest2.databinding.FragmentSecondaryAboutBinding

class SecondaryAboutFragment: Fragment() {

    private lateinit var binding: FragmentSecondaryAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondaryAboutBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener { goBack() }
        return binding.root
    }

    fun goBack(){
        requireActivity().onBackPressed()
    }
}