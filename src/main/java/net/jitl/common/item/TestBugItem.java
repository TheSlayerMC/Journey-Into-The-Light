package net.jitl.common.item;

import net.jitl.common.entity.base.BossCrystalEntity;
import net.jitl.init.JItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //ConfiguredFeature<BlockStateFeatureConfig, ?> configuredfeature = JFeatures.TORRID_CRYSTAL.get().configured(new BlockStateFeatureConfig(JBlocks.TORRID_CRYSTAL.defaultBlockState()));
        if (!worldIn.isClientSide()) {
            List<ItemStack> loot = new ArrayList<>();
            loot.add(new ItemStack(JItems.LUNIUM_POWDER, 5));
            loot.add(new ItemStack(Items.DIAMOND, 5));
            loot.add(new ItemStack(JItems.BLOOD, 5));
            BossCrystalEntity.create(worldIn, playerIn.position(), BossCrystalEntity.Type.CORBA, loot);
            return ActionResult.success(playerIn.getItemInHand(handIn));
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }


    public boolean isInMainHand(PlayerEntity player) {
        return player.getItemInHand(Hand.MAIN_HAND).getItem() == this;
    }
}
