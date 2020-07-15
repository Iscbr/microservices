package com.iscbr.sbootproducts.service

import com.iscbr.sbootproducts.entity.Product
import com.iscbr.sbootproducts.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Transactional(readOnly = true)
    fun getAllProducts(): List<Product> = productRepository.findAll().map { it }

    @Transactional(readOnly = true)
    fun getProductByID(idProduct: Long): Product? = productRepository.findById(idProduct).map { it }.orElse(null)

    @Transactional
    fun saveProduct(product: Product): Product = productRepository.save(product)

    @Transactional
    fun deleteProductById(idProduct: Long) = productRepository.deleteById(idProduct)
}
