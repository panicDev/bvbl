package com.bonifes.viewbinding.sample

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bonifes.viewbinding.sample.base.nonreflection.kotlin.BaseBindingActivity
import com.bonifes.viewbinding.sample.databinding.ActivityMainBinding
import com.bonifes.viewbinding.sample.item.Foo
import com.bonifes.viewbinding.sample.item.FooAdapter

class MainActivity : BaseBindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

//  private val binding by binding(ActivityMainBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.apply {
//      val adapter = MultiTypeAdapter(listOf(Foo("1"), Foo("2"), Foo("3")))
//      adapter.register(FooViewDelegate())
      recyclerView.adapter = FooAdapter().apply {
        data.addAll(listOf(Foo("1"), Foo("2"), Foo("3")))
      }
      recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }
  }
}