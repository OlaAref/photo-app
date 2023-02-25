package com.olaaref.photoapp.users.infrastructure.albums.client

import com.olaaref.photoapp.users.dto.AlbumDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

//name: service application name
@FeignClient(name = "albums-ws")
interface AlbumsClient {

    @GetMapping("/albums/{userId}")
    fun getAlbumByUser(@PathVariable("userId") userId: String): List<AlbumDto>
}