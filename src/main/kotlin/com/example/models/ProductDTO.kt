package com.example.models

import com.example.domain.Product

data class ProductDTO(
        var id: Long,
        var name: String,
        var amount: Int,
        var priceInCents: Int,
        var imageUrl: String,
        var imageBytes: String? = null,
        var enabled: Boolean? = null,
        var sizes: List<Int>
) {
    constructor(product: Product) : this(
            id = product.id ?: 0L,
            name = product.name,
            amount = product.amount,
            priceInCents = product.priceInCents,
            imageUrl = product.imageUrl ?: "",
            imageBytes = product.imageBytes,
            sizes = product.sizes,
            enabled = product.enabled
    )

    fun toUse(): Product? {
        return if (validade()) {
            Product(
                    id = id,
                    name = name,
                    amount = amount,
                    priceInCents = priceInCents,
                    imageUrl = imageUrl,
                    imageBytes = imageBytes,
                    sizes = sizes,
                    enabled = enabled
            )
        } else {
            null
        }
    }

    fun validade(): Boolean = !name.isEmpty()
}