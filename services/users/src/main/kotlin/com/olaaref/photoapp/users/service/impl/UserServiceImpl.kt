package com.olaaref.photoapp.users.service.impl

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.dto.response.UserDetailsResponse
import com.olaaref.photoapp.users.infrastructure.albums.service.AlbumsService
import com.olaaref.photoapp.users.model.User
import com.olaaref.photoapp.users.repository.UserRepository
import com.olaaref.photoapp.users.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl @Autowired constructor(val userRepository: UserRepository,
                                             val albumsService: AlbumsService,
                                             val passwordEncoder: BCryptPasswordEncoder) : UserService {

    val logger: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun createUser(userDetailsRequest: UserDetailsRequest): UserDto {
        logger.info("User is ready to be created.")
        val userId = UUID.randomUUID().toString()
        val encryptedPassword = passwordEncoder.encode(userDetailsRequest.password)
        val user = User()
        user.firstName = userDetailsRequest.firstName
        user.lastName = userDetailsRequest.lastName
        user.email = userDetailsRequest.email
        user.userId = userId
        user.encryptedPassword = encryptedPassword

        userRepository.save(user)
        logger.info("User is created")
        return user.toUserDto()
    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        logger.info("Get user by email.")
        when(val user = userRepository.findByEmail(email)){
            null -> throw UsernameNotFoundException(email)
            else -> return user.toUserDto()
        }
    }

    override fun getUserDetailsById(userId: String): UserDetailsResponse? {
        logger.info("Get user by Id.")
        val user = userRepository.findByUserId(userId)
        val albums = albumsService.getAlbums(userId)
        return user?.let { UserDetailsResponse(user.userId, user.firstName, user.lastName, user.email, albums) }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        when(val user = userRepository.findByEmail(username!!)){
            null -> throw UsernameNotFoundException(username)
            else -> return org.springframework.security.core.userdetails.User(username, user.encryptedPassword,
                true, true, true, true, emptyList())
        }
    }
}