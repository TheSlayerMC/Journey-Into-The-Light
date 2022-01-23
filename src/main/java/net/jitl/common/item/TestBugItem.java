package net.jitl.common.item;

import net.jitl.common.item.interactive.LoreScrollItem;
import net.jitl.core.init.JItems;
import net.jitl.core.init.client.ScrollEntries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        //ConfiguredFeature<BlockStateFeatureConfig, ?> configuredfeature = JFeatures.TORRID_CRYSTAL.get().configured(new BlockStateFeatureConfig(JBlocks.TORRID_CRYSTAL.defaultBlockState()));
        if (!worldIn.isClientSide()) {
            ItemStack scrollStack = new ItemStack(JItems.LORE_SCROLL);
            LoreScrollItem.bindScrollEntry(scrollStack, ScrollEntries.SENTERIAN_GOSPEL);
            playerIn.addItem(scrollStack);
           /* List<ItemStack> loot = new ArrayList<>();
            loot.add(new ItemStack(JItems.LUNIUM_POWDER, 5));
            loot.add(new ItemStack(Items.DIAMOND, 5));
            loot.add(new ItemStack(JItems.BLOOD, 5));
            BossCrystalEntity.create(worldIn, playerIn.position(), BossCrystalEntity.Type.CORBA, loot);
            return InteractionResultHolder.success(playerIn.getItemInHand(handIn));*/
        }
        return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
    }


    public boolean isInMainHand(Player player) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this;
    }
}
