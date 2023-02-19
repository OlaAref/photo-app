package com.olaaref.photoapp.users.security

import com.olaaref.photoapp.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurity @Autowired constructor(val passwordEncoder: BCryptPasswordEncoder,
                                        val userService: UserService,
                                        val environment: Environment) {

    @Bean
    protected fun configure(http: HttpSecurity): SecurityFilterChain{

        // Configure AuthenticationManagerBuilder
        var authenticationManagerBuilder: AuthenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder)

        // Get AuthenticationManager
        val authenticationManager: AuthenticationManager = authenticationManagerBuilder.build()

        http
            .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2-console/**", "/login").permitAll()
                .anyRequest().authenticated()

            .and()
            .addFilter(authFilter(authenticationManager))
            .authenticationManager(authenticationManager)

            // this microservice is stateless
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            //prevent spring security from add x-frame-options to header
            .and()
            .headers()
                .frameOptions().disable()

        http.csrf().disable()

        return http.build()

    }

    private fun authFilter(authenticationManager: AuthenticationManager): AuthenticationFilter{
        val filter = AuthenticationFilter(userService, environment)
        filter.setAuthenticationManager(authenticationManager)
        //filter.setFilterProcessesUrl("/login")
        return filter
    }
}