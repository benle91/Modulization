package com.android.modulization

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import com.android.modulization.ui.BaseBindingActivity
import com.android.modulization.ui.ScreenEventState
import com.android.modulization.ui.ScreenEventViewModel
import com.android.modulization.R
import com.android.modulization.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    private val mScreenEventViewModel by viewModels<ScreenEventViewModel>()

    private val mProgressDialog: ProgressDialog by lazy {
        ProgressDialog(this).apply {
            this.setMessage("Loading")
        }
    }

    override fun onViewModelInit() {
        with(mScreenEventViewModel) {
            ldScreenEvent.observe(this@MainActivity) { state ->
                when (state) {
                    ScreenEventState.OPEN_PROGRESS_DIALOG -> mProgressDialog.show()
                    ScreenEventState.CLOSE_PROGRESS_DIALOG -> mProgressDialog.dismiss()
                    ScreenEventState.NAVIGATE_TO_ROOT -> navigateTo(R.id.mobile_navigation)
                    else -> {}
                }
            }
        }
    }

    override fun ActivityMainBinding.onViewBindingCreated() {

    }

    private fun navigateTo(@IdRes resId: Int) {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(resId)
    }


}