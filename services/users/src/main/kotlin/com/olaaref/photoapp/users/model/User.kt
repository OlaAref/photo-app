package com.olaaref.photoapp.users.model

import com.olaaref.photoapp.users.constants.TableName
import com.olaaref.photoapp.users.constants.UsersColumn
import com.olaaref.photoapp.users.dto.UserDto
import javax.persistence.*

@Entity
@Table(name = TableName.USERS)
class User(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UsersColumn.ID)
    var id: Long = 0
    @Column(name = UsersColumn.USER_ID, nullable = false, unique = true)
    lateinit var userId: String
    @Column(name = UsersColumn.FIRST_NAME, nullable = false, length = 50)
    lateinit var firstName: String
    @Column(name = UsersColumn.LAST_NAME, nullable = false, length = 50)
    lateinit var lastName: String
    @Column(name = UsersColumn.EMAIL, nullable = false, length = 120, unique = true)
    lateinit var email: String
    @Column(name = UsersColumn.PASSWORD, nullable = false, unique = true)
    lateinit var encryptedPassword: String


    fun toUserDto(): UserDto{
        return UserDto(id, userId, firstName, lastName, email)
    }
}