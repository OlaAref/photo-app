package com.olaaref.photoapp.users.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController {

    @GetMapping("/status/checks")
    fun status(): String{
        return "Working"
    }
}