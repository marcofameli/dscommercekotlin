package com.devsuperior.dscommerce.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String? = null,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    val price: Double? = null,

    val imgUrl: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)// retire esse eager dps
    @JoinTable(
        name = "tb_product_category",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: MutableSet<Category> = mutableSetOf(),

  //  @OneToMany(mappedBy = "id.product")// o certo é esse
    @OneToMany(fetch = FetchType.EAGER)
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
