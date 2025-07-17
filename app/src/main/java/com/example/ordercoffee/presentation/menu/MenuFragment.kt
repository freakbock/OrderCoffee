package com.example.ordercoffee.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentMenuBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModel()
    private lateinit var adapter: MenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        adapter = MenuAdapter()
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        viewModel.menuItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
        val locationId = arguments?.getString("locationId")
        if (locationId != null) {
            viewModel.loadMenu(locationId)
        }

    }
}
