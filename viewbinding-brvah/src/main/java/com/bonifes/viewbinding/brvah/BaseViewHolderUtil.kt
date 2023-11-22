@file:Suppress("unused")

package com.bonifes.viewbinding.brvah

import android.view.View
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder

@Suppress("UNCHECKED_CAST")
fun <VB : ViewBinding> BaseViewHolder.getBinding(bind: (View) -> VB): VB {
    return itemView.getTag(R.id.tag_view_binding) as? VB
        ?: bind(itemView).also { itemView.setTag(R.id.tag_view_binding, it) }
}