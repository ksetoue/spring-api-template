package com.ksetoue.api.domain.customer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {
    fun findByName(name: String): List<Customer>

    fun findByEmail(email: String): Optional<Customer>

    fun findByEmailOrUserName(email: String, userName: String): Optional<Customer>
}
