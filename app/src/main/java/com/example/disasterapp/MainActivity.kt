package com.example.disasterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.disasterapp.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DisasterAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var map: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fetchData()
        viewModel.disasterList.observe(this) {
            val listItem = it.map { item ->
                DisasterData(
                    item.properties.imageUrl,
                    item.properties.disasterType,
                    item.properties.tags.instanceRegionCode
                )
            }
            adapter = DisasterAdapter(listItem)
            binding.bottomSheet.recyclerView.adapter = adapter
        }
        BottomSheetBehavior.from(binding.bottomSheet.root).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}