package fi.dy.masa.minihud.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import fi.dy.masa.minihud.util.DataStorage;

@Mixin(ServerWorld.class)
public class MixinServerWorld
{
    @Shadow private int spawnChunkRadius;

    @Inject(method = "setSpawnPos", at = @At("TAIL"))
    private void minihud_checkSpawnPos(BlockPos pos, float angle, CallbackInfo ci)
    {
        int radius = (this.spawnChunkRadius - 1);

        if (DataStorage.getInstance().getWorldSpawn().equals(pos) == false)
        {
            DataStorage.getInstance().setWorldSpawn(pos);
        }

        if (DataStorage.getInstance().isSpawnChunkRadiusKnown() == false || DataStorage.getInstance().getSpawnChunkRadius() != radius)
        {
            DataStorage.getInstance().setSpawnChunkRadius(radius);
        }
    }
}
