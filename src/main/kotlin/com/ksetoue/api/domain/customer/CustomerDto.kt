package com.ksetoue.api.domain

data class CustomerDto(
    val name: String,
    val email: String,
    val password: String,
    val userName: String,
)

data class GetCustomerDto(
    val name: String,
    val email: String,
    val userName: String
)
