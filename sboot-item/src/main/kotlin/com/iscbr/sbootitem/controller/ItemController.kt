package com.iscbr.sbootitem.controller

import com.iscbr.sbootitem.entity.Item
import com.iscbr.sbootitem.entity.Product
import com.iscbr.sbootitem.service.ItemService
import com.iscbr.sbootitem.service.ItemServiceFeign
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    lateinit var itemServiceFeign: ItemServiceFeign

    @GetMapping("/list")
    fun getAllItems(): List<Item> = itemService.findAll() //itemServiceFeign.findAll()

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}/{quantity}")
    fun getItem(
            @PathVariable("id") id: Long,
            @PathVariable("quantity"
        ) quantity: Int): Item
            = itemService.findById(id, quantity) // itemServiceFeign.findById(id, quantity)

    fun metodoAlternativo(id: Long, quantity: Int): Item {
        val productTest = Product(id, "Producto No encontrado", 0.0, Date(), -1)
        return Item(productTest, quantity)
    }
}
