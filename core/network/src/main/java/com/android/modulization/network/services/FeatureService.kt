package com.android.modulization.network.services

import com.android.modulization.data.remote.response.ItemCallResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeatureService {
    @GET("demo-1/call")
    suspend fun getItemsToCall(): Response<List<ItemCallResponse>>
}