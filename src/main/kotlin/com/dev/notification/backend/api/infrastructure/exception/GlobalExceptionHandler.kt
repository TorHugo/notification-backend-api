package com.dev.notification.backend.api.infrastructure.exception

import com.dev.notification.backend.api.domain.exception.template.DomainException
import com.dev.notification.backend.api.domain.exception.template.GatewayException
import com.dev.notification.backend.api.domain.exception.template.InvalidArgumentException
import com.dev.notification.backend.api.domain.exception.template.RepositoryException
import com.dev.notification.backend.api.domain.exception.template.ServiceException
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultDTO
import com.dev.notification.backend.api.infrastructure.exception.models.ExceptionData
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(RepositoryException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRepositoryException(
        ex: RepositoryException,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.error("Repository exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            error = "Repository Error",
            message = ex.message,
            path = request.requestURI,
        )
    }

    @ExceptionHandler(DomainException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleDomainException(
        ex: DomainException,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.warn("Domain exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.BAD_REQUEST,
            error = "Domain Error",
            message = ex.message,
            path = request.requestURI,
        )
    }

    @ExceptionHandler(ServiceException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleServiceException(
        ex: ServiceException,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.warn("Service exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.BAD_REQUEST,
            error = "Service Error",
            message = ex.message,
            path = request.requestURI,
        )
    }

    @ExceptionHandler(InvalidArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidArgumentException(
        ex: InvalidArgumentException,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.warn("Invalid Argument exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.BAD_REQUEST,
            error = "Invalid Argument Error",
            message = ex.message,
            path = request.requestURI,
        )
    }

    @ExceptionHandler(GatewayException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGatewayException(
        ex: GatewayException,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.warn("Gateway Exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            error = "Gateway Error",
            message = ex.message,
            path = request.requestURI,
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(
        ex: Exception,
        request: HttpServletRequest,
    ): DefaultDTO<ExceptionData> {
        logger.error("Unhandled exception occurred", ex)
        return createErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            error = "Internal Server Error",
            message = "An unexpected error occurred",
            path = request.requestURI,
        )
    }

    private fun createErrorResponse(
        status: HttpStatus,
        error: String,
        message: String?,
        path: String,
    ): DefaultDTO<ExceptionData> = DefaultDTO(
        status = status.value(),
        data = ExceptionData(
            error = error,
            message = message ?: "No additional information available",
            path = path,
        ),
    )
}
