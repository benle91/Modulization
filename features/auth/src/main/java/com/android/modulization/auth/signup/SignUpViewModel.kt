package com.android.modulization.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.modulization.data.local.models.FormFieldState
import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.domain.onSuccess
import com.android.modulization.domain.onThrowable
import com.android.modulization.domain.usecases.CreateAccountUseCase
import com.android.modulization.utils.isEmailValid
import com.android.modulization.utils.isEnoughStrengthPassword
import kotlinx.coroutines.flow.launchIn

/**
 *Created by Hien on 2/12/2023.
 */
class SignUpViewModel constructor(
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    val ldValidFirstName = MutableLiveData<FormFieldState>()
    val ldValidLastName = MutableLiveData<FormFieldState>()
    val ldValidEmail = MutableLiveData<FormFieldState>()
    val ldValidPassword = MutableLiveData<FormFieldState>()

    val ldAccountResponse = MutableLiveData<AccountResponse>()
    val ldAuthThrowable = MutableLiveData<Throwable>()

    fun createAccount(firstName: String, lastName: String, email: String, pass: String) {
        var isValid = true
        ldValidFirstName.value = when {
            firstName.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            else -> FormFieldState.OK
        }
        ldValidLastName.value = when {
            lastName.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            else -> FormFieldState.OK
        }
        ldValidEmail.value = when {
            email.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            !email.isEmailValid() -> {
                isValid = false
                FormFieldState.EMAIL_NOT_VALID
            }
            else -> FormFieldState.OK
        }
        ldValidPassword.value = when {
            pass.isBlank() -> {
                isValid = false
                FormFieldState.MUST_BE_REQUIRE
            }
            pass.length !in 6..18 -> {
                isValid = false
                FormFieldState.PASSWORD_NOT_ENOUGH_LENGTH
            }
            !pass.isEnoughStrengthPassword() -> {
                isValid = false
                FormFieldState.PASSWORD_NOT_ENOUGH_STRENGTH
            }
            else -> FormFieldState.OK
        }
        if (isValid) {
            val request = CreateAccountRequest(firstName, lastName, email, pass)
            createAccountViaRequest(request)
        }

    }

    private fun createAccountViaRequest(request: CreateAccountRequest) {
        createAccountUseCase(request)
            .onSuccess { response ->
                ldAccountResponse.value = response
            }.onThrowable { ex ->
                ldAuthThrowable.value = ex
            }
            .launchIn(viewModelScope)
    }

}