package com.bonifes.viewbinding.sample

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bonifes.viewbinding.binding
import com.bonifes.viewbinding.sample.databinding.LayoutCustomViewBinding

/**
 * @author Dylan Cai
 */
class CustomView(context: Context, attrs: AttributeSet? = null) :
  ConstraintLayout(context, attrs) {

  val binding: LayoutCustomViewBinding = binding()

  init {
    binding.tvTitle.text = "test"
  }

}