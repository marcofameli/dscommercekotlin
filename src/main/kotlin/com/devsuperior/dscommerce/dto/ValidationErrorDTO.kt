package com.devsuperior.dscommerce.dto

import java.time.Instant

class ValidationErrorDTO(
    timestamp: Instant,
    status: Int,
    error: String,
    path: String
) : CustomErrorDTO(timestamp, status, error, path) {
    val errors: MutableList<FieldMessageDTO> = mutableListOf()
    fun addError(fieldName: String, message: String) {
        errors.add(FieldMessageDTO(fieldName, message))
    }
}

