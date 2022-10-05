package com.ksetoue.api.domain.customer

import javax.validation.constraints.NotBlank

data class CustomerDto(
    @NotBlank
    val name: String,
    @NotBlank
    val email: String,
    @NotBlank
    val password: String,
    @NotBlank
    val userName: String,
)

data class GetCustomerDto(
    val name: String,
    val email: String,
    val userName: String
)
