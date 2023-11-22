package com.bonifes.viewbinding.sample.base.reflection

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bonifes.viewbinding.base.ViewBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * How to modify the base adapter class to use view binding, you need the following steps:
 * 1. Adds a generic of view binding to the base class.
 * 2. Uses a class of view holder with binding instead of the class of original view holder.
 * 3. Overrides the method of creating view holder and returns the view holder by the [inflateBindingWithGeneric] method.
 * 4. It is recommended to override the method for binding view holder so that it is simpler to use.
 *
 * Here is the core code. If you use `BaseRecyclerViewAdapterHelper`, you can copy this file directly to use.
 *
 * @author Dylan Cai
 */
abstract class BaseBindingQuickAdapter<T, VB : ViewBinding>(layoutResId: Int = -1) :
    BaseQuickAdapter<T, BaseBindingQuickAdapter.BaseBindingHolder>(layoutResId) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        BaseBindingHolder(ViewBindingUtil.inflateWithGeneric<VB>(this, parent))

    class BaseBindingHolder(private val binding: ViewBinding) : BaseViewHolder(binding.root) {
        constructor(itemView: View) : this(ViewBinding { itemView })

        @Suppress("UNCHECKED_CAST")
        fun <VB : ViewBinding> getViewBinding() = binding as VB
    }
}