package com.android.modulization.network

import com.android.modulization.network.repositories.FeatureRepository
import com.android.modulization.network.repositories.FeatureRepositoryImp
import com.android.modulization.network.services.FeatureService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    factory { RetrofitClient.getInstance(get()) }
    single { get<Retrofit>().create(FeatureService::class.java) }
}

val repositoryModule = module {
    factory <FeatureRepository> { FeatureRepositoryImp(get()) }
}