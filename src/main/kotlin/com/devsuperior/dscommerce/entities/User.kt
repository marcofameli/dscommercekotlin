package com.devsuperior.dscommerce.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tb_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    @Column(unique = true)
    var email: String? = null,
    var phone: String? = null,
    var birthDate: LocalDate? = null,
    var password: String? = null,
    @OneToMany(mappedBy = "client")
    val orders: MutableList<Order> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}
