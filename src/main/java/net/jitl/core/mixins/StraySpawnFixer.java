package net.jitl.core.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Stray.class)
public class StraySpawnFixer {

    @Inject(at = @At(value = "RETURN"), method = "checkStraySpawnRules", cancellable = true)
    private static void checkStraySpawnRules(EntityType<Stray> type_, ServerLevelAccessor worldIn, MobSpawnType spawnReason_, BlockPos pos, Random random_, CallbackInfoReturnable<Boolean> cir) {
        boolean canSpawn = Monster.checkMonsterSpawnRules(type_, worldIn, spawnReason_, pos, random_) && (!worldIn.getBlockState(pos).is(Blocks.POWDER_SNOW) || !worldIn.getBlockState(pos.below()).is(Blocks.POWDER_SNOW));
        cir.setReturnValue(canSpawn);
    }
}
