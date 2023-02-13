package com.android.modulization.network

import android.content.Context
import com.android.modulization.data.preference.PrefRepository
import com.android.modulization.data.preference.PreferenceKey
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://streaming.nexlesoft.com:4000/api/"

    fun getInstance(context: Context): Retrofit {

        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mAuthInterceptor = Interceptor { chain ->
            val token = PrefRepository.getDefaultSharedPreferences(context)
                .getString(PreferenceKey.ACCESS_TOKEN, "")
            val newRequest = chain.request().newBuilder().addHeader(
                "Authorization", "Bearer $token"
            ).build()
            chain.proceed(newRequest)
        }

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor(mAuthInterceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

}