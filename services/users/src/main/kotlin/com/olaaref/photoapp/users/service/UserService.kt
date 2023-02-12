package com.olaaref.photoapp.users.service

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest

interface UserService {

    fun createUser(userDetailsRequest: UserDetailsRequest): UserDto

}