package com.butterutil.config

import com.butterutil.ButterUtils
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

@Serializable
data class PersistentData(
    var exampleData: Map<String, String> = mapOf("key1" to "entry1"),
    var moreExampleData: Int = 5
) {

    fun save() {
        configFile.writeText(Json.encodeToString(this))
    }

    companion object {
        private val configFile: File = File("./config/butterutil/config.toml")

        fun load(): PersistentData {
            val data = if (!configFile.exists()) {
                configFile.createNewFile()
                PersistentData()
            } else configFile.runCatching {
                Json.decodeFromString<PersistentData>(this.readText())
            }.getOrNull() ?: PersistentData()
            return data.apply {
                this.save()
            }
        }
    }
}