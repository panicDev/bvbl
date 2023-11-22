package com.bonifes.viewbinding.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import com.bonifes.viewbinding.binding
import com.bonifes.viewbinding.sample.databinding.FragmentCustomViewBinding

class MeFragment : Fragment(R.layout.fragment_custom_view) {

    private val binding: FragmentCustomViewBinding by binding()
    private val loadingDialog by lazy { LoadingDialogFragment() }
    private val handler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customView.setOnClickListener {
            loadingDialog.show(childFragmentManager, "loading")
            handler.postDelayed(2000) { loadingDialog.dismiss() }
        }
    }
}