package com.android.modulization.ui.supporter

import androidx.viewbinding.ViewBinding

interface BaseViewSupporter {

    fun ViewBinding.onViewBindingCreated()
    fun onViewModelInit()

}