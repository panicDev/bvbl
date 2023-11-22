package com.bonifes.viewbinding.sample
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bonifes.viewbinding.inflate
import com.bonifes.viewbinding.sample.databinding.LayoutCustomViewBinding

class CustomView(context: Context, attrs: AttributeSet? = null) :
  ConstraintLayout(context, attrs) {

  val binding = inflate<LayoutCustomViewBinding>()

  init {
    binding.tvTitle.setText(R.string.show_loading_dialog)
  }
}