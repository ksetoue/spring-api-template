package com.ksetoue.api.port.controller

import com.ksetoue.api.domain.Customer
import com.ksetoue.api.domain.CustomerDto
import com.ksetoue.api.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun create(@RequestBody customerData: CustomerDto): ResponseEntity<String> {
        customerService.create(customerData)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Customer?> {
        return ResponseEntity(customerService.findById(id), HttpStatus.OK)
    }
}
