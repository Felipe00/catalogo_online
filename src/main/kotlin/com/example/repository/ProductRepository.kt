package com.example.repository

import com.example.domain.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository

@Repository
interface ProductRepository : PageableRepository<Product, Long> {

    fun findByName(name: String): Product?

}