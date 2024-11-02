package com.dev.notification.backend.api.infrastructure.service

import com.dev.notification.backend.api.application.FindAuthorityByName
import com.dev.notification.backend.api.application.FindUserByEmail
import com.dev.notification.backend.api.application.SaveToUserAuthorities
import com.dev.notification.backend.api.application.SaveUser
import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.enums.AuthorityEnum
import com.dev.notification.backend.api.domain.enums.TemplateMessageEnum
import com.dev.notification.backend.api.domain.exception.template.DomainException
import com.dev.notification.backend.api.domain.`object`.Parameter
import com.dev.notification.backend.api.domain.service.SignInService
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignInServiceImpl(
    private val findUserByEmail: FindUserByEmail,
    private val saveUser: SaveUser,
    private val saveToUserAuthorities: SaveToUserAuthorities,
    private val findAuthorityByName: FindAuthorityByName,
    private val applicationEventPublisher: ApplicationEventPublisher
) : SignInService {

    @Transactional
    override fun execute(domain: UserDomain): String {
        validateUserDoesNotExist(domain.email.value)
        createUser(domain)
        assignAuthorities(domain)
        sendNotification(domain)
        return domain.getIdentifier()
    }

    private fun validateUserDoesNotExist(email: String) {
        val existsUser = findUserByEmail.execute(email)
        if (Objects.nonNull(existsUser)) {
            throw DomainException("This user already exists with e-mail!", email)
        }
    }

    private fun createUser(domain: UserDomain) {
        saveUser.execute(domain)
    }

    private fun assignAuthorities(userDomain: UserDomain) {
        val authority = findAuthorityByName.execute(AuthorityEnum.DEFAULT_USER.authorityName)
        saveToUserAuthorities.execute(userDomain, listOf(authority))
    }

    private fun sendNotification(domain: UserDomain) {
        val templateMessage = TemplateMessageEnum.CONFIRMED_ACCOUNT
        val parameters = listOf(
            Parameter("name", domain.fullName())
        )
        val notification = NotificationDomain.create(
            domain.email.value,
            templateMessage.subject,
            templateMessage.template,
            parameters
        )
        applicationEventPublisher.publishEvent(notification)
    }
}
