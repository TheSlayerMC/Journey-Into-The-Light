package net.journey.items.ranged;

import net.journey.api.capability.EssenceStorage;
import net.journey.api.item.IUsesEssence;
import net.journey.common.capability.JCapabilityManager;
import net.journey.entity.projectile.EntityDamagingProjectile;
import net.journey.init.JourneySounds;
import net.journey.items.base.JItem;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemStaff extends JItem implements IUsesEssence {

	protected final int usage;
	protected int damage;
	protected boolean unBreakable;
	protected Class<? extends EntityDamagingProjectile> projectile;

	public ItemStaff(int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityDamagingProjectile> projectile) {
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		this.unBreakable = unbreakable;
		setMaxDamage(uses);
		setMaxStackSize(1);
		setFull3D();
	}

	public static float getMagicVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();
        if (mana.useEssence(usage)) {
            JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, player);
            if (!world.isRemote) {
                if (!unBreakable)
                    stack.damageItem(1, player);
                try {
	                EntityDamagingProjectile shoot = projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage);
                    shoot.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.2F);
                    world.spawnEntity(shoot);
                    stack.damageItem(1, player);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.DARK_GREEN + LangHelper.rangedDamage(damage));
        LangHelper.useEssence(usage);
        if (unBreakable) LangHelper.getInfiniteUses();
        else l.add(i.getMaxDamage() - i.getItemDamage() + " " + LangHelper.getUsesRemaining());
    }
}