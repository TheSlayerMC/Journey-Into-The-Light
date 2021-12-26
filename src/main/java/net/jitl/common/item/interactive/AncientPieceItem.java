package net.jitl.common.item.interactive;

import net.jitl.common.block.AncientSocketBlock;
import net.jitl.init.JBlocks;
import net.jitl.util.JItemProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class AncientPieceItem extends Item {

    public AncientPieceItem() {
        super(JItemProperties.itemGrouped());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(pos);
        Random itemRand = new Random();
        if(blockstate.is(JBlocks.ANCIENT_SOCKET) && !blockstate.getValue(AncientSocketBlock.INSERTED)) {
            if (world.isClientSide) {
                return ActionResultType.SUCCESS;
            } else {
                System.out.println("YES");
                BlockState blockstate1 = blockstate.setValue(AncientSocketBlock.INSERTED, Boolean.TRUE);
                world.setBlock(pos, blockstate1, 2);
                heldItem.shrink(1);
                for (int i = 0; i < 16; ++i) {
                    double d0 = (float) pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d1 = (float) pos.getY() + 0.8125F;
                    double d2 = (float) pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F;
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;
                    world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
                world.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
        return ActionResultType.CONSUME;
    }
}
