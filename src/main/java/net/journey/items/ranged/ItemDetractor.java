package net.journey.items.ranged;

import net.journey.api.capability.EssenceStorage;
import net.journey.common.capability.JCapabilityManager;
import net.journey.entity.projectile.EntityAttractor;
import net.journey.entity.projectile.EntityDetractor;
import net.journey.init.JourneySounds;
import net.journey.items.base.JItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.Random;

public class ItemDetractor extends JItem {

    public boolean attracts;
    public boolean detracts;
    private final int magic;

    public ItemDetractor(int magic, boolean attracts, boolean detracts) {
        setMaxStackSize(1);
        this.magic = magic;
        this.attracts = attracts;
        this.detracts = detracts;
        this.setFull3D();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        Random r = new Random();
        EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();

        if (detracts) {
            if (!world.isRemote && mana.useEssence(magic)) {
                JourneySounds.playSound(JourneySounds.HAMMER, world, player);
                EntityThrowable entity = new EntityDetractor(world, player);
                world.spawnEntity(entity);
            }
        }
        if (attracts) {
            if (!world.isRemote && mana.useEssence(magic)) {
                JourneySounds.playSound(JourneySounds.HAMMER, world, player);
                EntityThrowable entity = new EntityAttractor(world, player);
                world.spawnEntity(entity);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        if (detracts) {
            l.add(SlayerAPI.Colour.DARK_GREEN + "Uses " + magic + " Essence");
            l.add(SlayerAPI.Colour.AQUA + "Fires a mob away from you");
        }
        if (attracts) {
            l.add(SlayerAPI.Colour.DARK_GREEN + "Uses " + magic + " Essence");
            l.add(SlayerAPI.Colour.AQUA + "Pulls a mob towards you");
        }
    }
}