package com.iscbr.sbootitem.entity

import java.util.*

data class Product(
        var id: Long,
        var name: String,
        var price: Double,
        var createAt: Date,
        var port: Int) {
    constructor(): this(0, "", 0.0, Date(), -1)
}
