package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.AuthorityDomain
import com.dev.notification.backend.api.domain.entity.AuthorityToUserDomain
import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.gateway.AuthorityToUserGateway
import org.springframework.stereotype.Component

@Component
class SaveToUserAuthorities(
    private val authorityToUserGateway: AuthorityToUserGateway
) {

    fun execute(user: UserDomain, authorities: List<AuthorityDomain>) {
        val authoritiesToUser: List<AuthorityToUserDomain> = List(authorities.size) { index ->
            AuthorityToUserDomain.create(user.identifier, authorities[index].identifier)
        }
        authorityToUserGateway.saveAll(authoritiesToUser)
    }
}
