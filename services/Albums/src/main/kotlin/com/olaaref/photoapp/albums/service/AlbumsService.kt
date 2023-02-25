package com.olaaref.photoapp.albums.service

import com.olaaref.photoapp.albums.dto.AlbumDto
import com.olaaref.photoapp.albums.model.Album
import com.olaaref.photoapp.albums.repository.AlbumsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlbumsService @Autowired constructor(val albumRepository: AlbumsRepository) {

    fun findAllAlbums(): List<AlbumDto>{
        return albumRepository.findAll().map { it.toAlbumDto() }
    }

    fun findByUserId(userId: String): List<AlbumDto> {
        return albumRepository.findByUserId(userId).map { it.toAlbumDto() }
    }
}