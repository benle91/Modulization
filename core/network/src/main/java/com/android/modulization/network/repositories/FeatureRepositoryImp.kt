package com.android.modulization.network.repositories

import com.android.modulization.data.remote.response.ItemCallResponse
import com.android.modulization.network.BaseHandleDataResponseSupporter
import com.android.modulization.network.RepositoryResult
import com.android.modulization.network.services.FeatureService

class FeatureRepositoryImp constructor(private val service: FeatureService) : FeatureRepository,
    BaseHandleDataResponseSupporter {

    override suspend fun getItemsToCall(): RepositoryResult<List<ItemCallResponse>> {
        return executeService { service.getItemsToCall() }
    }
}