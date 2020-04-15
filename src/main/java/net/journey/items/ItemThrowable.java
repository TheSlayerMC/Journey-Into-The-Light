package net.journey.items;

import net.journey.client.ItemDescription;
import net.journey.init.JourneyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.item.ItemMod;

import java.util.List;

public class ItemThrowable extends ItemMod {

    private Class<? extends EntityThrowable> entity;
    private float damage;

    public ItemThrowable(String name, String f, float damage, Class<? extends EntityThrowable> entity) {
        super(name, f);
        this.damage = damage;
        this.entity = entity;
        setCreativeTab(JourneyTabs.WEAPONS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        try {
            if (!world.isRemote) {
                EntityThrowable t = entity.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage);
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
        l.add(damage + " Ranged Damage");
    }
}