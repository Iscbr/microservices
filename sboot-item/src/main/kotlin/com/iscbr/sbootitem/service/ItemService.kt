package com.iscbr.sbootitem.service

import com.iscbr.sbootitem.entity.Item
import com.iscbr.sbootitem.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.stream.Collectors

@Service
class ItemService {

    @Autowired
    lateinit var restTemplate: RestTemplate;

    fun findAll(): List<Item> {
        val products: List<Product> = restTemplate.getForObject("http://product-service/list", Array<Product>::class.java)?.toList() ?: emptyList()
        return products.map { Item(it, 1) }
    }

    fun findById(id: Long, quantity: Int): Item {
        val params: HashMap<String, String> = HashMap()
        params["id"] = id.toString()
        val product: Product = restTemplate.getForObject("http://product-service/{id}", Product::class.java, params) ?: Product()
        return Item(product, quantity)
    }
}
