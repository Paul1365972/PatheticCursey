package io.github.paul1365972.cursey.state

import io.github.paul1365972.cursey.Cursey
import io.github.paul1365972.cursey.DeeperWorldInterface
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerializersModule
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class PlayerDataManager(private val cursey: Cursey) {

    private val json: Json = Json(JsonConfiguration.Stable, context = SerializersModule {
       include(CURSE_SERIALIZER_MODULE)
    })

    private val playerDataKey: NamespacedKey = NamespacedKey(cursey, "PlayerData")

    private val playerDataMap: MutableMap<UUID, PlayerData> = HashMap()

    fun shutdown() {
        cursey.server.onlinePlayers.forEach { entity: Player -> save(entity) }
    }

    fun load(entity: Player): PlayerData {
        val container = entity.persistentDataContainer
        val jsonString = container.get(playerDataKey, PersistentDataType.STRING)
                ?: return PlayerData(lastHeight = DeeperWorldInterface.getTrueHeight(entity.location))
        return json.parse(PlayerData.serializer(), jsonString)
    }

    @JvmOverloads
    fun save(entity: Player, data: PlayerData = get(entity)) {
        val container = entity.persistentDataContainer
        val json = json.stringify(PlayerData.serializer(), data)
        container.set(playerDataKey, PersistentDataType.STRING, json)
    }

    operator fun get(entity: Player): PlayerData {
        return playerDataMap.computeIfAbsent(entity.uniqueId) { load(entity) }
    }
}