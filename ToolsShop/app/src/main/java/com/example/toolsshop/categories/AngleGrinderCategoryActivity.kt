package com.example.toolsshop.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.toolsshop.R
import com.example.toolsshop.Tool
import com.example.toolsshop.databinding.ActivityAngleGrinderCategoryBinding
import com.example.toolsshop.detailes.AngleGrinderDetailActivity

class AngleGrinderCategoryActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAngleGrinderCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAngleGrinderCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val angleGrinderHikoki = Tool(getString(R.string.angle_grinder_hikoki_title), getString(R.string.angle_grinder_hikoki_info), R.drawable.hikoki)
        val angleGrinderMakita = Tool(getString(R.string.angle_grinder_makita_title), getString(R.string.perforator_makita_info), R.drawable.makita_angle_grinder)
        val angleGrinderMetabo = Tool(getString(R.string.angle_grinder_metabo_title), getString(R.string.angle_grinder_metabo_info), R.drawable.metabo)
        var angleGrinders = arrayListOf(angleGrinderHikoki, angleGrinderMakita, angleGrinderMetabo)
        val adapter = ArrayAdapter(this, R.layout.listview_item, angleGrinders)
        binding.listViewAngleGrinders.adapter = adapter

        binding.listViewAngleGrinders.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, p2, _ ->
                var drill = angleGrinders[p2]
                val intent = Intent(applicationContext, AngleGrinderDetailActivity::class.java)
                intent.putExtra("title", drill.getTitle())
                intent.putExtra("info", drill.getInfo())
                intent.putExtra("resId", drill.getImageResourceId())
                startActivity(intent)
            }
    }
}