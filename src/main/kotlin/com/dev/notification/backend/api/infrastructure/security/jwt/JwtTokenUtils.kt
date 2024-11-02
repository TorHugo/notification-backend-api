package com.dev.notification.backend.api.infrastructure.security.jwt

import com.dev.notification.backend.api.infrastructure.security.models.UserDetailsImpl
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts.builder
import io.jsonwebtoken.Jwts.parser
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.WeakKeyException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils.getCookie
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenUtils {
    private val logger = LoggerFactory.getLogger(JwtTokenUtils::class.java)

    companion object {
        private const val COOKIE = "token"
    }

    @Value("\${spring.security.security-key}")
    private lateinit var secret: String

    @Value("\${spring.security.token-expiration}")
    private var expiration: Int = 0

    fun getTokenFromCookie(request: HttpServletRequest): String? =
        getCookie(request, COOKIE)?.value

    fun validateToken(token: String): Boolean {
        return try {
            parser()
                .verifyWith(getSigningKey(secret))
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: WeakKeyException) {
            logger.error("Invalid JWT signature: ${e.message}")
            false
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: ${e.message}")
            false
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: ${e.message}")
            false
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: ${e.message}")
            false
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: ${e.message}")
            false
        }
    }

    fun getUserName(token: String): String =
        parser()
            .verifyWith(getSigningKey(secret))
            .build()
            .parseSignedClaims(token)
            .payload
            .subject

    fun getAuthoritiesFromToken(token: String): Any? =
        parser()
            .verifyWith(getSigningKey(secret))
            .build()
            .parseSignedClaims(token)
            .payload["authorities"]

    fun getExpirationDate(token: String): Date =
        parser()
            .verifyWith(getSigningKey(secret))
            .build()
            .parseSignedClaims(token)
            .payload
            .expiration

    fun generateToken(user: UserDetailsImpl): ResponseCookie {
        val token = generateTokenFromUser(user)
        return ResponseCookie.from(COOKIE, token)
            .path("/")
            .maxAge(216_000)
            .secure(true)
            .httpOnly(true)
            .build()
    }

    fun cleanToken(): ResponseCookie =
        ResponseCookie.from(COOKIE, "")
            .path("/")
            .maxAge(0)
            .secure(true)
            .httpOnly(true)
            .build()

    private fun generateTokenFromUser(user: UserDetailsImpl): String {
        if (!user.isActive()) throw AccessDeniedException("Account is expired!")
        val dateNow = Date()
        return builder()
            .claim("email", user.username)
            .claim("authorities", user.authorities)
            .subject(user.username)
            .issuedAt(Date())
            .expiration(Date(dateNow.time + expiration))
            .signWith(getSigningKey(secret))
            .compact()
    }

    private fun getSigningKey(secret: String): SecretKey =
        Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
}
