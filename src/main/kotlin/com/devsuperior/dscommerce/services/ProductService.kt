package com.devsuperior.dscommerce.services

import com.devsuperior.dscommerce.dto.ProductDTO
import com.devsuperior.dscommerce.entities.Product
import com.devsuperior.dscommerce.repositories.ProductRepository
import com.devsuperior.dscommerce.services.exceptions.DatabaseException
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException
import jakarta.persistence.EntityNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    @Transactional(readOnly = true)
    fun findById(id: Long): ProductDTO {
        val product = productRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Recurso não encontrado")
        }
        return ProductDTO(product)
    }

    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<ProductDTO> {
        val result = productRepository.findAll(pageable)
        return result.map { ProductDTO(it) }
    }

    @Transactional
    fun insert(dto: ProductDTO): ProductDTO {
        val product = Product()
        copyDtoToEntity(dto, product)
        val saved = productRepository.save(product)
        return ProductDTO(saved)
    }

    @Transactional
    fun update(id: Long, dto: ProductDTO): ProductDTO {
        try {
            val product = productRepository.getReferenceById(id)
            copyDtoToEntity(dto, product)
            val saved = productRepository.save(product)
            return ProductDTO(saved)
        } catch (e: EntityNotFoundException) {
            logger.error("Error updating product with id $id", e)
            throw ResourceNotFoundException("Recurso não encontrado")
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    fun delete(id: Long) {
        if (!productRepository.existsById(id)) {
            throw ResourceNotFoundException("Recurso não encontrado")
        }
        try {
            productRepository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            logger.error("Error deleting product with id $id", e)
            throw DatabaseException("Falha de integridade referencial")
        }
    }

    private fun copyDtoToEntity(dto: ProductDTO, product: Product) {
        product.name = dto.name
        product.description = dto.description
        product.price = dto.price
        product.imgUrl = dto.imgUrl
    }
}

