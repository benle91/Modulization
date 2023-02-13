package com.android.modulization.network.repositories

import com.android.modulization.data.remote.request.AuthorizationAccountRequest
import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.data.remote.response.EmptyResponse
import com.android.modulization.data.remote.response.ItemCallResponse
import com.android.modulization.network.RepositoryResult

interface FeatureRepository {
    suspend fun createAccount(request: CreateAccountRequest): RepositoryResult<AccountResponse>
    suspend fun authorizationAccount(request: AuthorizationAccountRequest): RepositoryResult<AccountResponse>
    suspend fun logout(): RepositoryResult<EmptyResponse>
}