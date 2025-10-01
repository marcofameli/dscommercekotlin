package com.devsuperior.dscommerce.entities

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "tb_order_item")
open class OrderItem(
    @EmbeddedId
    var id: OrderItemPK = OrderItemPK(),
    var quantity: Int? = null,
    var price: Double? = null
) {
    var order: Order?
        get() = id.order
        set(value) { id.order = value }

    var product: Product?
        get() = id.product
        set(value) { id.product = value }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderItem) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

