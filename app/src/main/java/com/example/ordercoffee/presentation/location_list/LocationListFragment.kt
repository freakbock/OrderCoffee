package com.example.ordercoffee.presentation.location_list

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentLocationListBinding
import com.example.ordercoffee.presentation.location_map.MapFragmentDirections


class LocationListFragment : Fragment(R.layout.fragment_location_list) {

    private lateinit var binding: FragmentLocationListBinding
    private val viewModel: LocationListViewModel by viewModel()
    private lateinit var adapter: LocationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLocationListBinding.bind(view)
        adapter = LocationAdapter { location ->
            val action = LocationListFragmentDirections.actionLocationListFragmentToMenuFragment(location.id.toString())
            findNavController().navigate(action)
        }

        binding.locationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.locationsRecyclerView.adapter = adapter

        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            adapter.submitList(locations)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(requireContext(), "Ошибка: $it", Toast.LENGTH_SHORT).show()
            }
        }

        binding.openMap.setOnClickListener {
            findNavController().navigate(R.id.action_locationListFragment_to_mapFragment)
        }

    }
}
