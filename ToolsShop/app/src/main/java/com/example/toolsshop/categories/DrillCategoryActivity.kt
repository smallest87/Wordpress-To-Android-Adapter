package com.example.toolsshop.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.toolsshop.Tool
import com.example.toolsshop.R
import com.example.toolsshop.databinding.ActivityDrillCategoryBinding
import com.example.toolsshop.detailes.DrillDetailActivity

class DrillCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrillCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrillCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val drillInterskol = Tool(getString(R.string.drill_interskol_title), getString(R.string.drill_interskol_info), R.drawable.interskol)
        val drillMakita = Tool(getString(R.string.drill_makita_title), getString(R.string.drill_makita_info), R.drawable.makita)
        val drillDewalt = Tool(getString(R.string.drill_dewalt_title), getString(R.string.drill_dewalt_info), R.drawable.dewalt)
        var drills = arrayListOf(drillInterskol, drillMakita, drillDewalt)
        val adapter = ArrayAdapter(this, R.layout.listview_item, drills)
        binding.listViewDrills.adapter = adapter

        binding.listViewDrills.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, p2, _ ->
                var drill = drills[p2]
                val intent = Intent(applicationContext, DrillDetailActivity::class.java)
                intent.putExtra("title", drill.getTitle())
                intent.putExtra("info", drill.getInfo())
                intent.putExtra("resId", drill.getImageResourceId())
                startActivity(intent)
            }
    }
}