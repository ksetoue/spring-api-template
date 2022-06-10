package com.ksetoue.api.service

import com.ksetoue.api.domain.Customer
import com.ksetoue.api.domain.CustomerDto
import com.ksetoue.api.domain.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun create(customerData: CustomerDto) {
        val newCustomer = Customer(null, customerData.name, customerData.email)
        customerRepository.save(newCustomer)
    }
}