package io.github.paul1365972.cursey.curse

import io.github.paul1365972.cursey.state.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.bukkit.entity.Player
import java.util.*

@Serializable
abstract class AbstractCurse constructor(
        @Serializable(with = UUIDSerializer::class)
        final override val uuid: UUID,
        @Transient
        final override val player: Player? = null
) : Curse {

    override fun onStart(curses: CurseMap) {
        curses[uuid] = this
    }
    override fun onFinish() {}
    override fun onPause() {}
    override fun onResume() {}
}