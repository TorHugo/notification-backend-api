package com.dev.notification.backend.api.infrastructure.service

import com.dev.notification.backend.api.application.FindAuthorityByName
import com.dev.notification.backend.api.application.FindUserByEmail
import com.dev.notification.backend.api.application.SaveToUserAuthorities
import com.dev.notification.backend.api.application.SaveUser
import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.enums.AuthorityEnum
import com.dev.notification.backend.api.domain.exception.template.DomainException
import com.dev.notification.backend.api.domain.service.SignInService
import com.dev.notification.backend.api.infrastructure.api.dto.request.SignInUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.UserSuccessfullyDTO
import com.dev.notification.backend.api.infrastructure.mappers.UserToDomainMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignInServiceImpl(
    private val findUserByEmail: FindUserByEmail,
    private val saveUser: SaveUser,
    private val saveToUserAuthorities: SaveToUserAuthorities,
    private val findAuthorityByName: FindAuthorityByName,
    private val encoder: PasswordEncoder
): SignInService {

    override fun execute(entry: SignInUserDTO): UserSuccessfullyDTO {
        TODO("Refactoring to use domain object as a method parameter.")
        validateUserDoesNotExist(entry.email)
        val userDomain = createUser(entry)
        assignAuthorities(userDomain)
        notifyUserCreation()
        return UserSuccessfullyDTO(userDomain.getIdentifier())
    }

    private fun validateUserDoesNotExist(email: String) {
        val existsUser = findUserByEmail.execute(email)
        if (Objects.nonNull(existsUser))
            throw DomainException("This user already exists with e-mail!", email)
    }

    private fun createUser(entry: SignInUserDTO): UserDomain {
        val password = encoder.encode(entry.password)
        val userDomain = UserToDomainMapper.toUserDomain(entry, password)
        saveUser.execute(userDomain)
        return userDomain
    }

    private fun assignAuthorities(userDomain: UserDomain) {
        val authority = findAuthorityByName.execute(AuthorityEnum.DEFAULT_USER.authorityName)
        saveToUserAuthorities.execute(userDomain, listOf(authority))
    }

    private fun notifyUserCreation() {
        TODO("Not yet implemented!")
    }
}