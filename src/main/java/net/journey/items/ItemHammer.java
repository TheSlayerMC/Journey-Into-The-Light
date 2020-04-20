package net.journey.items;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemHammer extends ItemSword {

    protected int usage;
    protected int damage;
    protected boolean essence, unbreakable;
    protected Class<? extends EntityBasicProjectile> projectile;
    protected JourneyToolMaterial mat;

    public ItemHammer(String name, String f, JourneyToolMaterial toolMaterial, boolean durability, Class<? extends EntityBasicProjectile> projectile, int dam, int magic, int uses) {
        super(toolMaterial.getToolMaterial());
        this.projectile = projectile;
        damage = dam;
        usage = magic;
        setMaxDamage(uses);
        setMaxStackSize(1);
        LangRegistry.addItem(name, f);
        setTranslationKey(name);
        mat = toolMaterial;
        setCreativeTab(JourneyTabs.WEAPONS);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
        JourneyItems.items.add(this);
        setRegistryName(SlayerAPI.MOD_ID, name);
    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
		if (mana.useEssence(usage)) {
			JourneySounds.playSound(JourneySounds.HAMMER, world, player);
			if (!world.isRemote) {
				if (!unbreakable)
					stack.damageItem(1, player);
				try {
					EntityBasicProjectile shoot = projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage);
					shoot.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0.2F);
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
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack i, ItemStack i1) {
        boolean canRepair = mat.getRepairItem() != null;
        if (canRepair) return mat.getRepairItem() == i1.getItem() || super.getIsRepairable(i, i1);
        return super.getIsRepairable(i, i1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World worldIn, List<String> l, ITooltipFlag flagIn) {
        if (item.getMaxDamage() != -1)
            l.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
        else l.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
        LangHelper.useEssence(usage);
        l.add(SlayerAPI.Colour.DARK_GREEN + "+" + LangHelper.rangedDamage(damage));
    }
}