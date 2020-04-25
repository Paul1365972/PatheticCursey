package io.github.paul1365972.cursey.curse

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import java.util.*

@Serializable
abstract class AbstractRepeatingCurse : AbstractCurse {

    private var interval: Int
    private var iterations: Int
    private var tick: Int

    constructor(uuid: UUID, player: Player, interval: Int, iterations: Int, tick: Int = 0) : super(uuid, player) {
        this.interval = interval
        this.iterations = iterations
        this.tick = tick
    }

    override fun tick(): Boolean {
        if (iterations == 0) return false
        if (tick++ % interval == 0) {
            trigger()
            iterations--
        }
        return iterations == 0
    }

    abstract fun trigger()

}