package com.bonifes.viewbinding.sample.base.nonreflection

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.drakeet.multitype.ItemViewDelegate

abstract class BindingViewDelegate<T, VB : ViewBinding> : ItemViewDelegate<T, BindingViewDelegate.BindingViewHolder<VB>>() {
    override fun onCreateViewHolder(context: Context, parent: ViewGroup): BindingViewHolder<VB> {
        return BindingViewHolder(onCreateViewBinding(LayoutInflater.from(parent.context), parent))
    }

    protected abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}