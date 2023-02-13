package com.android.modulization.data.remote.request

/**
 *Created by Hien on 2/12/2023.
 */
data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)