package com.devsuperior.dscommerce.controllers.handlers

import com.devsuperior.dscommerce.dto.CustomErrorDTO
import com.devsuperior.dscommerce.dto.ValidationErrorDTO
import com.devsuperior.dscommerce.services.exceptions.DatabaseException
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFound(e: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<CustomErrorDTO> {
        val status = HttpStatus.NOT_FOUND
        val err = CustomErrorDTO(Instant.now(), status.value(), e.message ?: "", request.requestURI)
        return ResponseEntity.status(status).body(err)
    }

    @ExceptionHandler(DatabaseException::class)
    fun database(e: DatabaseException, request: HttpServletRequest): ResponseEntity<CustomErrorDTO> {
        val status = HttpStatus.BAD_REQUEST
        val err = CustomErrorDTO(Instant.now(), status.value(), e.message ?: "", request.requestURI)
        return ResponseEntity.status(status).body(err)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidation(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ValidationErrorDTO> {
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val err = ValidationErrorDTO(
            Instant.now(),
            status.value(),
            "Dados inválidos",
            request.requestURI
        )
        for (f in e.bindingResult.fieldErrors) {
            err.addError(f.field, f.defaultMessage ?: "")
        }
        return ResponseEntity.status(status).body(err)
    }
}

