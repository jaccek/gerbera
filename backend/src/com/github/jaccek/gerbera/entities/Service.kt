package com.github.jaccek.gerbera.entities

data class Service(
    private val name: String,
    private val version: String,
    private val status: Status,
    private val environment: Environment
)
