package com.ksetoue.api.port.controller

import com.ksetoue.api.domain.customer.CustomerDto
import com.ksetoue.api.application.service.CustomerService
import com.ksetoue.api.domain.customer.GetCustomerDto
import com.ksetoue.api.domain.auth.LoginDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/user")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping("/register")
    fun create(@RequestBody customerData: CustomerDto): ResponseEntity<String> {
        customerService.create(customerData)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): GetCustomerDto? {
        return customerService.findById(id)!!.toCleanUser()
    }
    
    @GetMapping
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        return try {
            val user = customerService.authenticate(jwt)
            ResponseEntity(user, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }
    
    @PostMapping("/login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<out Any> {
        val (user, cookie) = customerService.login(body)
        response.addCookie(cookie)
        
        return ResponseEntity(user.toCleanUser(),HttpStatus.OK)
    }
    
    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        
        response.addCookie(cookie)
        
        return ResponseEntity(HttpStatus.OK)
    }
}
