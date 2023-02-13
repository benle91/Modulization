package com.android.modulization.network

import com.android.modulization.data.remote.response.EmptyResponse
import retrofit2.HttpException
import retrofit2.Response

interface BaseHandleDataResponseSupporter {

    suspend fun <_Response> executeService(api: suspend () -> Response<_Response>): RepositoryResult<_Response> {
        try {
            api.invoke().let { response ->
                response.body()?.let { res ->
                    if (response.isSuccessful) {
                        return RepositoryResult.Success(res)
                    }
                }
                return RepositoryResult.Error(error = HttpException(response))
            }
        } catch (ex: Exception) {
            // Unknown
            return RepositoryResult.Error(ex)
        }
    }

    suspend fun executeServiceEmptyResponse(api: suspend () -> Response<EmptyResponse>): RepositoryResult<EmptyResponse> {
        try {
            api.invoke().let { response ->
                if (response.isSuccessful) {
                    return RepositoryResult.Success(EmptyResponse())
                }
                return RepositoryResult.Error(error = HttpException(response))
            }
        } catch (ex: Exception) {
            // Unknown
            return RepositoryResult.Error(ex)
        }
    }

    suspend fun <_Response> executeDao(api: suspend () -> _Response): RepositoryResult<_Response> {
        return try {
            val response = api.invoke()
            RepositoryResult.Success(response)
        } catch (ex: Exception) {
            // Unknown
            RepositoryResult.Error(ex)
        }
    }
}