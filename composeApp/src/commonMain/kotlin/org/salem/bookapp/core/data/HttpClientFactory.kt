package org.salem.bookapp.core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.http.ContentType
import io.ktor.http.contentType

object HttpClientFactory{
    fun create(engine : HttpClientEngine) : HttpClient{
        return HttpClient(engine){

            install(ContentNegotiation){  // automatic parse Json Response to object
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }

            install(HttpTimeout){
                requestTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                contentType(
                    ContentType.Application.Json
                )
            }


        }

    }
}