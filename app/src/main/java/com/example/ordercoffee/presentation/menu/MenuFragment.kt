package com.example.ordercoffee.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentMenuBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModel()
    private lateinit var adapter: MenuAdapter

    private val args: MenuFragmentArgs by navArgs()
    private val locationId get() = args.locationId

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        adapter = MenuAdapter { updatedList ->

            viewModel.updateSelectedMenuItems(updatedList.filter { it.count > 0 })
        }

        binding.menuRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.menuRecyclerView.adapter = adapter

        viewModel.menuItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        if (locationId != null) {
            viewModel.loadMenu(locationId)
        }

        binding.buttonOrder.setOnClickListener {

            val selectedItems = viewModel.menuItems.value?.filter { it.count > 0 } ?: emptyList()

            val bundle = Bundle().apply {
                putParcelableArrayList("selectedItems", ArrayList(selectedItems))
            }

            findNavController().navigate(R.id.action_menuFragment_to_orderFragment, bundle)
        }
    }
}

