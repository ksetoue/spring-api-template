package com.ksetoue.api.domain

import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val company: String? = null,
    val password: String? = null,
    val userName: String? = null
)