package com.iscbr.sbootitem.entity

data class Item(
        val product: Product,
        val quantity: Int
) {
    fun getTotal(): Double = product.price * quantity.toDouble()
}
