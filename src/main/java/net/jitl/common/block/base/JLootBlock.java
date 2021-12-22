package net.jitl.common.block.base;

import net.jitl.util.LootHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
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
    public void playerDestroy(World worldIn, @NotNull PlayerEntity player, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!worldIn.isClientSide) {
            ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
            List<ItemStack> lootTable = LootHelper.genFromLootTable(this.lootTable, playerMP, builder -> builder.withLuck(playerMP.getLuck()));
            for (ItemStack itemToSpawn : lootTable) {
                ItemEntity item = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemToSpawn);
                worldIn.addFreshEntity(item);
            }
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GLASS_BREAK, SoundCategory.NEUTRAL, 0.75F, MathHelper.nextFloat(RANDOM, 0.55F, 0.75F));
        }
    }
}
