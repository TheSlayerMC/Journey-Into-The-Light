package net.journey.items.ranged;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.launcher.EntityBouncingProjectile;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyWeapons;
import net.journey.items.base.JItem;
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

public class ItemGun extends JItem {

	public int damage;
	public String ability;
	protected Class<? extends EntityBasicProjectile> projectile;

	public ItemGun(String name, String f, int damage, String ability,
	               Class<? extends EntityBasicProjectile> projectile) {
		super(name, f, JourneyTabs.WEAPONS);
		this.ability = ability;
		this.projectile = projectile;
        this.damage = damage;
        setMaxStackSize(1);
        setMaxDamage(500);
        setFull3D();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
        ItemStack stack = player.getHeldItem(handIn);
        if (mana.useEssence(2)) {
            JourneySounds.playSound(JourneySounds.CANNON, world, player);
            if (this == JourneyWeapons.chaosCannon && !world.isRemote) {
                EntityBouncingProjectile bouncing = new EntityBouncingProjectile(world, player, damage, 4);
                bouncing.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.0F);
                world.spawnEntity(bouncing);
                stack.damageItem(1, player);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
            } else if (projectile != null && !world.isRemote) {
                // JourneySounds.playSound(JourneySounds.PLASMA, world, player);
                try {
                    EntityBasicProjectile shoot = projectile
                            .getConstructor(World.class, EntityLivingBase.class, float.class)
                            .newInstance(world, player, damage);
                    shoot.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
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
        l.add("Infinite ammo");
        l.add("Uses 2 Essence");
        l.add(SlayerAPI.Colour.GOLD + "Ability: " + ability);
        l.add(SlayerAPI.Colour.AQUA + "Damage: " + damage + " ranged damage");
        l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
    }
}