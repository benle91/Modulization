package com.android.modulization.ui

import androidx.lifecycle.ViewModel
import com.android.modulization.utils.SingleLiveEvent

/**
 *Created by Hien on 2/12/2023.
 */
class DrawerEventViewModel: ViewModel() {

    val ldDrawerEvent = SingleLiveEvent<Unit>()

    fun openDrawer() {
        ldDrawerEvent.call()
    }

}