package com.example.services

import com.cloudinary.Cloudinary
import com.example.domain.Product
import com.example.models.MResponse
import com.example.models.ProductDTO
import com.example.repository.ProductRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductServiceImpl : ProductService {

    @Inject
    lateinit var productRepository: ProductRepository

    override fun saveProduct(product: Product, mResponse: MResponse<ProductDTO>) {

        val tempProduct = productRepository.findByName(product.name)

        if (tempProduct != null) {
            mResponse.errors.add("Produto já Cadastrado!")
            return
        }

        try {
            val cloudinary = Cloudinary(getCloudinaryPass())
            val imageResult = cloudinary.uploader()
                    .upload(product.imageBytes, mutableMapOf<String, String>())
            product.imageUrl = imageResult["url"].toString()
            product.imageBytes = null
        } catch (e: Exception) {
            print("Cloudinary Error: ${e.message}")
        }

        productRepository.apply {
            product.id = null
            save(product)
            findByName(product.name)?.let { mResponse.response = ProductDTO(it) }
        }
    }

    private fun getCloudinaryPass(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map["cloud_name"] = "cata-logo"
        map["api_key"] = "492473526762667"
        map["api_secret"] = "iSmsVR0IKx0n1cc_q2jNwyZHhLU"
        return map
    }

    override fun delete(productId: Long, mResponse: MResponse<ProductDTO>) {
        productRepository.deleteById(productId)
    }

    override fun findAll(mResponse: MResponse<Page<ProductDTO>>, page: Pageable) {
        val productList = productRepository.findAll().map { ProductDTO(it) }//productRepository.findAll(page)//.map { ProductDTO(it) }
//                .findAll().map { ProductDTO(it) }
        mResponse.response = productList//.map { ProductDTO(it) }
    }

    override fun findById(productId: Long, mResponse: MResponse<ProductDTO>) {
        val product = productRepository.findById(productId)
        if (product.isPresent) {
            mResponse.response = ProductDTO(product.get())
        } else {
            mResponse.errors.add("Id invalido!")
        }
    }
}