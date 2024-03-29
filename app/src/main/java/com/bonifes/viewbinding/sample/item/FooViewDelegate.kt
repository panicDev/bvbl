package com.bonifes.viewbinding.sample.item

import android.content.Context
import android.view.ViewGroup
import com.bonifes.viewbinding.BindingViewHolder
import com.bonifes.viewbinding.sample.databinding.ItemFooBinding
import com.drakeet.multitype.ItemViewDelegate

class FooViewDelegate : ItemViewDelegate<Foo, BindingViewHolder<ItemFooBinding>>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemFooBinding>(parent)

  override fun onBindViewHolder(holder: BindingViewHolder<ItemFooBinding>, item: Foo) {
    holder.binding.foo.text = item.value
  }
}

//class FooViewDelegate : ItemViewDelegate<Foo, FooViewDelegate.ViewHolder>() {
//
//  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
//    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_foo, parent, false))
//  }
//
//  override fun onBindViewHolder(holder: ViewHolder, item: Foo) {
//    holder.fooView.text = item.value
//  }
//
//  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val fooView: TextView = itemView.findViewById(R.id.foo)
//  }
//}