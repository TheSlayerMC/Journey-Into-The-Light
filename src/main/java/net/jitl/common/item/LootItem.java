package net.jitl.common.item;

import net.jitl.core.JITL;
import net.jitl.core.init.JSounds;
import net.jitl.core.init.JTabs;
import net.jitl.core.util.LootHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LootItem extends Item {

    private final ResourceLocation lootTable;
    private final boolean hasEffect;

    public LootItem(ResourceLocation loot, boolean hasEffect) {
        super(new Properties().tab(JTabs.MISC));
        this.lootTable = loot;
        this.hasEffect = hasEffect;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        if (!worldIn.isClientSide) {
            ServerPlayer playerMP = (ServerPlayer) playerIn;
            List<ItemStack> lootTable = LootHelper.genFromLootTable(this.lootTable, playerMP, builder -> builder.withLuck(playerMP.getLuck()));
            for (ItemStack itemToSpawn : lootTable) {
                ItemEntity item = new ItemEntity(worldIn, playerMP.getX(), playerMP.getY(), playerMP.getZ(), itemToSpawn);
                worldIn.addFreshEntity(item);
            }
            playerMP.getItemInHand(handIn).shrink(1);
            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.LOOT.get(), SoundSource.NEUTRAL, 0.75F, Mth.nextFloat(playerMP.getRandom(), 0.75F, 1.25F));
        }
        JITL.LOGGER.info(this.lootTable.toString());
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(playerIn.getItemInHand(handIn), worldIn.isClientSide());
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
        return hasEffect ? Rarity.RARE : Rarity.COMMON;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return this.hasEffect;
    }
}
