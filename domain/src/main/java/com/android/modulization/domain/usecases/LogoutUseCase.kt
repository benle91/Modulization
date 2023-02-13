package com.android.modulization.domain.usecases

import com.android.modulization.data.remote.request.CreateAccountRequest
import com.android.modulization.data.remote.response.AccountResponse
import com.android.modulization.data.remote.response.EmptyResponse
import com.android.modulization.domain.BaseFlowUseCase
import com.android.modulization.domain.UseCaseResult
import com.android.modulization.network.RepositoryResult
import com.android.modulization.network.repositories.FeatureRepository
import kotlinx.coroutines.CoroutineDispatcher

/**
 *Created by Hien on 2/12/2023.
 */
class LogoutUseCase constructor(
    private val repository: FeatureRepository,
    executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, EmptyResponse, EmptyResponse>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<EmptyResponse> {
        return repository.logout()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<EmptyResponse>): UseCaseResult.Success<EmptyResponse> {
        return UseCaseResult.Success(response.data ?: EmptyResponse())
    }

}