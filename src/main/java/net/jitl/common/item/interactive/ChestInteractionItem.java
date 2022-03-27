package net.jitl.common.item.interactive;

import net.jitl.common.block.base.JChestBlock;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

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

        if(blockstate.getBlock() instanceof JChestBlock) {
            //Sets the chest key to be the universal key
            unlockChest(player, JItems.CHEST_KEY, world, pos, JBlocks.BOIL_CHEST, JBlocks.EUCA_CHEST, JBlocks.FROZEN_CHEST, JBlocks.NETHER_CHEST, JBlocks.JOURNEY_CHEST);

            //Sets specific keys to only open their respective chests
            unlockChest(player, JItems.BOILING_KEY, world, pos, JBlocks.BOIL_CHEST);
            unlockChest(player, JItems.EUCA_KEY, world, pos, JBlocks.EUCA_CHEST);
            unlockChest(player, JItems.FROZEN_KEY, world, pos, JBlocks.FROZEN_CHEST);
            unlockChest(player, JItems.NETHER_KEY, world, pos, JBlocks.NETHER_CHEST);
            unlockChest(player, JItems.JOURNEY_KEY, world, pos, JBlocks.JOURNEY_CHEST);

            //Locks the chest if the player wishes to do so
            if(heldItem.getItem() == JItems.PADLOCK) {
                lockChest(player, world, pos);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    public void lockChest(Player player, Level world, BlockPos pos) {
        BlockState clickedChest = world.getBlockState(pos);
        Block chest = clickedChest.getBlock();
        if(world.getBlockState(pos).getBlock() == chest) {
            BlockState n = world.getBlockState(pos.north());
            BlockState s = world.getBlockState(pos.south());
            BlockState e = world.getBlockState(pos.east());
            BlockState w = world.getBlockState(pos.west());
            boolean isNorth = n.getBlock() == chest;
            boolean isSouth = s.getBlock() == chest;
            boolean isEast = e.getBlock() == chest;
            boolean isWest = w.getBlock() == chest;
            if(!clickedChest.getValue(JChestBlock.IS_LOCKED)) {
                world.setBlock(pos, clickedChest.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2); //Locks the clicked chest

                //Checks if double chest is adjacent
                if(isNorth) {
                    world.setBlock(pos.north(), n.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isSouth) {
                    world.setBlock(pos.south(), s.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isEast) {
                    world.setBlock(pos.east(), e.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                if(isWest) {
                    world.setBlock(pos.west(), w.setValue(JChestBlock.IS_LOCKED, Boolean.TRUE), 2);
                }
                player.getMainHandItem().shrink(1);
                world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public void unlockChest(Player player, Item key, Level world, BlockPos pos, Block...c) {
        for(Block chest : c) {
            if (player.getMainHandItem().getItem() == key && world.getBlockState(pos).getBlock() == chest) {
                BlockState n = world.getBlockState(pos.north());
                BlockState s = world.getBlockState(pos.south());
                BlockState e = world.getBlockState(pos.east());
                BlockState w = world.getBlockState(pos.west());
                boolean isNorth = n.getBlock() == chest;
                boolean isSouth = s.getBlock() == chest;
                boolean isEast = e.getBlock() == chest;
                boolean isWest = w.getBlock() == chest;
                BlockState clickedChest = world.getBlockState(pos);
                if (clickedChest.getValue(JChestBlock.IS_LOCKED)) {
                    world.setBlock(pos, clickedChest.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2); //Locks the clicked chest

                    //Checks if double chest is adjacent
                    if (isNorth) {
                        world.setBlock(pos.north(), n.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if (isSouth) {
                        world.setBlock(pos.south(), s.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if (isEast) {
                        world.setBlock(pos.east(), e.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    if (isWest) {
                        world.setBlock(pos.west(), w.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE), 2);
                    }
                    player.getMainHandItem().shrink(1);
                    world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    public void checkSpecificKeyToChest(Player player, Item key, Block chest, BlockState blockstate, Level world, BlockPos pos) {
        if(player.getMainHandItem().getItem() == key && blockstate.getBlock() == chest) {
            if(blockstate.getValue(JChestBlock.IS_LOCKED)) {
                BlockState s = blockstate.setValue(JChestBlock.IS_LOCKED, Boolean.FALSE);
                world.setBlock(pos, s, 2);
                player.getMainHandItem().shrink(1);
                world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> text, @NotNull TooltipFlag flag) {
        if(stack.getItem() == JItems.CHEST_KEY) {
            text.add(new TranslatableComponent("jitl.key.chest.desc"));
        }
        if(stack.getItem() == JItems.BOILING_KEY) {
            text.add(new TranslatableComponent("jitl.key.boiling.desc"));
        }
        if(stack.getItem() == JItems.EUCA_KEY) {
            text.add(new TranslatableComponent("jitl.key.euca.desc"));
        }
        if(stack.getItem() == JItems.FROZEN_KEY) {
            text.add(new TranslatableComponent("jitl.key.frozen.desc"));
        }
        if(stack.getItem() == JItems.NETHER_KEY) {
            text.add(new TranslatableComponent("jitl.key.nether.desc"));
        }
        if(stack.getItem() == JItems.PADLOCK) {
            text.add(new TranslatableComponent("jitl.padlock.desc"));
        }
    }
}