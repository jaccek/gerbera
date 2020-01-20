package com.github.jaccek.gerbera.statuspage

import com.github.jaccek.gerbera.config.entities.ServiceEntry
import com.github.jaccek.gerbera.entities.Environment
import com.github.jaccek.gerbera.entities.Service
import com.github.jaccek.gerbera.entities.Status
import com.google.gson.Gson

class DefaultStatusPageParser {

    private val gson = Gson()

    fun parse(entry: ServiceEntry, statusPageBody: String): Service {
        val body = gson.fromJson<Map<String, Any>>(statusPageBody, Map::class.java)

        val version = getValueFromPath(body, entry.statusParsing!!.versionPath) // TODO: remove !!
        val status = when (getValueFromPath(body, entry.statusParsing.statusPath)) {
            entry.statusParsing.ok -> Status.UP
            else -> Status.DOWN
        }

        return Service(
            name = entry.serviceName,
            version = version,
            status = status,
            environment = Environment.valueOf(entry.environment.toUpperCase())) // TODO: what if env is not found?
    }

    private fun getValueFromPath(body: Map<String, Any>?, path: String): String {
        val splitPath = path.split('.')
        var obj: Any? = body
        splitPath.forEach { obj = (obj as Map<*, *>)[it] }
        return obj as String
    }
}
