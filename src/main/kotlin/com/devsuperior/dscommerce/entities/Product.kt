package com.devsuperior.dscommerce.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tb_product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    var price: Double? = null,

    var imgUrl: String? = null,

    @ManyToMany
    @JoinTable(
        name = "tb_product_category",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: MutableSet<Category> = mutableSetOf(),

    @OneToMany(mappedBy = "id.product")
    val items: MutableSet<OrderItem> = mutableSetOf()
) {
    val orders: List<Order>
        get() = items.mapNotNull { it.order }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
