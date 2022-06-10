package com.ksetoue.api.domain

import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {
    fun findByName(name: String): List<Customer>
}