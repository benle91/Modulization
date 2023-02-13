package com.android.modulization

import com.android.modulization.auth.signin.SignInViewModel
import com.android.modulization.auth.signup.SignUpViewModel
import com.android.modulization.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}