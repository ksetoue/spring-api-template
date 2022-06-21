package com.ksetoue.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
