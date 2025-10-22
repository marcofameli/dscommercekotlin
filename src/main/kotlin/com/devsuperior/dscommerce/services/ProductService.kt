package com.devsuperior.dscommerce.services

import com.devsuperior.dscommerce.dto.ProductDTO
import com.devsuperior.dscommerce.repositories.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    @Transactional(readOnly = true)
    fun findById(id: Long): ProductDTO {
        val product = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }
        return ProductDTO(product)
    }
}