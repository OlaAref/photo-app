package com.olaaref.photoapp.albums

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class AlbumsApplication

fun main(args: Array<String>) {
	runApplication<AlbumsApplication>(*args)
}
