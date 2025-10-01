package com.devsuperior.dscommerce.controllers

import com.devsuperior.dscommerce.dto.ProductDTO
import com.devsuperior.dscommerce.services.ProductService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/products")
class ProductController(private val service: ProductService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ProductDTO> {
        val dto = service.findById(id)
        return ResponseEntity.ok(dto)
    }

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<ProductDTO>> {
        val dto = service.findAll(pageable)
        return ResponseEntity.ok(dto)
    }

    @PostMapping
    fun insert(@Valid @RequestBody dto: ProductDTO): ResponseEntity<ProductDTO> {
        val savedDto = service.insert(dto)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedDto.id).toUri()
        return ResponseEntity.created(uri).body(savedDto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: ProductDTO): ResponseEntity<ProductDTO> {
        val updatedDto = service.update(id, dto)
        return ResponseEntity.ok(updatedDto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
