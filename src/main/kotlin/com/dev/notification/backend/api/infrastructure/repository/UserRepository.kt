package com.dev.notification.backend.api.infrastructure.repository

import com.dev.notification.backend.api.infrastructure.repository.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByEmail(email: String): UserEntity?
}