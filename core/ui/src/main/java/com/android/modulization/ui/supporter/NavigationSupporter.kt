package com.android.modulization.ui.supporter

import androidx.annotation.IdRes
import androidx.navigation.NavController

interface NavigationSupporter {

    val navController: NavController

    fun navigate(@IdRes navId: Int) {
        navController.navigate(navId)
    }

}