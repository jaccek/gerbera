package com.github.jaccek.gerbera.config

import com.github.jaccek.gerbera.config.entities.DefaultConfig
import com.github.jaccek.gerbera.config.entities.ServiceEntry

data class Config(
    val services: List<ServiceEntry>,
    val default: DefaultConfig
)
