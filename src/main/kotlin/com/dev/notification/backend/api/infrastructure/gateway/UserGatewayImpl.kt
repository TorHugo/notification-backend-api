package com.dev.notification.backend.api.infrastructure.gateway

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.exception.template.GatewayException
import com.dev.notification.backend.api.domain.gateway.UserGateway
import com.dev.notification.backend.api.infrastructure.repository.db.UserRepository
import com.dev.notification.backend.api.infrastructure.repository.db.mapper.UserEntityMapper
import org.springframework.stereotype.Component

@Component
class UserGatewayImpl(
    private val userRepository: UserRepository
) : UserGateway {

    override fun save(user: UserDomain): UserDomain {
        val entity = UserEntityMapper.fromAggregate(user)
        return UserEntityMapper.toAggregate(userRepository.save(entity))
    }

    override fun findByEmail(email: String): UserDomain? {
        val entity = userRepository.findByEmail(email)
        return entity?.let { UserEntityMapper.toAggregate(it) }
    }

    override fun findByEmailWithThrows(email: String): UserDomain {
        val entity = userRepository.findByEmail(email) ?: throw GatewayException("User not found!", email)
        return UserEntityMapper.toAggregate(entity)
    }

}