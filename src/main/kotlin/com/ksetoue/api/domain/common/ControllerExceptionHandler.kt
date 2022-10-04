package com.ksetoue.api.domain.common

import org.springframework.dao.DuplicateKeyException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.security.InvalidKeyException
import java.time.OffsetDateTime
import javax.security.auth.message.AuthException

class DataNotFound(message: String?) : NotFoundException()
class InvalidData(message: String?) : InvalidKeyException(message)

class AuthDataNotFound(message: String?) : AuthException(message)

class DuplicatedData(message: String) : DuplicateKeyException(message)
data class ErrorDetails(
    val timestamp: OffsetDateTime,
    val message: String,
    val details: String,
    val code: String
)

@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(DataNotFound::class)
    fun dataNotFoundExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        return ResponseEntity<Any>(
            ErrorDetails(
                OffsetDateTime.now(),
                "${exception.message} not found",
                request.getDescription(false),
                "resource-not-found"
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        return ResponseEntity<Any>(
            ErrorDetails(
                OffsetDateTime.now(),
                "could not process request for ${exception.message}",
                request.getDescription(false),
                "unknown-exception"
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(InvalidData::class)
    fun invalidDataExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        return ResponseEntity<Any>(
            ErrorDetails(
                OffsetDateTime.now(),
                "${exception.message} is invalid",
                request.getDescription(false),
                "invalid-data"
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(AuthDataNotFound::class)
    fun authDataExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        return ResponseEntity<Any>(
            ErrorDetails(
                OffsetDateTime.now(),
                "not allowed ${exception.message}",
                request.getDescription(false),
                "missing-auth"
            ),
            HttpStatus.FORBIDDEN
        )
    }

    @ExceptionHandler(DuplicatedData::class)
    fun duplicatedDataExceptionHandler(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        return ResponseEntity<Any>(
            ErrorDetails(
                OffsetDateTime.now(),
                "${exception.message} already exists",
                request.getDescription(false),
                "duplicated-key"
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
        )
    }
}
