package com.example.criminalintent.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.Crime
import com.example.criminalintent.viewmodels.CrimeListViewModel
import com.example.criminalintent.R
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import java.util.*

private const val TAG = "CrimeListFragment"



class CrimeListFragment: Fragment() {

    //-----------Interface for Callbacks to Host Activity-----------
    interface Callbacks{
        fun onCrimeSelected(crimeId: UUID)}

    private lateinit var binding: FragmentCrimeListBinding
    private var callbacks: Callbacks? = null
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       //    return inflater.inflate(R.layout.list_item_empty, container, false)  <--- for empty CrimeList

        binding = FragmentCrimeListBinding.inflate(inflater, container, false)
            binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.crimeRecyclerView.adapter = adapter
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimesLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got Crimes ${crimes.size}")
                    updateUI(crimes)
                }
            }
        )
    }

    companion object{
        fun newInstance(): CrimeListFragment{
            return CrimeListFragment()
        }
    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var crime: Crime

        val titleTextView: TextView = itemView.findViewById(R.id.crimeTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.crimeDate)
        val solvedImageView: ImageView = itemView.findViewById(R.id.isSolvedImage)

        init {
            itemView.setOnClickListener(this)
        }
        
        fun bind(crime:Crime){
            this.crime = crime
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
            solvedImageView.visibility = if (crime.isSolved){
                View.VISIBLE
            }else{
                View.INVISIBLE
            }
        }

        override fun onClick(p0: View?) {
            callbacks?.onCrimeSelected(crime.id)
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {

//            =============Code for function "Requires Police"================
//            return if(viewType == 0){
//                val view = layoutInflater.inflate(
//                    R.layout.list_item_crime_police,
//                    parent,
//                    false)
//                CrimeHolder(view)
//            }else{
//                val view = layoutInflater.inflate(
//                    R.layout.list_item_crime,
//                    parent,
//                    false)
//                CrimeHolder(view)
//            }
            val view = layoutInflater.inflate(
                R.layout.list_item_crime,
                parent,
                false)
            return CrimeHolder(view)

        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount() = crimes.size

//            =============Code for function "Requires Police"================
        /*override fun getItemViewType(position: Int): Int {
            return if(crimes[position].requiresPolice && !crimes[position].isSolved ){
                0
            }else if(crimes[position].requiresPolice && crimes[position].isSolved){
                1
            }
            else{
                2
            }
        }*/
    }
    private fun updateUI(crimes: List<Crime>){
        adapter = CrimeAdapter(crimes)
        binding.crimeRecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.new_crime ->{
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}