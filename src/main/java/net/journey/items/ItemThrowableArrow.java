package net.journey.items;

import net.journey.client.ItemDescription;
import net.journey.init.JourneyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

import java.util.List;

public class ItemThrowableArrow extends ItemMod {

    String string;
    private Class<? extends EntityTippedArrow> entity;

    public ItemThrowableArrow(String name, String f, Class<? extends EntityTippedArrow> entity, String description) {
        super(name, f);
        this.entity = entity;
        this.string = description;
        setCreativeTab(JourneyTabs.weapons);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        try {
            if (!world.isRemote) {
                EntityTippedArrow t = entity.getConstructor(World.class, EntityLivingBase.class).newInstance(world, player);
                t.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
                world.spawnEntity(t);
                if (!player.capabilities.isCreativeMode) stack.shrink(1);
                world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        ItemDescription.addInformation(i, l);
        l.add(SlayerAPI.Colour.GOLD + "5 Ranged Damage");
        l.add(SlayerAPI.Colour.RED + "On hit: " + string);
    }
}