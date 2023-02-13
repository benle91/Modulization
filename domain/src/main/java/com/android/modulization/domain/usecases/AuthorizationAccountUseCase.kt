package com.android.modulization.domain.usecases

import com.android.modulization.data.remote.request.AuthorizationAccountRequest
import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.domain.BaseFlowUseCase
import com.android.modulization.domain.UseCaseResult
import com.android.modulization.network.RepositoryResult
import com.android.modulization.network.repositories.FeatureRepository
import kotlinx.coroutines.CoroutineDispatcher

/**
 *Created by Hien on 2/12/2023.
 */
class AuthorizationAccountUseCase constructor(
    private val repository: FeatureRepository,
    executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<AuthorizationAccountRequest, AccountResponse, AccountResponse>(executionThread) {

    override suspend fun execute(parameters: AuthorizationAccountRequest): RepositoryResult<AccountResponse> {
        return repository.authorizationAccount(parameters)
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<AccountResponse>): UseCaseResult.Success<AccountResponse> {
        return UseCaseResult.Success(response.data ?: AccountResponse())
    }

}