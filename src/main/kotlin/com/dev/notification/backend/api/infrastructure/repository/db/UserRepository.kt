package com.dev.notification.backend.api.infrastructure.repository.db

import com.dev.notification.backend.api.infrastructure.repository.db.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByEmail(email: String): UserEntity?
}
