package com.android.modulization.data.remote.response


import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("admin")
    val admin: Boolean? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("displayName")
    val displayName: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("__v")
    val v: Int? = null
)