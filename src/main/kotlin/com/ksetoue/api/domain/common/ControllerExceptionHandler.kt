package com.ksetoue.api.domain.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.OffsetDateTime
import javax.persistence.EntityNotFoundException

class DataNotFound(message: String?) : EntityNotFoundException(message)

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
                "resource-not-found"),
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
                "unknown-exception"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}