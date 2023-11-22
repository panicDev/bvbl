package com.bonifes.viewbinding.sample.base.reflection

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bonifes.viewbinding.base.ViewBindingUtil
import com.drakeet.multitype.ItemViewDelegate

/**
 * @author Dylan Cai
 */
abstract class BindingViewDelegate<T, VB : ViewBinding> :
    ItemViewDelegate<T, BindingViewDelegate.BindingViewHolder<VB>>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
        BindingViewHolder(ViewBindingUtil.inflateWithGeneric<VB>(this, parent))

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, item: T) {
        onBindViewHolder(holder.binding, item, holder.bindingAdapterPosition)
    }

    abstract fun onBindViewHolder(holder: VB, item: T, position: Int)

    class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}