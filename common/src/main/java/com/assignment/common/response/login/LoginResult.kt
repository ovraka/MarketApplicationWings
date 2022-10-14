package com.assignment.common.response.login


import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)