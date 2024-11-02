package com.dev.notification.backend.api.infrastructure.repository.db

import com.dev.notification.backend.api.infrastructure.repository.db.models.AuthorityToUserEntity
import com.dev.notification.backend.api.infrastructure.repository.db.models.CompositeKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityToUserRepository : JpaRepository<AuthorityToUserEntity, CompositeKey>
