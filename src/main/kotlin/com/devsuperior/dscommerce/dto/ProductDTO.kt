package com.devsuperior.dscommerce.dto

import com.devsuperior.dscommerce.entities.Product

data class ProductDTO(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val imgUrl: String? = null
) {
    constructor(entity: Product) : this(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        price = entity.price,
        imgUrl = entity.imgUrl
    )
}