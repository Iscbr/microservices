package com.iscbr.sbootitem.clients

import com.iscbr.sbootitem.entity.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "product-service")
interface ProductClientRest {

    @GetMapping("/list")
    fun getAllProducts(): List<Product>

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") idProduct: Long): Product

    @PostMapping("/create")
    fun saveProduct(@RequestBody product: Product): Product

    @PutMapping("/edit/{id}")
    fun updateProduct(@PathVariable("id") idProduct: Long, @RequestBody product: Product): Product

    @DeleteMapping("/delete/{id}")
    fun deleteProduct(@PathVariable("id") idProduct: Long)
}
