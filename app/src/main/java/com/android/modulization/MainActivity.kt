package com.android.modulization

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.R
import com.android.databinding.ActivityMainBinding
import com.android.modulization.ui.BaseBindingActivity

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun onViewModelInit() {
    }

    override fun ActivityMainBinding.onViewBindingCreated() {

    }


}