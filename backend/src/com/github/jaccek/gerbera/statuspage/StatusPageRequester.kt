package com.github.jaccek.gerbera.statuspage

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class StatusPageRequester {

    fun requestStatusPage(entry: ServiceEntry): String? {
        val url = URL(entry.statusPageUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.useCaches = false

        connection.connect()

        val body: String?
        val statusCode = connection.responseCode
        if (statusCode >= 300) {
            body = null
        } else {
            var line: String?
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val stringBuffer = StringBuffer()
            while (reader.readLine().also { line = it } != null) {
                stringBuffer.append(line)
            }
            body = stringBuffer.toString()
        }

        connection.disconnect()

        return body
    }
}
