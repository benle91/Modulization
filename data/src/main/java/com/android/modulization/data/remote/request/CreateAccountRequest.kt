package com.android.modulization.data.remote.request


import com.google.gson.annotations.SerializedName

data class CreateAccountRequest(
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)