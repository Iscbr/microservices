package com.iscbr.sbootproducts.controller

import com.iscbr.sbootproducts.entity.Product
import com.iscbr.sbootproducts.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/list")
    fun getAllProducts(): List<Product>? =
            productService.getAllProducts().map { it.copy(port = environment.getProperty("local.server.port")?.toInt() ?: -1) }

    @GetMapping("/{id}")
    fun getProduct(
            @PathVariable("id") idProduct: Long
    ): ResponseEntity<Any> {
        val product: Product = productService.getProductByID(idProduct) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        product.port = environment.getProperty("local.server.port")?.toInt() ?: -1
//        Thread.sleep(5000L)
        return ResponseEntity.ok(product)
    }

    @PostMapping("/create")
    fun saveProduct(
            @RequestBody product: Product
    ): ResponseEntity<Any> {
        val productSaved = productService.saveProduct(product)
        return ResponseEntity(productSaved, HttpStatus.CREATED)
    }

    @PutMapping("/edit/{id}")
    fun editProduct(
            @PathVariable("id") idProduct: Long,
            @RequestBody product: Product
    ): ResponseEntity<Any> {
        val productToEdit = productService.getProductByID(idProduct) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        productToEdit.name = product.name
        productToEdit.price = product.price

        val productEdited = productService.saveProduct(productToEdit)
        return ResponseEntity(productEdited, HttpStatus.CREATED)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteProduct(
            @PathVariable("id") idProduct: Long
    ): ResponseEntity<Any> {
        productService.getProductByID(idProduct) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        productService.deleteProductById(idProduct)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}