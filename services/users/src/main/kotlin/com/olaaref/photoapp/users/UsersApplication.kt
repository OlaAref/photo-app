package com.olaaref.photoapp.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
class UsersApplication{
	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
	runApplication<UsersApplication>(*args)
}

