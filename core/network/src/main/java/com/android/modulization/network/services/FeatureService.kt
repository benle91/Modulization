package com.android.modulization.network.services

import com.android.modulization.data.remote.request.AuthorizationAccountRequest
import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.request.SignUpRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.data.remote.response.EmptyResponse
import com.android.modulization.data.remote.response.ItemCallResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FeatureService {
    @POST("auth/signup")
    suspend fun authSignUp(@Body request: CreateAccountRequest?): Response<AccountResponse>

    @POST("auth/signin")
    suspend fun authSignIn(@Body request: AuthorizationAccountRequest?): Response<AccountResponse>

    @POST("auth/logout")
    suspend fun authLogout(): Response<EmptyResponse>
}