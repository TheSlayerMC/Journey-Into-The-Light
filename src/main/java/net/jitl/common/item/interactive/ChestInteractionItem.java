package net.jitl.common.item.interactive;

import net.jitl.common.block.base.JChestBlock;
import net.jitl.core.init.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

public class ChestInteractionItem extends Item {

    public ChestInteractionItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(pos);

        if(blockstate.getBlock() instanceof JChestBlock && blockstate.getValue(JChestBlock.TYPE) == ChestType.SINGLE) {
            if(heldItem.getItem() == JItems.CHEST_KEY) {
                if(blockstate.getValue(JChestBlock.IS_LOCKED)) {
                    BlockState s = blockstate.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE);
                    world.setBlock(pos, s, 2);
                    heldItem.shrink(1);
                    world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
            if(heldItem.getItem() == JItems.PADLOCK) {
                if(!blockstate.getValue(JChestBlock.IS_LOCKED)){
                    BlockState s = blockstate.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE);
                    world.setBlock(pos, s, 2);
                    heldItem.shrink(1);
                    world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}