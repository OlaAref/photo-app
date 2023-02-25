package com.olaaref.photoapp.users.dto.response

import com.olaaref.photoapp.users.dto.AlbumDto
import com.olaaref.photoapp.users.model.User

data class UserDetailsResponse(var userId: String,
                               var firstName: String,
                               var lastName: String,
                               var email: String,
                               var albums: List<AlbumDto>)
