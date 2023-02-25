package com.olaaref.photoapp.users.infrastructure.albums.service

import com.olaaref.photoapp.users.dto.AlbumDto
import com.olaaref.photoapp.users.infrastructure.albums.client.AlbumsClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlbumsService @Autowired constructor(private val albumsClient: AlbumsClient){

    fun getAlbums(userId: String): List<AlbumDto>{
        return albumsClient.getAlbumByUser(userId)
    }
}