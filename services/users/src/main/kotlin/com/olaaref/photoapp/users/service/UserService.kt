package com.olaaref.photoapp.users.service

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.dto.response.UserDetailsResponse
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService: UserDetailsService {

    fun createUser(userDetailsRequest: UserDetailsRequest): UserDto
    fun getUserDetailsByEmail(email: String): UserDto
    fun getUserDetailsById(userId: String): UserDetailsResponse?
}