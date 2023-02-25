package com.olaaref.photoapp.users.controller

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.dto.response.UserDetailsResponse
import com.olaaref.photoapp.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController @Autowired constructor(private val userService: UserService, private val env: Environment) {

    @GetMapping("/status/checks")
    fun status(): String{
        return "Working with ${env.getProperty("token.secret")}"
    }

    @PostMapping
    fun createUser(@RequestBody userDetailsRequest: UserDetailsRequest): ResponseEntity<UserDto> =
        ResponseEntity.ok(userService.createUser(userDetailsRequest))

    @GetMapping("/{userId}")
    fun getUserDetails(@PathVariable("userId") userId:String): ResponseEntity<UserDetailsResponse>{
        return ResponseEntity.ok(userService.getUserDetailsById(userId))
    }
}