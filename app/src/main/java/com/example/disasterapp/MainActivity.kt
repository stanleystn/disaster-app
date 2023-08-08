package com.example.disasterapp

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.disasterapp.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DisasterAdapter
    private lateinit var viewModel: MainViewModel

    //map declaration
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        //map show
        map = findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        //map controller
        val mapController = map.controller
        mapController.setZoom(6)
        val startPoint = GeoPoint(0.7893, 113.9213)
        mapController.setCenter(startPoint)

        //view-model
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

    //permission map
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }
}