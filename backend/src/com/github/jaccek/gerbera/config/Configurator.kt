package com.github.jaccek.gerbera.config

import com.google.gson.Gson
import java.io.File

fun readConfig(): Config {
    val plainConfig = File("resources/services.json").readText(Charsets.UTF_8)
    val gson = Gson()
    return gson.fromJson(plainConfig, Config::class.java)
}
