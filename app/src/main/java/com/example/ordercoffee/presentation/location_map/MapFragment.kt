package com.example.ordercoffee.presentation.location_map

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentMapBinding
import com.example.ordercoffee.domain.model.Location
import com.google.android.gms.maps.model.CameraPosition
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
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
            placemark.userData = location.id.toString()
            placemark.setText(location.name)
            placemark.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.map_icon))
            placemark.addTapListener(object: MapObjectTapListener{
                override fun onMapObjectTap(p0: MapObject, p1: Point): Boolean {
                    Log.d("TAP", "TAP")
                    val locationId = p0.userData as? String ?: return false
                    Log.d("MapFragment", "Tapped location with id: $locationId")
                    openMenuFragment(locationId)
                    return true
                }
            })
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

    private fun openMenuFragment(locationId: String) {
        val action = MapFragmentDirections.actionMapFragmentToMenuFragment(locationId)
        findNavController().navigate(action)
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
