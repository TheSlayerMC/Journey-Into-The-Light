package net.jitl.common.entity.euca;

import net.jitl.core.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class EucaHopperEntity extends TamableAnimal {

    public EucaHopperEntity(EntityType<? extends TamableAnimal> entityType_, Level level_) {
        super(entityType_, level_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world_, AgeableMob entity_) {
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canSpawn(EntityType<? extends TamableAnimal> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER) || Objects.equals(worldIn.getBiome(pos).getRegistryName(), JITL.rl("euca_goldite_grains"));
    }
}
