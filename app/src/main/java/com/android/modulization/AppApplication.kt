package com.android.modulization

import android.app.Application
import com.android.modulization.data.preference.PrefRepository
import com.android.modulization.domain.useCaseModule
import com.android.modulization.network.networkModule
import com.android.modulization.network.repositoryModule
import com.android.modulization.room.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                module {
                    single { PrefRepository.getDefaultSharedPreferences(get()) }
                    factory { PrefRepository(get()) }
                },
                roomModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )

        }
    }


}