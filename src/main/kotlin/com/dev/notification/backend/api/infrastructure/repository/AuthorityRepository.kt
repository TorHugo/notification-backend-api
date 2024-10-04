package com.dev.notification.backend.api.infrastructure.repository

import com.dev.notification.backend.api.infrastructure.repository.models.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<AuthorityEntity, String>{
    fun findByName(name: String): AuthorityEntity?
}