package com.example.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.FragmentCrimeListBinding

private const val TAG = "CrimeListFragment"
class CrimeListFragment: Fragment() {

    private lateinit var binding: FragmentCrimeListBinding
    private var adapter: CrimeAdapter? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total Crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimeListBinding.inflate(inflater, container, false)
        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return binding.root
    }

    companion object{
        fun newInstance(): CrimeListFragment{
            return CrimeListFragment()
        }
    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView = itemView.findViewById(R.id.crimeTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.crimeDate)
    }

    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(
                R.layout.list_item_crime,
                parent,
                false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.apply {
                titleTextView.text = crime.title
                dateTextView.text = crime.date.toString()
            }
        }

        override fun getItemCount() = crimes.size
    }

    private fun updateUI(){
        val crimes =crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes)
        binding.crimeRecyclerView.adapter = adapter
    }
}