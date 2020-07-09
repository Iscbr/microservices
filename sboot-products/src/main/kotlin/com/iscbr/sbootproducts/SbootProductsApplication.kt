package com.iscbr.sbootproducts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class SbootProductsApplication

fun main(args: Array<String>) {
    runApplication<SbootProductsApplication>(*args)
}
