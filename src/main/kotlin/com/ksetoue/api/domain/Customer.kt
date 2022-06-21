package com.ksetoue.api.domain

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String,
    val email: String,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val company: String? = null,
    val password: String,
    val userName: String
) {
    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
    
    fun toCleanUser(): GetCustomerDto = GetCustomerDto(
        this.name,
        this.email,
        this.userName
    )
    
    companion object {
        fun from(customerDto: CustomerDto): Customer {
            val passwordEncoder = BCryptPasswordEncoder()
            val encodedPass = passwordEncoder.encode(customerDto.password)
            return Customer(
                id = null,
                name = customerDto.name,
                userName = customerDto.userName,
                email = customerDto.email,
                password = encodedPass
            )
        }
    }
}
