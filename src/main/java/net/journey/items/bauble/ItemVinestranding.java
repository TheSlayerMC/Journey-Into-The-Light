package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.items.base.JItem;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemVinestranding extends JItem implements IBauble {

    public ItemVinestranding(String name, String enName) {
        super(name, enName);
        setCreativeTab(JourneyTabs.UTIL);
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.CHARM;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase entityIn) {
        BlockPos pos = new BlockPos(entityIn);
        if (entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).world.getBlockState(pos.down()).getBlock() == JourneyBlocks.taintedMud || ((EntityPlayer) entityIn).world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND) {
            entityIn.motionX *= 1.4F;
            entityIn.motionZ *= 1.4F;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add("Briskly Swing Across Soul Sand and Mud");
    }
}
