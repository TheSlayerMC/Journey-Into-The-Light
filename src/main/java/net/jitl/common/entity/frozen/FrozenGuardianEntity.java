package net.jitl.common.entity.frozen;

import net.jitl.common.block.tileentity.PedestalTile;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        int check_radius = 8;
        int countedFullPedestals = 0;
        final World world = this.level;
        final BlockPos entityPos = new BlockPos(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.FROZEN_PEDISTAL) {
                        PedestalTile tile = (PedestalTile)world.getBlockEntity(pos);
                        EssenciaBoltEntity bolt = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, level);
                        bolt.setPos(pos.getX(), pos.getY() + 1.2, pos.getZ());
                        bolt.setARGB(0x5acbff);
                        bolt.setVisualOnly(true);
                    //NEED TO GET IT TO CHECK IF THE PEDESTAL HAS THE ITEM AND IF IT DOES COUNT + 1, AND IF IT REACHES ALL 8
                        // KILL THE ENTITY AND SPAWN THE ITEM AND DESTROY THE ITEMS ON THE PEDESTAL
                        if(tile.getItem(0) == new ItemStack(JItems.SAPPHIRE)) {
                            countedFullPedestals++;
                            System.out.println(pos);
                            if(!world.isClientSide) {
                                this.level.addFreshEntity(bolt);
                            }
                        }
                    }
                }
            }
        }
        if(countedFullPedestals == 8) {
           // this.level.addFreshEntity(new ItemEntity(level, this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.STAFF_OF_CONJURING)));
           // this.kill();
        }
        countedFullPedestals = 0;
        return super.mobInteract(playerEntity, hand);
    }
}
