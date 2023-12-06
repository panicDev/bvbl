package com.bonifes.viewbinding.sample.item

import com.bonifes.viewbinding.brvah.getBinding
import com.bonifes.viewbinding.sample.R
import com.bonifes.viewbinding.sample.base.nonreflection.BaseBindingQuickAdapter
import com.bonifes.viewbinding.sample.databinding.ItemFooBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


//class FooAdapter(var list: List<Foo>) : RecyclerView.Adapter<BindingViewHolder<ItemFooBinding>>() {
//
//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//    BindingViewHolder(ItemFooBinding::inflate, parent)
//
//  override fun onBindViewHolder(holder: BindingViewHolder<ItemFooBinding>, position: Int) {
//    holder.binding.apply {
//      tvFoo.text = list[position].value
//    }
//  }
//
//  override fun getItemCount() = list.size
//}
//

//class FooAdapter : BaseQuickAdapter<Foo, BaseViewHolder>(R.layout.item_foo) {
//
//  override fun convert(holder: BaseViewHolder, item: Foo) {
//    holder.getBinding(ItemFooBinding::bind).apply {
//      foo.text = item.value
//    }
//  }
//}

class FooAdapter : BaseBindingQuickAdapter<Foo, ItemFooBinding>(ItemFooBinding::inflate) {

  override fun convert(holder: BaseBindingHolder, item: Foo) {
    holder.getViewBinding<ItemFooBinding>().apply {
      foo.text = item.value
    }
  }
}