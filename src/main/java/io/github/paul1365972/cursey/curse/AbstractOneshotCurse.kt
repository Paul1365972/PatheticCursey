package io.github.paul1365972.cursey.curse

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import java.util.*

@Serializable
abstract class AbstractOneshotCurse : AbstractCurse {

    constructor(uuid: UUID, player: Player?) : super(uuid, player)

    override fun tick(): Boolean {
        trigger()
        return true
    }

    abstract fun trigger()
}