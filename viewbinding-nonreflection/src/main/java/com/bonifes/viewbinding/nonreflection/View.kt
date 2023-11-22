package com.bonifes.viewbinding.nonreflection

import android.view.View
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
fun <VB : ViewBinding> View.getBinding(bind: (View) -> VB): VB =
    getTag(R.id.tag_view_binding) as? VB ?: bind(this).also { setTag(R.id.tag_view_binding, it) }