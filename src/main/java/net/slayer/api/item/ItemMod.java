package net.slayer.api.item;

import net.journey.JITL;
import net.journey.client.ItemDescription;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemMod extends Item {

    protected int healAmount = 0;
    protected String name;

    public ItemMod(String name, String finalName) {
        this(name, finalName, JourneyTabs.ITEMS);
        this.name = name;
    }

    public ItemMod(String name, String finalName, CreativeTabs tab) {
        this.name = name;

        setTranslationKey(name.toLowerCase());
        setCreativeTab(tab);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name.toLowerCase());
        JourneyItems.items.add(this);
        setRegistryName(JITL.MOD_ID, name.toLowerCase());

        LangGeneratorFacade.addItemEntry(this, finalName);
    }

    public ItemMod setHealAmount(int healAmount) {
        this.healAmount = healAmount;
        return this;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        if (player.getHeldItemMainhand().getItem() instanceof ItemMod) {
            if (healAmount != 0) {
                if (player.getHealth() < player.getMaxHealth()) {
                    player.heal(healAmount);
                    player.getHeldItemMainhand().shrink(1);
                }
            }
        }
        return super.onItemRightClick(worldIn, player, handIn);
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
    public void addInformation(ItemStack stack, World player, List list) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World world, List<String> list, ITooltipFlag flagIn) {
        ItemDescription.addInformation(item, list);
        addInformation(item, world, list);
        addInformation(item, list);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, List l) {
    }
}