package com.iscbr.sbootproducts.controller

import com.iscbr.sbootproducts.entity.Product
import com.iscbr.sbootproducts.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/list")
    fun getAllProducts(): List<Product>? = productService.getAllProducts().map { it.copy(port = environment.getProperty("local.server.port")?.toInt() ?: -1) }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") idProduct: Long): Product {
        val product: Product = productService.getProductByID(idProduct) ?: Product()
        product.port = environment.getProperty("local.server.port")?.toInt() ?: -1

//        Thread.sleep(5000L)
        return product
    }
}
