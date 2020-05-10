package net.journey.items.interactive;

import net.journey.JITL;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemStorage extends Item {

    protected int healAmount = 0;

    public ItemStorage(String name, String finalName) {
        this(name, finalName, JourneyTabs.ITEMS);
    }

    public ItemStorage(String name, String finalName, CreativeTabs tab) {
        setTranslationKey(name);
        setCreativeTab(tab);
        JourneyItems.items.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addItemEntry(this, finalName);
    }

    public ItemStorage setHealAmount(int healAmount) {
        this.healAmount = healAmount;
        return this;
    }

    public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, SoundEvent sound, boolean damage, ItemStack item, int dam) {
        if (!w.isRemote) {
            if (magic) w.spawnEntity(entity);
        }
        if (magic) {
            JourneySounds.playSound(sound, w, p);
            if (damage) item.damageItem(dam, p);
        }
    }

    public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, SoundEvent sound, boolean damage, ItemStack item, int dam) {
        if (!w.isRemote) {
            w.spawnEntity(entity);
            JourneySounds.playSound(sound, w, p);
            if (damage) item.damageItem(dam, p);
        }
    }

    public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, SoundEvent sound) {
        spawnEntityIntoWorld(w, p, entity, magic, sound, false, new ItemStack(Items.APPLE), 0);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List list) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        addInformation(stack, tooltip);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        JourneySounds.playSound(JourneySounds.CHEST_OPEN, world, player);
        if (world.isRemote) player.displayGUIChest(null);
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}