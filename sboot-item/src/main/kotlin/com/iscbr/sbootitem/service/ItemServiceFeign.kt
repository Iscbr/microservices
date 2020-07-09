package com.iscbr.sbootitem.service

import com.iscbr.sbootitem.clients.ProductClientRest
import com.iscbr.sbootitem.entity.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemServiceFeign {

    @Autowired
    lateinit var productClientRest: ProductClientRest

    fun findAll(): List<Item> {
        return productClientRest.getAllProducts().map { Item(it, 1) }
    }

    fun findById(id: Long, quantity: Int): Item {
        return Item(productClientRest.getProduct(id), quantity)
    }
}
