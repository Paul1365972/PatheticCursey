package io.github.paul1365972.cursey.curse

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import java.util.*

@Serializable
abstract class AbstractDurationCurse : AbstractCurse {

    private var duration: Int
    private var tick: Int

    constructor(uuid: UUID, player: Player, duration: Int, tick: Int = 0) : super(uuid, player) {
        this.duration = duration
        this.tick = tick
    }

    override fun tick(): Boolean {
        if (tick++ == duration) {
            trigger()
            return true
        }
        return false
    }

    abstract fun trigger()

}