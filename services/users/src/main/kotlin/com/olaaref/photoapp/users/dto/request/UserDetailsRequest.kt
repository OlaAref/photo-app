package com.olaaref.photoapp.users.dto.request

data class UserDetailsRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) {
}