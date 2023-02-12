package com.olaaref.photoapp.users.controller

import com.olaaref.photoapp.users.dto.UserDto
import com.olaaref.photoapp.users.dto.request.UserDetailsRequest
import com.olaaref.photoapp.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController @Autowired constructor(private val userService: UserService) {

    @GetMapping("/status/checks")
    fun status(): String{
        return "Working"
    }

    @PostMapping
    fun createUser(@RequestBody userDetailsRequest: UserDetailsRequest): ResponseEntity<UserDto> =
        ResponseEntity.ok(userService.createUser(userDetailsRequest))
}