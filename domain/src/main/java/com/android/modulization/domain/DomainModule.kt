package com.android.modulization.domain

import com.android.modulization.domain.usecases.AuthorizationAccountUseCase
import com.android.modulization.domain.usecases.CreateAccountUseCase
import com.android.modulization.domain.usecases.LogoutUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { CreateAccountUseCase(get()) }
    single { AuthorizationAccountUseCase(get()) }
    single { LogoutUseCase(get()) }
}