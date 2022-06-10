package com.ksetoue.api.port.controller

import com.ksetoue.api.domain.Customer
import com.ksetoue.api.domain.CustomerDto
import com.ksetoue.api.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    fun create(@RequestBody customerData: CustomerDto) {
        return customerService.create(customerData)
    }
}