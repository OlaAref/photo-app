package com.olaaref.photoapp.users.dto

import java.io.Serializable

data class UserDto(var id: Long,
                    var userId: String,
                    var firstName: String,
                    var lastName: String,
                    var email: String,): Serializable{

}
