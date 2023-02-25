package com.olaaref.photoapp.albums.controller

import com.olaaref.photoapp.albums.dto.AlbumDto
import com.olaaref.photoapp.albums.model.Album
import com.olaaref.photoapp.albums.service.AlbumsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/albums")
class AlbumsController @Autowired constructor(val albumsService: AlbumsService){

    @GetMapping
    fun getAllAlbums(): List<AlbumDto>{
        return albumsService.findAllAlbums()
    }

    @GetMapping("/{userId}")
    fun getAlbumByUser(@PathVariable("userId") userId: String): List<AlbumDto>{
        return albumsService.findByUserId(userId)
    }
}