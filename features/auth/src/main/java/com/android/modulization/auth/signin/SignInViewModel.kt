package com.android.modulization.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.modulization.data.local.models.FormFieldState
import com.android.modulization.data.remote.request.AuthorizationAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.domain.onSuccess
import com.android.modulization.domain.onThrowable
import com.android.modulization.domain.usecases.AuthorizationAccountUseCase
import kotlinx.coroutines.flow.launchIn

/**
 *Created by Hien on 2/13/2023.
 */
class SignInViewModel constructor(
    private val authorizationAccountUseCase: AuthorizationAccountUseCase
) : ViewModel() {

    val ldValidEmail = MutableLiveData<FormFieldState>()
    val ldValidPassword = MutableLiveData<FormFieldState>()

    val ldAccountResponse = MutableLiveData<AccountResponse>()
    val ldAuthThrowable = MutableLiveData<Throwable>()

    fun authorizationAccount(email: String, pass: String) {
        var isValid = true
        ldValidEmail.value = when {
            email.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            else -> FormFieldState.OK
        }
        ldValidPassword.value = when {
            pass.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            else -> FormFieldState.OK
        }
        if (isValid) {
            val request = AuthorizationAccountRequest(email, pass)
            authorizationAccountViaRequest(request)
        }

    }

    private fun authorizationAccountViaRequest(request: AuthorizationAccountRequest) {
        authorizationAccountUseCase(request)
            .onSuccess { response ->
                ldAccountResponse.value = response
            }.onThrowable { ex ->
                ldAuthThrowable.value = ex
            }.launchIn(viewModelScope)
    }

}