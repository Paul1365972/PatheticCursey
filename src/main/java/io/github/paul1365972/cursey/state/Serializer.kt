package io.github.paul1365972.cursey.state

import io.github.paul1365972.cursey.curse.Curse
import io.github.paul1365972.cursey.curse.impl.NauseaCurse
import kotlinx.serialization.*
import kotlinx.serialization.modules.SerializersModule
import java.util.*

val CURSE_SERIALIZER_MODULE = SerializersModule {
    polymorphic(Curse::class) {
        subclass(NauseaCurse.serializer())
    }
}

@Serializer(forClass = UUID::class)
object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("Data", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }
}
