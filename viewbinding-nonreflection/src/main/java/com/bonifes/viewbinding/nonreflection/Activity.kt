package com.bonifes.viewbinding.nonreflection

import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import kotlin.LazyThreadSafetyMode.NONE

fun <VB : ViewBinding> ComponentActivity.binding(inflate: (LayoutInflater) -> VB, setContentView: Boolean = true) = lazy(NONE) {
    inflate(layoutInflater).also { binding ->
        if (setContentView) setContentView(binding.root)
        if (binding is ViewDataBinding) binding.lifecycleOwner = this
    }
}