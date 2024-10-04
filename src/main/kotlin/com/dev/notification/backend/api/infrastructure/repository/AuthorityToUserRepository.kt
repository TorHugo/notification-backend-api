package com.dev.notification.backend.api.infrastructure.repository

import com.dev.notification.backend.api.infrastructure.repository.models.AuthorityToUserEntity
import com.dev.notification.backend.api.infrastructure.repository.models.CompositeKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityToUserRepository : JpaRepository<AuthorityToUserEntity, CompositeKey>