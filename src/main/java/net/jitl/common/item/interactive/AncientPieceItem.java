package net.jitl.common.item.interactive;

import net.jitl.common.block.AncientSocketBlock;
import net.jitl.init.JBlocks;
import net.jitl.util.JItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class AncientPieceItem extends Item {

    public AncientPieceItem() {
        super(JItemProperties.itemGrouped());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(pos);
        Random itemRand = new Random();
        if(blockstate.is(JBlocks.ANCIENT_SOCKET) && !blockstate.getValue(AncientSocketBlock.INSERTED)) {
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
                world.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
