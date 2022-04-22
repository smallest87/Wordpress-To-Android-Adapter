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
        binding.ThemesButton.setOnClickListener { onThemesToLearnClick() }

        return binding.root
    }

    private fun onClickAbout(){
        val fragment = SecondaryAboutFragment()
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragmentContainerSecondary, fragment)
            .commit()
    }

    private fun onThemesToLearnClick(){
        val fragment = SecondaryThemesFragment()
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragmentContainerSecondary, fragment)
            .commit()
    }
}