package com.android.modulization.network.repositories

import com.android.modulization.data.remote.request.AuthorizationAccountRequest
import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.data.remote.response.EmptyResponse
import com.android.modulization.network.BaseHandleDataResponseSupporter
import com.android.modulization.network.RepositoryResult
import com.android.modulization.network.services.FeatureService

class FeatureRepositoryImp constructor(private val service: FeatureService) : FeatureRepository,
    BaseHandleDataResponseSupporter {
    override suspend fun createAccount(request: CreateAccountRequest): RepositoryResult<AccountResponse> {
        return executeService { service.authSignUp(request) }
    }

    override suspend fun authorizationAccount(request: AuthorizationAccountRequest): RepositoryResult<AccountResponse> {
        return executeService { service.authSignIn(request) }
    }

    override suspend fun logout(): RepositoryResult<EmptyResponse> {
        return executeServiceEmptyResponse { service.authLogout() }
    }


}