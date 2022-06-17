package com.ksetoue.api.application.service

import com.ksetoue.api.domain.Customer
import com.ksetoue.api.domain.CustomerDto
import com.ksetoue.api.domain.CustomerRepository
import com.ksetoue.api.domain.common.DataNotFound
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    fun create(customerData: CustomerDto) {
        val newCustomer = Customer(null, customerData.name, customerData.email)
        customerRepository.save(newCustomer)
    }

    fun findById(id: Long): Customer? {
        return customerRepository.findById(id)
            .orElseThrow {
                logger.error("could not find customer: $id")
                DataNotFound("customer")
            }
    }
}
