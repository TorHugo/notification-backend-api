package com.dev.notification.backend.api.infrastructure.repository.db

import com.dev.notification.backend.api.infrastructure.repository.db.models.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<AuthorityEntity, String> {
    fun findByName(name: String): AuthorityEntity?
}
