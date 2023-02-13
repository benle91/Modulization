package com.android.modulization.ui.home

import androidx.fragment.app.activityViewModels
import com.android.databinding.FragmentHomeBinding
import com.android.modulization.ui.BaseBindingFragment
import com.android.modulization.ui.DrawerEventViewModel
import com.android.modulization.ui.ScreenEventViewModel

/**
 *Created by Hien on 2/12/2023.
 */
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

    private val mDrawerEventViewModel by activityViewModels<DrawerEventViewModel>()

    override fun FragmentHomeBinding.onViewBindingCreated() {
        ibDrawer.setOnClickListener {
            mDrawerEventViewModel.openDrawer()
        }
    }

    override fun onViewModelInit() {
    }
}