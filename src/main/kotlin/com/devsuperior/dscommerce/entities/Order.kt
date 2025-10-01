package com.devsuperior.dscommerce.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant


@Entity
@Table(name = "tb_order")
open class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    var moment: Instant? = null,
    var status: OrderStatus? = null,

    @ManyToOne
    @JoinColumn(name = "client_id")
    var client: User? = null,

    @OneToOne(mappedBy = "order", cascade = [CascadeType.ALL])
    var payment: Payment? = null,

    @OneToMany(mappedBy = "id.order")
    var items: MutableSet<OrderItem> = mutableSetOf()



){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != other.id) return false
        if (moment != other.moment) return false
        if (status != other.status) return false
        if (client != other.client) return false
        if (payment != other.payment) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

}
