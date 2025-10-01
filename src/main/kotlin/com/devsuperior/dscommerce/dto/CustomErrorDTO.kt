package com.devsuperior.dscommerce.dto

import java.time.Instant

open class CustomErrorDTO(
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val path: String
)

