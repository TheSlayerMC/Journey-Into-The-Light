package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemGlowshroom extends ItemFood {
	
	private boolean op;
	
    public ItemGlowshroom(String name, String actual, int heal, float f, boolean sat, boolean b) {
        super(heal, sat);
        LangRegistry.addItem(name, actual);
        setUnlocalizedName(name);
        GameRegistry.registerItem(this, name);
        JourneyItems.itemNames.add(name);
        setCreativeTab(JourneyTabs.crops);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack i) {
        return op;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, EntityPlayer p, List list, boolean par4) {
		list.add(SlayerAPI.Colour.GREEN + "Grants player nightvision when eaten");
    	
    }
    @Override
    protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
        if(!w.isRemote) {
            p.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2400, 0));
            }
            super.onFoodEaten(i, w, p);
        }

    }