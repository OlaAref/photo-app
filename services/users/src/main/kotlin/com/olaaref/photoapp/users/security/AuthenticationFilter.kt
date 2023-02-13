package com.olaaref.photoapp.users.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.olaaref.photoapp.users.dto.request.LoginRequest
import com.olaaref.photoapp.users.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter @Autowired constructor(private val userService: UserService,
                                                  private val environment: Environment): UsernamePasswordAuthenticationFilter() {


    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {

        val credentials = ObjectMapper().readValue(request?.inputStream, LoginRequest::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.email,
                credentials.password
            )
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?,
                                            chain: FilterChain?, authResult: Authentication?
                                        ) {

        val username = (authResult?.principal as User).username
        val userDto = userService.getUserDetailsByEmail(username)

        val token = Jwts.builder()
            .setSubject(userDto.userId)
            .setExpiration(
                Date(
                    System.currentTimeMillis().plus(environment.getProperty("token.expiration_time")?.toLong()!!)
                )
            )
            .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
            .compact()

        response?.addHeader("token", token)
        response?.addHeader("userId", userDto.userId)
    }
}