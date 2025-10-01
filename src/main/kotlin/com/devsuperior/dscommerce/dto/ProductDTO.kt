package com.devsuperior.dscommerce.dto

import com.devsuperior.dscommerce.entities.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

class ProductDTO(
    var id: Long? = null,
    @field:Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @field:NotBlank(message = "Campo requerido")
    var name: String? = null,
    @field:Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @field:NotBlank(message = "Campo requerido")
    var description: String? = null,
    @field:Positive(message = "O preço deve ser positivo")
    var price: Double? = null,
    var imgUrl: String? = null
) {
    constructor(entity: Product) : this(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        price = entity.price,
        imgUrl = entity.imgUrl
    )
}

