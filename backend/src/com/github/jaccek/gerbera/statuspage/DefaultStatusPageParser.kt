package com.github.jaccek.gerbera.statuspage

import com.github.jaccek.gerbera.entities.DefaultStatusPage
import com.github.jaccek.gerbera.entities.Environment
import com.github.jaccek.gerbera.entities.Service
import com.google.gson.Gson

class DefaultStatusPageParser {

    private val gson = Gson()

    fun parse(entry: ServiceEntry, statusPageBody: String): Service {
        val body = gson.fromJson<DefaultStatusPage>(statusPageBody, DefaultStatusPage::class.java)
        return Service(
            name = entry.serviceName,
            version = body.healthConfig.version,
            status = body.status,
            environment = Environment.valueOf(entry.environment))
    }
}
