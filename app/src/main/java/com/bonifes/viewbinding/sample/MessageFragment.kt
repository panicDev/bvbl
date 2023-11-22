package com.bonifes.viewbinding.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bonifes.viewbinding.base.simpleStringListAdapter
import com.bonifes.viewbinding.nonreflection.binding
import com.bonifes.viewbinding.sample.databinding.FragmentMessageBinding
import com.bonifes.viewbinding.sample.databinding.ItemFooBinding

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding by binding(FragmentMessageBinding::bind)

    private val list = listOf("item 1", "item 2", "item 3")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.submitList(list)
        adapter.doOnItemClick { item, _ ->
            Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
        }
        adapter.doOnItemLongClick { item, _ ->
            Toast.makeText(requireContext(), "long click $item", Toast.LENGTH_SHORT).show()
        }
    }

    private val adapter by simpleStringListAdapter<ItemFooBinding> {
        foo.text = it
    }
}