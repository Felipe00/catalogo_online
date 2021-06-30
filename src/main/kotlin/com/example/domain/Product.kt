package com.example.domain

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Where
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
@Where("@.enabled = true")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @Column(name = "name")
        var name: String = "",
        @Column(name = "idEnterprise")
        var idEnterprise: String = "",
        @Column(name = "amount")
        var amount: Int = 0,
        @Column(name = "priceInCents")
        var priceInCents: Int = 0,
        @Column(name = "imageUrl")
        var imageUrl: String? = null,
        @Column(name = "imageBytes")
        var imageBytes: String? = null,
        @Column(name = "stockAmount")
        var stockAmount: Int = 0,
        @Column(name = "sizes")
        @ElementCollection(fetch = FetchType.EAGER)
        var sizes: List<Int> = listOf(),
        @DateCreated
        @Column(name = "createdAt")
        var createdAt: Instant? = null,
        @Column(name = "enabled")
        var enabled: Boolean?= true
)