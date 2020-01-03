package com.github.jaccek.statuspageadapters

import com.github.jaccek.entities.Environment
import com.github.jaccek.entities.Service
import com.github.jaccek.entities.Status
import com.github.jaccek.statuspageadapters.entities.DefaultStatusPage
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DefaultStatusPageAdapter {

    private val gson = Gson()

    fun requestStatusPage(serviceName: String, url: URL): Service {
        val connection = url.openConnection() as HttpURLConnection
        connection.useCaches = false
        connection.connect()

        val statusCode = connection.responseCode
        return if (statusCode >= 300) {
            // TODO: environment
            connection.disconnect()
            Service(serviceName, "unknown", Status.DOWN, Environment.RC)
        } else {
            val reader = InputStreamReader(connection.inputStream)
            val body = gson.fromJson<DefaultStatusPage>(reader, DefaultStatusPage::class.java)
            connection.disconnect()

            Service(serviceName, body.healthConfig.version, Status.UP, Environment.RC)
        }
    }
}
