package com.iscbr.sbootproducts.repository

import com.iscbr.sbootproducts.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductRepository : CrudRepository<Product, Long> {
    @Transactional(readOnly = true)
    fun findByIdProduct(idProduct: Long): Product
}
