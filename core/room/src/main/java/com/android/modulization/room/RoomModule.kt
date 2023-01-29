package com.android.modulization.room

import androidx.room.Room
import com.android.modulization.room.dao.ItemToCallDao
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "modulation")
            .createFromAsset("modulation.db")
            .fallbackToDestructiveMigration().build()
    }
    single<ItemToCallDao> { get<AppDatabase>().getItemCallDao() }
}