package com.olaaref.photoapp.users.repository

import com.olaaref.photoapp.users.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}