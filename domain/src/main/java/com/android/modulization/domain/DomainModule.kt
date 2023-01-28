package com.android.modulization.domain

import com.android.modulization.domain.usecases.GetItemsToCallUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetItemsToCallUseCase(get()) }
}