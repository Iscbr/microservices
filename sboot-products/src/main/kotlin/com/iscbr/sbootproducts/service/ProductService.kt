package com.iscbr.sbootproducts.service

import com.iscbr.sbootproducts.entity.Product
import com.iscbr.sbootproducts.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun getAllProducts(): List<Product> = productRepository.findAll().map { it }

    fun getProductByID(idProduct: Long): Product? = productRepository.findById(idProduct).map { it }.orElse(null)
}
