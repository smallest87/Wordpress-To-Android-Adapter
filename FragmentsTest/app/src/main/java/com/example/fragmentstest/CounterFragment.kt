package com.example.fragmentstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentstest.databinding.FragmentCounterBinding

class CounterFragment: Fragment() {

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(inflater, container, false)
        binding.counterTextView.text = getString(R.string.screen_label, getCounterValue())
        binding.quoteTextView.text = getQuote()

        binding.launchNextButton.setOnClickListener { launchNext() }
        binding.goBackButton.setOnClickListener { goBack() }
        return binding.root
    }

    private fun launchNext(){
        val fragment = CounterFragment.newInstance(
            counterValue = (requireActivity() as MainActivity).getScreensCount() + 1,
            quote = (requireActivity() as MainActivity).createQuote()
        )
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun goBack(){
        requireActivity().onBackPressed()
    }

    private fun getCounterValue(): Int = requireArguments().getInt(ARG_COUNTER_VALUE)

    private fun getQuote(): String = requireArguments().getString(ARG_QUOTE).toString()

    companion object{

        @JvmStatic
        private val ARG_COUNTER_VALUE = "ARG_COUNTER_VALUE"

        @JvmStatic
        private val ARG_QUOTE = "ARG_QUOTE"

        @JvmStatic
        fun newInstance(counterValue: Int, quote: String): CounterFragment{
            val args = Bundle().apply {
                putInt(ARG_COUNTER_VALUE, counterValue)
                putString(ARG_QUOTE, quote)
            }
            val fragment = CounterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}