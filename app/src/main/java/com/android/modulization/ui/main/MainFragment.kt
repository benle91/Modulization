package com.android.modulization.ui.main

import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.databinding.FragmentMainBinding
import com.android.modulization.data.preference.PrefRepository
import com.android.modulization.data.preference.PreferenceKey
import com.android.modulization.ui.BaseBindingFragment
import com.android.modulization.ui.DrawerEventViewModel
import com.android.modulization.ui.ScreenEventViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseBindingFragment<FragmentMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()
    private val mSharedPreference: PrefRepository by inject()

    private val mScreenEventViewModel by activityViewModels<ScreenEventViewModel>()
    private val mDrawerEventViewModel by activityViewModels<DrawerEventViewModel>()

    override fun FragmentMainBinding.onViewBindingCreated() {
        val accessToken = mSharedPreference.getString(PreferenceKey.ACCESS_TOKEN)
        if (accessToken.isNullOrBlank()) {
            navigate2Auth()
            return
        }
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    mScreenEventViewModel.openProgressDialog()
                    mViewModel.logout()
                }
            }
            true
        }

    }

    override fun onViewModelInit() {
        with(mViewModel) {
            ldLogoutResult.observe(viewLifecycleOwner) { isLogout ->
                mScreenEventViewModel.closeProgressDialog()
                if (isLogout) {
                    mSharedPreference.clear()
                    navigate2Auth()
                }
            }
        }
        with(mDrawerEventViewModel) {
            ldDrawerEvent.observe(viewLifecycleOwner) {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun navigate2Auth() {
        findNavController().navigate(R.id.action_mainFragment_to_auth_navigation)
    }

    override fun onCustomBackPresses(): Boolean {
        return true
    }


}