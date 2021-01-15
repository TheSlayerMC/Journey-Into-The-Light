package net.jitl.common.item;

import net.jitl.init.JTabs;
import net.jitl.util.LootHelper;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LootItem extends Item {

    private final ResourceLocation lootTable;
    private final boolean hasEffect;

    public LootItem(ResourceLocation loot, boolean hasEffect) {
        super(new Item.Properties().tab(JTabs.MISC));
        this.lootTable = loot;
        this.hasEffect = hasEffect;
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {

        if(!worldIn.isClientSide) {
            ServerPlayerEntity playerMP = (ServerPlayerEntity)playerIn;
            List<ItemStack> lootTable = LootHelper.genFromLootTable(this.lootTable, playerMP, builder -> builder.withLuck(playerMP.getLuck()));
            int index = worldIn.random.nextInt(lootTable.size());
            ItemStack itemToSpawn = lootTable.get(index);
            ItemEntity item = new ItemEntity(worldIn, playerMP.getX(), playerMP.getY(), playerMP.getZ(), itemToSpawn);
            worldIn.addFreshEntity(item);
            playerMP.getItemInHand(handIn).shrink(1);
            //worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), JSounds.LOOT.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            //TODO add loot sound
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(playerIn.getItemInHand(handIn), worldIn.isClientSide());
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return hasEffect ? Rarity.RARE : Rarity.COMMON;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.hasEffect;
    }
}
