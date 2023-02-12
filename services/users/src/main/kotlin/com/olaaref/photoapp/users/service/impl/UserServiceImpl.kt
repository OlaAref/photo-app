package com.olaaref.photoapp.users.service.impl

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.model.User
import com.olaaref.photoapp.users.repository.UserRepository
import com.olaaref.photoapp.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl @Autowired constructor(val userRepository: UserRepository,
                                             val passwordEncoder: BCryptPasswordEncoder) : UserService {

    override fun createUser(userDetailsRequest: UserDetailsRequest): UserDto {

        val userId = UUID.randomUUID().toString()
        val encryptedPassword = passwordEncoder.encode(userDetailsRequest.password)
        val user = User(firstName = userDetailsRequest.firstName, lastName = userDetailsRequest.lastName,
            email = userDetailsRequest.email, userId = userId, encryptedPassword = encryptedPassword)

        userRepository.save(user)
        return user.toUserDto()
    }
}