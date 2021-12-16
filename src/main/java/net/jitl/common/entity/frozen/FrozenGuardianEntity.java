package net.jitl.common.entity.frozen;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

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
            final World world = this.level;
            final BlockPos entityPos = new BlockPos(this.position());
            if(!level.isClientSide) {
            for (int x = -check_radius; x <= check_radius; x++) {
                for (int z = -check_radius; z <= check_radius; z++) {
                    for (int y = -check_radius; y <= check_radius; y++) {
                        final BlockPos pos = entityPos.offset(x, y, z);
                        final Block block = world.getBlockState(pos).getBlock();
                        if (block == JBlocks.FROZEN_PEDISTAL) {
                            EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);//Fix blue lightning not rendering
                            bolt.setPos(pos.getX(), pos.getY() + 1.2, pos.getZ());
                            bolt.setARGB(0x5acbff);
                            bolt.setVisualOnly(true);
                            this.level.addFreshEntity(bolt);
                        }
                    }
                }
            }
        }
        return super.mobInteract(playerEntity_, hand_);
    }
}
