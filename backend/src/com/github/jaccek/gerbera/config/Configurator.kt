package com.github.jaccek.gerbera.config

import com.google.gson.Gson
import java.io.File

fun readConfig(): Config {
    val plainConfig = File("resources/services.json").readText(Charsets.UTF_8)
    val gson = Gson()
    val config = gson.fromJson(plainConfig, Config::class.java)     // TODO: handle parsing errors

    val services = config.services.map {
        if (it.statusParsing == null) {
            it.copy(statusParsing = config.default.statusParsing)
        } else {
            it
        }
    }

    return config.copy(services = services)
}
