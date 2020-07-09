package com.iscbr.sbootitem.controller

import com.iscbr.sbootitem.entity.Item
import com.iscbr.sbootitem.entity.Product
import com.iscbr.sbootitem.service.ItemService
import com.iscbr.sbootitem.service.ItemServiceFeign
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.HashMap

@RestController
class ItemController {

    @Autowired
    lateinit var environment: Environment

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

    @GetMapping("/get-config")
    fun getConfig(): ResponseEntity<Any> {
        val json = HashMap<String, String>()

        json["portInConfigServer"] = environment.getProperty("server.port") ?: "No port available"
        json["textInConfigServer"] = environment.getProperty("config.info") ?: "No text available"

        if (environment.activeProfiles.isNotEmpty() && environment.activeProfiles[0] == "dev") {
            json["authorInConfigServer"] = environment.getProperty("config.author.name") ?: "No author available"
            json["emailInConfigServer"] = environment.getProperty("config.author.email") ?: "No email available"
        }

        return ResponseEntity(json, HttpStatus.OK)
    }
}
