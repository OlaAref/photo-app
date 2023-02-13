package com.olaaref.photoapp.users.dto.request

data class LoginRequest(
    val email: String = "",
    val password: String = ""
)