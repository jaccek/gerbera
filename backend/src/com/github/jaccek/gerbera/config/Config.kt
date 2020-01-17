package com.github.jaccek.gerbera.config

import com.github.jaccek.gerbera.statuspage.ServiceEntry

data class Config(
    val services: List<ServiceEntry>
)
