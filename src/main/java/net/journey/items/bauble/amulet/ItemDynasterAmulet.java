package net.journey.items.bauble.amulet;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.capability.JCapabilityManager;
import net.journey.items.bauble.ItemBaubleBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemDynasterAmulet extends ItemBaubleBase implements IBauble {

    public ItemDynasterAmulet() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.world.isRemote) {
            if (player.motionY < 0.0D && !player.onGround && !player.isInWater() && !player.isInLava() && player.isSneaking()) {
                JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer((EntityPlayer) player);
                EssenceStorage mana = journeyPlayer.getEssenceStorage();
                if (mana.useEssence(1)) {
                    if (player.world.getBlockState(player.getPosition().down(5)).isBlockNormalCube()) {
                        player.motionY *= 0.75F;
                        player.fallDistance = -1.0F;
                    }
                }
                journeyPlayer.sendUpdates();
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(TextFormatting.YELLOW + "Crouch within 5 block of a surface to negate fall damage");
        l.add(TextFormatting.RED + "" + TextFormatting.ITALIC + "Rapidly consumes Essence bar while being used.");
        l.add(TextFormatting.RED + "" + TextFormatting.ITALIC + "If the Essence bar is empty, the effect can no longer be used.");
    }
}
