package com.example.services

import com.example.models.MResponse
import com.example.domain.Product
import com.example.models.ProductDTO
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface ProductService {

    fun saveProduct(product: Product, MResponse: MResponse<ProductDTO>)

    fun delete(productId: Long, mResponse: MResponse<ProductDTO>)

    fun findAll(mResponse: MResponse<Page<ProductDTO>>, page: Pageable)

    fun findById(productId: Long, mResponse: MResponse<ProductDTO>)

}