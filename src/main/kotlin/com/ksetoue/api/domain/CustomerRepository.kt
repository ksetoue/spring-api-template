package com.ksetoue.api.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface CustomerRepository : CrudRepository<Customer, Long> {
    fun findByName(name: String): List<Customer>
    
    fun findByEmail(email:String): Optional<Customer>
}
