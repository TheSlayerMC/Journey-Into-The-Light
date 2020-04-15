package net.journey.items;

import java.util.List;
import javax.annotation.Nullable;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.entity.projectile.EntityTippedEssenceArrow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTippedEssenceArrow extends ItemEssenceArrow {
	
	public ItemTippedEssenceArrow(String name, String finalname) {
		super(name, finalname);
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getDefaultInstance() {
		return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), PotionTypes.POISON);
	}

	@Override
	public EntityEssenceArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		EntityTippedEssenceArrow entitytippedarrow = new EntityTippedEssenceArrow(worldIn, shooter, 0);
		entitytippedarrow.setPotionEffect(stack);
		return entitytippedarrow;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (PotionType potiontype : PotionType.REGISTRY) {
				if (!potiontype.getEffects().isEmpty()) {
					items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		PotionUtils.addPotionTooltip(stack, tooltip, 0.125F);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal(PotionUtils.getPotionFromItem(stack).getNamePrefixed("tipped_arrow.effect."));
	}
}