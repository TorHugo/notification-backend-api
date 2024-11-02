package com.dev.notification.backend.api.infrastructure.api

import com.dev.notification.backend.api.infrastructure.api.dto.request.LoginUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.request.SignInUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultMessageDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.UserSuccessfullyDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/auth")
interface AuthenticationAPI {

    @PostMapping("/public/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    fun signIn(@Valid @RequestBody entry: SignInUserDTO) : DefaultDTO<UserSuccessfullyDTO>

    @PostMapping("/public/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@Valid @RequestBody entry: LoginUserDTO) : ResponseEntity<DefaultDTO<DefaultMessageDTO>>

    @PostMapping("/public/logout")
    @ResponseStatus(HttpStatus.OK)
    fun logout() : ResponseEntity<DefaultDTO<DefaultMessageDTO>>
}