package com.android.modulization.data.remote.request


import com.google.gson.annotations.SerializedName

data class AuthorizationAccountRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)