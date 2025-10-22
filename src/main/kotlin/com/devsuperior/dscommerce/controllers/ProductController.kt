//package com.devsuperior.dscommerce.controllers
//
//import com.devsuperior.dscommerce.dto.ProductDTO
//import com.devsuperior.dscommerce.services.ProductService
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/products")
//class ProductController(
//    private val productservice: ProductService
//) {
//    @GetMapping("/{id}")
//    fun findById(@PathVariable id: Long): ProductDTO {
//        return productservice.findById(id)
//    }
//}

package com.devsuperior.dscommerce.controllers

import com.devsuperior.dscommerce.entities.Product
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    // Vamos injetar o EntityManager para ter acesso direto ao banco
    @PersistenceContext
    private val entityManager: EntityManager
    // private val repository: ProductRepository // Você pode manter o repo seguro
) {

    // ===================================================================
    //
    //  !! ATENÇÃO: MÉTODO VULNERÁVEL A SQL INJECTION !!
    //      (Use apenas para fins de estudo local)
    //
    // ===================================================================
    @Transactional(readOnly = true)
    @GetMapping("/vulnerable/{id}")
    fun findByIdVulnerable(@PathVariable id: String): ResponseEntity<List<Product>> {

        // 1. A VULNERABILIDADE:
        // Estamos "colando" o 'id' (String) que vem da URL
        // diretamente dentro da string da consulta SQL.
        val sql = "SELECT * FROM tb_product WHERE id = " + id

        // Vamos imprimir no console para ver a mágica acontecer
        println("--- SQL VULNERÁVEL EXECUTADO ---")
        println(sql)
        println("----------------------------------")

        // 2. Criando e executando a consulta nativa (raw SQL)
        val query = entityManager.createNativeQuery(sql, Product::class.java)

        // 3. Obtendo os resultados
        // Usamos @Suppress para o cast, já que createNativeQuery retorna Object
        @Suppress("UNCHECKED_CAST")
        val products = query.resultList as List<Product>

        return ResponseEntity.ok(products)
    }
}
