package net.journey.items.ranged;

import net.journey.JITL;
import net.journey.api.block.FeatureProvider;
import net.journey.blocks.util.Features;
import net.journey.entity.projectile.EntityDamagingProjectile;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemCreativeHammer extends ItemSword implements FeatureProvider {

    protected int use;
    protected int dam;
    protected boolean unbreakable;
    protected Class<? extends EntityDamagingProjectile> projectile;
    protected JourneyToolMaterial mat;

    public ItemCreativeHammer(JourneyToolMaterial toolMaterial, boolean unbreakable, Class<? extends EntityLightningBall> projectile) {
        super(toolMaterial.getToolMaterial());
        this.projectile = projectile;
        this.unbreakable = unbreakable;
        setMaxStackSize(1);
        mat = toolMaterial;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        JourneySounds.playSound(JourneySounds.PLASMA, world, player);
        if (!unbreakable) stack.damageItem(1, player);
        try {
            world.spawnEntity(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, dam));
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        } catch (Exception e) {
            e.printStackTrace();
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
    public void addInformation(ItemStack item, World worldIn, List<String> infoList, ITooltipFlag flagIn) {
        if (item.getMaxDamage() != -1)
            infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
        else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
        infoList.add(SlayerAPI.Colour.YELLOW + "Creative Only");
    }

    @Override
    public @NotNull Features getExtraFeatures() {
        return Features.Builder.create()
                .setCustomItemModelLocation(JITL.rl("hammer/" + getRegistryName().getPath()))
                .build();
    }
}