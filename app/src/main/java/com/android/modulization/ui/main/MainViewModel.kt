package com.android.modulization.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.modulization.data.remote.response.ItemCallResponse
import com.android.modulization.domain.onSuccess
import com.android.modulization.domain.usecases.GetItemsToCallUseCase
import kotlinx.coroutines.flow.launchIn

class MainViewModel constructor(private val useCase: GetItemsToCallUseCase) : ViewModel() {

    val ldListResponse = MutableLiveData<List<ItemCallResponse>>()

    fun getItemsToCall() {
        useCase("").onSuccess {
            ldListResponse.postValue(it)
        }.launchIn(viewModelScope)
    }

}