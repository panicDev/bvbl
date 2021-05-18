package com.bonifes.viewbinding.sample.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bonifes.viewbinding.binding
import com.bonifes.viewbinding.sample.R
import com.bonifes.viewbinding.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding: FragmentHomeBinding by binding()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
}