package com.devsuperior.dscommerce.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "tb_payment")
open class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    var moment: Instant? = null,

    @OneToOne
    @MapsId
    var order: Order? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Payment) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
