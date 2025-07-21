package com.example.ordercoffee.presentation.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.FragmentOrderBinding
import com.example.ordercoffee.domain.model.MenuItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModel()  // Предположим, что он один для Menu и Order

    private val adapter = OrderAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentOrderBinding.bind(view)

        binding.orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRecyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        val selectedItems = arguments?.getParcelableArrayList<MenuItem>("selectedItems") ?: emptyList()
        viewModel.setItems(selectedItems)


    }
}
