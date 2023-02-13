package com.android.modulization.ui

import androidx.lifecycle.ViewModel
import com.android.modulization.utils.SingleLiveEvent

/**
 *Created by Hien on 2/12/2023.
 */
class ScreenEventViewModel : ViewModel() {

    val ldScreenEvent = SingleLiveEvent<ScreenEventState>()

    fun openProgressDialog() {
        ldScreenEvent.value = ScreenEventState.OPEN_PROGRESS_DIALOG
    }

    fun closeProgressDialog() {
        ldScreenEvent.value = ScreenEventState.CLOSE_PROGRESS_DIALOG
    }

    fun navigateToScreen(state: ScreenEventState) {
        ldScreenEvent.value = state
    }

}

enum class ScreenEventState {
    OPEN_PROGRESS_DIALOG,
    CLOSE_PROGRESS_DIALOG,
    NAVIGATE_TO_ROOT
}