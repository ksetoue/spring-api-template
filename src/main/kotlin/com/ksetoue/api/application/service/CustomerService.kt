package com.ksetoue.api.application.service

import com.ksetoue.api.domain.auth.LoginDto
import com.ksetoue.api.domain.common.AuthDataNotFound
import com.ksetoue.api.domain.common.DataNotFound
import com.ksetoue.api.domain.common.DuplicatedData
import com.ksetoue.api.domain.common.InvalidData
import com.ksetoue.api.domain.customer.Customer
import com.ksetoue.api.domain.customer.CustomerDto
import com.ksetoue.api.domain.customer.CustomerRepository
import com.ksetoue.api.domain.customer.GetCustomerDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Date
import javax.servlet.http.Cookie

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun create(customerData: CustomerDto) {
        customerRepository.findByEmailOrUserName(customerData.email, customerData.userName)
            .ifPresent { throw DuplicatedData("email or userName") }

        val newCustomer = Customer.from(customerData)
        customerRepository.save(newCustomer)
    }

    fun findById(id: Long): Customer? {
        return customerRepository.findById(id)
            .orElseThrow {
                logger.error("could not find customer: $id")
                DataNotFound("customer")
            }
    }

    fun findByEmail(email: String): Customer? {
        return customerRepository.findByEmail(email)
            .orElseThrow {
                logger.error("could not find customer by email")
                DataNotFound("customer")
            }
    }

    fun authenticate(token: String?): GetCustomerDto {
        if (token == null) {
            throw AuthDataNotFound("token")
        }

        val data = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).body
        val user = findById(data.issuer.toLong())!!
        return user.toCleanUser()
    }

    fun login(data: LoginDto): Pair<Customer, Cookie> {
        val user = findByEmail(data.email)
            ?: throw DataNotFound("customer")

        if (!user.comparePassword(data.password)) {
            throw InvalidData("password")
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        return Pair(user, cookie)
    }
}
