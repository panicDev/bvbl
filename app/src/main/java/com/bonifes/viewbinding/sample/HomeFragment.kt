package com.bonifes.viewbinding.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bonifes.viewbinding.binding
import com.bonifes.viewbinding.sample.R
import com.bonifes.viewbinding.sample.base.reflection.BaseBindingFragment
import com.bonifes.viewbinding.sample.databinding.FragmentHomeBinding

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btnAddMessage.setOnClickListener {
      setFragmentResult("add_message", Bundle())
    }
  }
}