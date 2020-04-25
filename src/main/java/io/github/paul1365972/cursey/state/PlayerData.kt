package io.github.paul1365972.cursey.state

import io.github.paul1365972.cursey.curse.CurseMap
import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
        var curseEnabled: Boolean = true,
        var lastHeight: Double,
        var ascended: Double = 0.0,
        var curses: CurseMap = mutableMapOf()
)
