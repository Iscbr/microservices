package com.iscbr.zuulserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
class ZuulServerApplication

fun main(args: Array<String>) {
    runApplication<ZuulServerApplication>(*args)
}
