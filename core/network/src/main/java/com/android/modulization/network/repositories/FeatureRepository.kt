package com.android.modulization.network.repositories

import com.android.modulization.data.remote.response.ItemCallResponse
import com.android.modulization.network.RepositoryResult

interface FeatureRepository {
    suspend fun getItemsToCall(): RepositoryResult<List<ItemCallResponse>>
}