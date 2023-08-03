package com.example.disasterapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.disasterapp.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DisasterAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fetchData()
        viewModel.disasterList.observe(this) {
            Log.d("Data", it.toString())
            val listItem = it.map { item ->
                DisasterData(
                    R.drawable.disaster_icon,
                    item.properties.disasterType,
                    item.properties.tags.instanceRegionCode
                )
            }
            adapter = DisasterAdapter(listItem)
            binding.bottomSheet.recyclerView.adapter = adapter
        }
        BottomSheetBehavior.from(binding.bottomSheet.root).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        }
    }