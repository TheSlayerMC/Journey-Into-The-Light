package net.journey.items.bauble.amulet;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.capability.JCapabilityManager;
import net.journey.items.base.JItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemDynasterAmulet extends JItem implements IBauble {

    public ItemDynasterAmulet() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.motionY < 0.0D && !player.onGround && !player.isInWater() && !player.isInLava() && player.isSneaking()) {
            if (player.world.getBlockState(player.getPosition().down()).isBlockNormalCube()) {
                JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer((EntityPlayer) player);
                EssenceStorage mana = journeyPlayer.getEssenceStorage();
                if (mana.useEssence(1)) {
                    player.motionY *= 0.75F;
                    player.fallDistance = -1.0F;
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.YELLOW + "Crouch within 5 block of a surface to negate fall damage");
    }
}
