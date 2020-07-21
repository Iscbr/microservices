package com.iscbr.sbootitem.service

import com.iscbr.sbootitem.clients.ProductClientRest
import com.iscbr.sbootitem.entity.Item
import com.iscbr.sbootitem.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemServiceFeign {

    @Autowired
    lateinit var productClientRest: ProductClientRest

    fun findAll(): List<Item> = productClientRest.getAllProducts().map { Item(it, 1) }

    fun findById(id: Long, quantity: Int): Item =  Item(productClientRest.getProduct(id), quantity)

    fun saveProduct(product: Product): Product = productClientRest.saveProduct(product)

    fun updateProduct(id: Long, product: Product): Product = productClientRest.updateProduct(id, product)

    fun deleteProduct(id: Long) = productClientRest.deleteProduct(id)
}
