package io.github.paul1365972.cursey.curse.impl

import io.github.paul1365972.cursey.curse.AbstractOneshotCurse
import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

@Serializable
class NauseaCurse : AbstractOneshotCurse {

    var duration: Int

    constructor(uuid: UUID, player: Player?, duration: Int) : super(uuid, player) {
        this.duration = duration
    }

    override fun trigger() {
        player!!.addPotionEffect(PotionEffect(PotionEffectType.CONFUSION, duration, 0, true, false))
    }
}