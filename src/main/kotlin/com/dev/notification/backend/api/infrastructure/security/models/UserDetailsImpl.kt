package com.dev.notification.backend.api.infrastructure.security.models

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
    private val email: String,
    @JsonIgnore private val password: String,
    private val active: Boolean,
    private val confirmed: Boolean,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    fun isActive(): Boolean = active

    fun isConfirmed(): Boolean = confirmed

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = active

    companion object {
        fun build(entity: UserDomain, authorityList: List<GrantedAuthority>) =
            UserDetailsImpl(
                email = entity.email.value,
                password = entity.password,
                active = entity.active,
                confirmed = entity.confirmed,
                authorities = authorityList
            )
    }
}