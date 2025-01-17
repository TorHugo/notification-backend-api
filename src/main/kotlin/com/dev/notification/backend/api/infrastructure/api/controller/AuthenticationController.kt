package com.dev.notification.backend.api.infrastructure.api.controller

import com.dev.notification.backend.api.application.Login
import com.dev.notification.backend.api.application.Logout
import com.dev.notification.backend.api.domain.service.SignInService
import com.dev.notification.backend.api.infrastructure.api.AuthenticationAPI
import com.dev.notification.backend.api.infrastructure.api.dto.request.LoginUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.request.SignInUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultMessageDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.UserSuccessfullyDTO
import com.dev.notification.backend.api.infrastructure.api.mappers.UserToDomainMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val login: Login,
    private val logout: Logout,
    private val encoder: PasswordEncoder,
    private val signIn: SignInService
) : AuthenticationAPI {

    override fun signIn(entry: SignInUserDTO): DefaultDTO<UserSuccessfullyDTO> {
        val encryptedPassword = encoder.encode(entry.password)
        val userDomain = UserToDomainMapper.toUserDomain(entry, encryptedPassword)
        return DefaultDTO.created(UserSuccessfullyDTO(signIn.execute(userDomain)))
    }

    override fun login(entry: LoginUserDTO): ResponseEntity<DefaultDTO<DefaultMessageDTO>> {
        val cookie = login.execute(entry.email, entry.password)
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(DefaultDTO.success(DefaultMessageDTO("Login successfully!")))
    }

    override fun logout(): ResponseEntity<DefaultDTO<DefaultMessageDTO>> {
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, logout.toString())
            .body(DefaultDTO.success(DefaultMessageDTO("Logout successfully!")))
    }
}
