package com.example.fragmenttest2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.fragmenttest2.databinding.FragmentSecondaryThemesBinding

class SecondaryThemesFragment: Fragment() {

    private lateinit var binding: FragmentSecondaryThemesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondaryThemesBinding.inflate(inflater, container, false)

        val themesArray = resources.getStringArray(R.array.themes)
        val adapter = ArrayAdapter(requireContext(), R.layout.listview_item, themesArray)
        binding.listView.adapter = adapter

        return binding.root
    }
}