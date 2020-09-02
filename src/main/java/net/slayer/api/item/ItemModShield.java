package net.slayer.api.item;

import net.journey.api.capability.EssenceStorage;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.JourneySounds;
import net.journey.items.base.JItem;
import net.journey.util.LangHelper;
import net.journey.util.PotionEffects;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemModShield extends JItem {

	protected int usage;
	protected boolean unBreakable;

	public ItemModShield(String name, String finalName, int manaUsed, boolean unbreakable) {
		super(name, finalName);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxDamage(336);
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F
                        : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        LangHelper.useEssence(usage);
        if (unBreakable) LangHelper.getInfiniteUses();
        else l.add(i.getMaxDamage() - i.getItemDamage() + " " + LangHelper.getUsesRemaining());
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        if (!world.isRemote && mana.useEssence(usage)) {
            JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, player);
            if (!unBreakable) itemstack.damageItem(1, player);
            try {
                player.addPotionEffect(new PotionEffects().setPotionEffect(PotionEffects.fireResistance, 240, 0));
                itemstack.damageItem(1, player);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.BLAZE_ROD || super.getIsRepairable(toRepair, repair);
    }
}
