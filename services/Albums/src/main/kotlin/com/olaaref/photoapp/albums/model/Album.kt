package com.olaaref.photoapp.albums.model

import com.olaaref.photoapp.albums.constants.AlbumsColumn
import com.olaaref.photoapp.albums.constants.TableName
import com.olaaref.photoapp.albums.dto.AlbumDto
import javax.persistence.*

@Entity
@Table(name = TableName.ALBUMS)
class Album() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = AlbumsColumn.ID)
    var Id: Long = 0
    @Column(name = AlbumsColumn.ALBUM_ID)
    lateinit var albumId: String
    @Column(name = AlbumsColumn.USER_ID)
    lateinit var userId: String
    @Column(name = AlbumsColumn.NAME)
    lateinit var name: String
    @Column(name = AlbumsColumn.DESCRIPTION)
    lateinit var description: String

    fun toAlbumDto() = AlbumDto(albumId, userId, name, description)
}