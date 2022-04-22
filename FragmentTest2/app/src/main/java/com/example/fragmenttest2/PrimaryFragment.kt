package com.example.fragmenttest2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmenttest2.databinding.FragmentPrimaryBinding

class PrimaryFragment: Fragment() {

    private lateinit var binding: FragmentPrimaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrimaryBinding.inflate(inflater, container, false)
        binding.aboutButton.setOnClickListener { onClickAbout() }
        return binding.root
    }

    fun onClickAbout(){
        val fragment = PrimaryFragment()
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerSecondary, fragment)
            .commit()
    }
}