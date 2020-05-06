package net.journey.init;

import net.journey.JITL;
import net.journey.enchantment.EnchantmentHotTouch;
import net.journey.enchantment.EnchantmentWaterWalk;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = JITL.MOD_ID)
public class JourneyEnchantments {

    public static final Enchantment hotTouch = new EnchantmentHotTouch();
    public static final Enchantment waterWalk = new EnchantmentWaterWalk();

    public static int getItemEnchantment(Enchantment en, EntityLivingBase e) {
        if (en != null && e != null) return EnchantmentHelper.getEnchantmentLevel(en, e.getHeldItemMainhand());
        else return 0;
    }

    public static boolean hasItemEnchantment(Enchantment en, EntityLivingBase e) {
        return getItemEnchantment(en, e) > 0;
    }

    public static int getArmorEnchantment(Enchantment en, EntityLivingBase e) {
        if (en != null && e != null) return EnchantmentHelper.getEnchantmentLevel(en, e.getHeldItemMainhand());
        else return 0;
    }

    public static boolean hasArmorEnchantment(Enchantment en, EntityLivingBase e) {
        return getArmorEnchantment(en, e) > 0;
    }

    @SubscribeEvent
    public static void onBlockHarvested(HarvestDropsEvent event) {
        EntityPlayer p = event.getHarvester();
        if (hasItemEnchantment(hotTouch, p)) {
            if (event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null) {
                if (!event.isSilkTouching()) {
                    ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
                    if (stack != null && event.getState().getBlock() != Blocks.REDSTONE_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE) {
                        event.getDrops().clear();
                        event.getDrops().add(stack.copy());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        int i = MathHelper.floor(player.posX);
        int j = MathHelper.floor(player.posY);
        int k = MathHelper.floor(player.posZ);

        if (hasArmorEnchantment(waterWalk, player) && player.world.getBlockState(new BlockPos(i, j, k)).getMaterial() == Material.WATER && player.motionY < 0) {
            player.motionY = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.5D : 0;
        }
    }
}
