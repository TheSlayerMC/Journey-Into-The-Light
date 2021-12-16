package net.jitl.common.entity.frozen;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.jitl.init.JSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class FrozenGuardianEntity extends CreatureEntity {

    public FrozenGuardianEntity(EntityType<? extends FrozenGuardianEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {

    }
    public static boolean canSpawn(EntityType<? extends CreatureEntity> entityType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return false;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity playerEntity_, Hand hand_) {
        int check_radius = 8;
        if(!this.level.isClientSide) {
            final World world = this.level;
            final BlockPos entityPos = new BlockPos(this.position());

            for (int x = -check_radius; x <= check_radius; x++) {
                for (int z = -check_radius; z <= check_radius; z++) {
                    if (x == 0 && z == 0) {
                        continue;
                    }

                    final BlockPos pos = entityPos.offset(x, 0, z);
                    final Block block = world.getBlockState(pos).getBlock();

                    if (block == JBlocks.FROZEN_PEDISTAL) {
                        System.out.println("PEDISTAL LOCATED" + " X:" + x + " Z:" + z);
                        ItemEntity item = new ItemEntity(level, x, this.position().y + 1, z, new ItemStack(Items.APPLE));
                        this.level.addFreshEntity(item);
                    }
                }
            }
        }
        return super.mobInteract(playerEntity_, hand_);
    }
}
