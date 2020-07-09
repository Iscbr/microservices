package com.iscbr.sbootitem.clients

import com.iscbr.sbootitem.entity.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product-service")
interface ProductClientRest {

    @GetMapping("/list")
    fun getAllProducts(): List<Product>

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") idProduct: Long): Product
}
