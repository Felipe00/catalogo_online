package com.example.controllers

import com.cloudinary.Cloudinary
import com.example.models.MResponse
import com.example.models.ProductDTO
import com.example.services.ProductService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured
import io.netty.util.concurrent.Future
import io.reactivex.Maybe
import io.reactivex.Single
import java.lang.Exception
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

@Controller("/produtos")
class ProductController {

    @Inject
    lateinit var productService: ProductService

    //    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Secured("isAuthenticated()")
    @Post()
    fun save(@Body productDTO: ProductDTO): HttpResponse<MResponse<ProductDTO>> {
        val response = MResponse<ProductDTO>()
        val product = productDTO.toUse()
        return if (product != null) {
            productService.saveProduct(product, response)
            HttpResponse.ok(response)
        } else {
            response.errors.add("Produto inválido!")
            HttpResponse.badRequest(response)
        }
    }

    @Get()
    fun findAll(page: Int?): HttpResponse<MResponse<Page<ProductDTO>>> {
        val response = MResponse<Page<ProductDTO>>()
        productService.findAll(response, Pageable.from(page?: 1))
        return HttpResponse.ok(response) // TODO não funciona;
    }

    @Secured("isAuthenticated()")
    @Delete("/{id}")
    fun delete(@Part id: Long): HttpResponse<MResponse<ProductDTO>> {
        val response = MResponse<ProductDTO>()
        productService.delete(id, response)
        return HttpResponse.ok(response)
    }

    @Get("/{id}")
    fun findById(@Part id: Long): HttpResponse<MResponse<ProductDTO>> {
        val response = MResponse<ProductDTO>()
        productService.findById(id, response)
        return HttpResponse.ok(response)
    }
}