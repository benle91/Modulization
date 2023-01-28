package com.android.modulization.network

sealed class RepositoryResult<T> {
    data class Success<T>(
        val `data`: T?,
    ) : RepositoryResult<T>()

    data class Error<T>(val error: Exception) : RepositoryResult<T>()
}