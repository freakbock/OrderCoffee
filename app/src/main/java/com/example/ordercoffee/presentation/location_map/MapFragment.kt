package com.example.ordercoffee.presentation.location_map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentMapBinding
import com.example.ordercoffee.domain.model.Location
import com.google.android.gms.maps.model.CameraPosition
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import org.koin.androidx.viewmodel.ext.android.viewModel



class MapFragment : Fragment(R.layout.fragment_map) {

    private lateinit var binding: FragmentMapBinding
    private val viewModel: MapViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMapBinding.bind(view)

        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()

        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            showLocationsOnMap(locations)
        }

        viewModel.loadLocations()
    }

    private fun showLocationsOnMap(locations: List<Location>) {
        val map = binding.mapView.mapWindow.map
        for (location in locations) {
            val point = Point(location.point.latitude, location.point.longitude)
            val placemark: PlacemarkMapObject = map.mapObjects.addPlacemark(point)
            placemark.setText(location.name)
        }

        // Опционально: центрировать карту на первую точку
        locations.firstOrNull()?.let {
            map.move(
                com.yandex.mapkit.map.CameraPosition(
                    Point(it.point.latitude, it.point.longitude),
                    15.0f,
                    0.0f,
                    0.0f
                )
            )
        }
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }
}
