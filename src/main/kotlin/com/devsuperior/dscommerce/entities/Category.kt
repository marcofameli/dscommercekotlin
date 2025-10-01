package com.devsuperior.dscommerce.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tb_category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    @ManyToMany(mappedBy = "categories")
    val products: MutableSet<Product> = mutableSetOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Category
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
