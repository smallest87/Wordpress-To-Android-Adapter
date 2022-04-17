package com.example.toolsshop.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.toolsshop.R
import com.example.toolsshop.Tool
import com.example.toolsshop.databinding.ActivityPerforatorCategoryBinding
import com.example.toolsshop.detailes.PerforatorDetailActivity

class PerforatorCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerforatorCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerforatorCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val perforatorBolt = Tool(getString(R.string.perforator_bolt_title), getString(R.string.perforator_bolt_info), R.drawable.bolt)
        val perforatorMakita = Tool(getString(R.string.perforator_makita_title), getString(R.string.perforator_makita_info), R.drawable.makita_perforator)
        val perforatorBosch = Tool(getString(R.string.perforator_bosch_title), getString(R.string.perforator_bosch_info), R.drawable.bosch)
        var perforators = arrayListOf(perforatorBolt, perforatorMakita, perforatorBosch)
        val adapter = ArrayAdapter(this, R.layout.listview_item, perforators)
        binding.listViewPerforators.adapter = adapter

        binding.listViewPerforators.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, p2, _ ->
                var drill = perforators[p2]
                val intent = Intent(applicationContext, PerforatorDetailActivity::class.java)
                intent.putExtra("title", drill.getTitle())
                intent.putExtra("info", drill.getInfo())
                intent.putExtra("resId", drill.getImageResourceId())
                startActivity(intent)
            }
    }
}