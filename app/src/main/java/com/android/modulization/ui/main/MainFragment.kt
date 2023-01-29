package com.android.modulization.ui.main

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.databinding.FragmentMainBinding
import com.android.modulization.ui.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseBindingFragment<FragmentMainBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    override fun FragmentMainBinding.onViewBindingCreated() {
        message.setOnClickListener {
            navigate(R.id.action_mainFragment_to_firstFragment)
        }
    }

    override fun onViewModelInit() {
        viewModel.ldListResponse.observe(this) {
            Toast.makeText(requireContext(), it.first().name, Toast.LENGTH_LONG).show()
        }
    }


}