package com.iscbr.sbootproducts.repository

import com.iscbr.sbootproducts.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long>
