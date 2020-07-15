package com.iscbr.sbootproducts.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "productos")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var idProduct: Long? = null,

        @Column(name = "name")
        var name: String,

        @Column(name = "price")
        var price: Double,

        @Column(name = "create_at")
        @Temporal(TemporalType.DATE)
        var createAt: Date,

        @Transient
        var port: Int) : Serializable {

        constructor() : this(-1, "", 0.0, Date(), -1)
}
