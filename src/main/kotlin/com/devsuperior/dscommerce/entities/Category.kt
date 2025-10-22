package com.devsuperior.dscommerce.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "categories") o certo é esse
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
