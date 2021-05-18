/*
 * Copyright (c) 2021. panicDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.bonifes.viewbinding

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


/**
 * @author Dylan Cai
 */

inline fun <reified VB : ViewBinding> Activity.binding() = lazy {
  inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> Fragment.binding() =
  FragmentBindingDelegate(this, VB::class)

fun <T : ViewBinding> Fragment.binding(clazz: KClass<T>) =
  FragmentBindingDelegate(this, clazz)

inline fun <reified VB : ViewBinding> Dialog.binding() = lazy {
  inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> ViewGroup.binding(attachToParent: Boolean = true): VB =
  if (attachToParent)
    inflateBinding(LayoutInflater.from(context), this, attachToParent)
  else
    inflateBinding(LayoutInflater.from(context))

inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
  VB::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB

inline fun <reified VB : ViewBinding> inflateBinding(parent: ViewGroup) =
  inflateBinding<VB>(LayoutInflater.from(parent.context), parent, false)

inline fun <reified VB : ViewBinding> inflateBinding(
  layoutInflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean
) =
  VB::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    .invoke(null, layoutInflater, parent, attachToParent) as VB

class FragmentBindingDelegate<VB : ViewBinding>(
  private val fragment: Fragment,
  private val clazz: KClass<VB>
) : ReadOnlyProperty<Fragment, VB> {

  private var binding: VB? = null

  init {
    fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
      val viewLifecycleOwnerLiveDataObserver =
        Observer<LifecycleOwner?> {
          val viewLifecycleOwner = it ?: return@Observer

          viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
              binding = null
            }
          })
        }

      override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
      }

      override fun onDestroy(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
      }
    })
  }

  override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
    val binding = binding
    if (binding != null) {
      return binding
    }

    val lifecycle = thisRef.viewLifecycleOwner.lifecycle
    if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
      throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
    }


    @Suppress("UNCHECKED_CAST")
    return clazz.java.getMethod("bind", View::class.java)
      .invoke(null, thisRef.requireView()) as VB
  }
}