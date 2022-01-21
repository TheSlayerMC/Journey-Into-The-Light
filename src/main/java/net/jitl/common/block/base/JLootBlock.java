package net.jitl.common.block.base;

import net.jitl.core.util.LootHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JLootBlock extends Block {
    private final ResourceLocation lootTable;

    public JLootBlock(Properties properties, ResourceLocation loot) {
        super(properties);
        this.lootTable = loot;
    }

    @Override
    public void playerDestroy(Level worldIn, @NotNull Player player, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        if (!worldIn.isClientSide) {
            ServerPlayer playerMP = (ServerPlayer) player;
            List<ItemStack> lootTable = LootHelper.genFromLootTable(this.lootTable, playerMP, builder -> builder.withLuck(playerMP.getLuck()));
            for (ItemStack itemToSpawn : lootTable) {
                ItemEntity item = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemToSpawn);
                worldIn.addFreshEntity(item);
            }
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 0.75F, Mth.nextFloat(RANDOM, 0.55F, 0.75F));
        }
    }
}
