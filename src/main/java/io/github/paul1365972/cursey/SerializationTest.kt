package io.github.paul1365972.cursey

import io.github.paul1365972.cursey.curse.impl.NauseaCurse
import io.github.paul1365972.cursey.state.CURSE_SERIALIZER_MODULE
import io.github.paul1365972.cursey.state.PlayerData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerializersModule
import java.util.*

fun main() {
    val json = Json(JsonConfiguration.Stable, context = SerializersModule {
        include(CURSE_SERIALIZER_MODULE)
    })

    val data = PlayerData(lastHeight = 3.14)
    val uuid = UUID.randomUUID();
    data.curses[uuid] = NauseaCurse(uuid, null, 42)

    println(data.curses.toString())

    println(json.stringify(PlayerData.serializer(), data))
}