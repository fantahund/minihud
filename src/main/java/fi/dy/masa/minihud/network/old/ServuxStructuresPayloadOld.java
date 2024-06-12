package fi.dy.masa.minihud.network.old;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

/**
 * ServuX Structures Data Provider; the actual Payload shared between MiniHUD and ServuX
 */
@Deprecated
public record ServuxStructuresPayloadOld(NbtCompound data) implements CustomPayload
{
    public static final Id<ServuxStructuresPayloadOld> TYPE = new Id<>(ServuxStructuresHandlerOld.CHANNEL_ID);
    public static final PacketCodec<PacketByteBuf, ServuxStructuresPayloadOld> CODEC = CustomPayload.codecOf(ServuxStructuresPayloadOld::write, ServuxStructuresPayloadOld::new);

    public ServuxStructuresPayloadOld(PacketByteBuf buf) { this(buf.readNbt()); }

    private void write(PacketByteBuf buf) { buf.writeNbt(data); }

    @Override
    public Id<ServuxStructuresPayloadOld> getId() { return TYPE; }
}
