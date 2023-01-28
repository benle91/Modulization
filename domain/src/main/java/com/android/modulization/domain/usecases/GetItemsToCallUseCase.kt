package com.android.modulization.domain.usecases

import com.android.modulization.data.remote.response.ItemCallResponse
import com.android.modulization.domain.BaseFlowUseCase
import com.android.modulization.domain.UseCaseResult
import com.android.modulization.network.RepositoryResult
import com.android.modulization.network.repositories.FeatureRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetItemsToCallUseCase constructor(
    private val repository: FeatureRepository,
    executionThread: CoroutineDispatcher? = null
) : BaseFlowUseCase<String, List<ItemCallResponse>, List<ItemCallResponse>>(executionThread) {

    override suspend fun execute(parameters: String): RepositoryResult<List<ItemCallResponse>> {
        return repository.getItemsToCall()
    }

    override suspend fun onSuccess(response: RepositoryResult.Success<List<ItemCallResponse>>): UseCaseResult.Success<List<ItemCallResponse>> {
        return UseCaseResult.Success(response.data ?: emptyList<ItemCallResponse>())
    }

}