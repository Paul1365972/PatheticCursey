package io.github.paul1365972.cursey.curse

import io.github.paul1365972.cursey.state.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*
import java.util.stream.Stream

typealias CurseMap = MutableMap<@Serializable(with = UUIDSerializer::class) UUID, Curse>

inline fun <reified T : Curse> CurseMap.streamValuesOfType(): Stream<T> {
    return values.stream().filter { curse -> curse is T }.map { curse -> curse as T }
}
