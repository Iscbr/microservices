package com.iscbr.sbootproducts.entity

import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "productos")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idProduct: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "price")
        val price: Double,

        @Column(name = "create_at")
        @Temporal(TemporalType.DATE)
        val createAt: Date,

        @Transient
        var port: Int) {

        constructor() : this(-1, "", 0.0, Date(), -1)
}
