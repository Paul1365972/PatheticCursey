package io.github.paul1365972.cursey.curse

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import java.util.*

/**
 * Interface for all curses
 *
 * @property uuid UUID of the curse
 * @property player Player affected by the curse
 */
interface Curse {
    val uuid: UUID
    val player: Player?

    /**
     * Called every game tick while curse is active
     */
    fun tick(): Boolean

    /**
     * Called when the curse is started
     *
     * @param curses Current curses of the player.
     * In most cases you only want to add this curse to the list,
     * but you could also remove all curses of the same type, to prevent curses from stacking
     */
    fun onStart(curses: CurseMap)

    /**
     * Called when the curse is paused (server shutdown, player leaves)
     */
    fun onPause()

    /**
     * Called when the curse is resumed (server startup, player joins)
     */
    fun onResume()

    /**
     * Called when the curse finishes
     */
    fun onFinish()
}