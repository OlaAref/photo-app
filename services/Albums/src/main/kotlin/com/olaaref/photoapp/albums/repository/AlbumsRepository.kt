package com.olaaref.photoapp.albums.repository

import com.olaaref.photoapp.albums.model.Album
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumsRepository: JpaRepository<Album, Long> {
    fun findByUserId(userId: String): List<Album>
}