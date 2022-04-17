package com.example.toolsshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.toolsshop.categories.AngleGrinderCategoryActivity
import com.example.toolsshop.categories.DrillCategoryActivity
import com.example.toolsshop.categories.PerforatorCategoryActivity
import com.example.toolsshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val toolsArray: Array<String> = resources.getStringArray(R.array.tools)
        val adapter = ArrayAdapter(this, R.layout.listview_item, toolsArray)
        binding.listViewTools.adapter = adapter

        binding.listViewTools.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, p2, _ ->
                when (p2) {
                    0 -> {
                        val intent1 = Intent(applicationContext, DrillCategoryActivity::class.java)
                        startActivity(intent1)
                    }
                    1 -> {
                        val intent2 =
                            Intent(applicationContext, PerforatorCategoryActivity::class.java)
                        startActivity(intent2)
                    }
                    2 -> {
                        val intent3 =
                            Intent(applicationContext, AngleGrinderCategoryActivity::class.java)
                        startActivity(intent3)
                    }
                }
            }
    }
}