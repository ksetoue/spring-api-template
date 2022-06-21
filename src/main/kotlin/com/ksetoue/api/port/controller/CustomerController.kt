package com.ksetoue.api.port.controller

import com.ksetoue.api.domain.Customer
import com.ksetoue.api.domain.CustomerDto
import com.ksetoue.api.application.service.CustomerService
import com.ksetoue.api.domain.GetCustomerDto
import com.ksetoue.api.domain.LoginDto
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
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
        
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        
            return ResponseEntity.ok(customerService.findById(body.issuer.toLong())!!.toCleanUser())
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated")
        }
    }
    
    @PostMapping("/login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<out Any> {
        val user = this.customerService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body("user not found!")
    
        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body("invalid password!")
        }
    
        val issuer = user.id.toString()
    
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
    
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
    
        response.addCookie(cookie)
    
        return ResponseEntity(user.toCleanUser(), HttpStatus.OK)
    }
    
    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        
        response.addCookie(cookie)
        
        return ResponseEntity(HttpStatus.OK)
    }
}
