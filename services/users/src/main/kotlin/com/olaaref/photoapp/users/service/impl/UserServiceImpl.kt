package com.olaaref.photoapp.users.service.impl

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.model.User
import com.olaaref.photoapp.users.repository.UserRepository
import com.olaaref.photoapp.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl @Autowired constructor(val userRepository: UserRepository,
                                             val passwordEncoder: BCryptPasswordEncoder) : UserService {

    override fun createUser(userDetailsRequest: UserDetailsRequest): UserDto {

        val userId = UUID.randomUUID().toString()
        val encryptedPassword = passwordEncoder.encode(userDetailsRequest.password)
        val user = User()
        user.firstName = userDetailsRequest.firstName
        user.lastName = userDetailsRequest.lastName
        user.email = userDetailsRequest.email
        user.userId = userId
        user.encryptedPassword = encryptedPassword

        userRepository.save(user)
        return user.toUserDto()
    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        when(val user = userRepository.findByEmail(email)){
            null -> throw UsernameNotFoundException(email)
            else -> return user.toUserDto()
        }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        when(val user = userRepository.findByEmail(username!!)){
            null -> throw UsernameNotFoundException(username)
            else -> return org.springframework.security.core.userdetails.User(username, user.encryptedPassword,
                true, true, true, true, emptyList())
        }
    }
}