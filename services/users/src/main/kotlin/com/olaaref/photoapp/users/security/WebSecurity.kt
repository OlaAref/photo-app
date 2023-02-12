package com.olaaref.photoapp.users.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurity {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    protected fun configure(http: HttpSecurity): SecurityFilterChain{
        http
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/h2-console/**").permitAll()

            // this microservice is stateless
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            //prevent spring security from add x-frame-options to header
            .and()
            .headers()
                .frameOptions().disable()

            .and()
            .csrf()
                .disable()

        return http.build()

    }
}