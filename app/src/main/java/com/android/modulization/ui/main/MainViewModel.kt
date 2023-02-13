package com.android.modulization.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.modulization.domain.onSuccess
import com.android.modulization.domain.onThrowable
import com.android.modulization.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.launchIn

class MainViewModel constructor(private val logoutUseCase: LogoutUseCase) : ViewModel() {

    val ldLogoutResult = MutableLiveData<Boolean>()

    fun logout() {
        logoutUseCase("").onSuccess {
            ldLogoutResult.value = true
        }.onThrowable {
            ldLogoutResult.value = false
        }.launchIn(viewModelScope)
    }

}