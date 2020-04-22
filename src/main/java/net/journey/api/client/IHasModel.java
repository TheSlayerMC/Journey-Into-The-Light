package net.journey.api.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.slayer.api.SlayerAPI;

public interface IHasModel {

    static void sMRL(Item k, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(k, meta, new ModelResourceLocation(k.getRegistryName(), variant));
    }

    static void sMRL(String statePath, Item k, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(k, meta, new ModelResourceLocation(SlayerAPI.PREFIX + statePath, variant));
    }

    @SideOnly(Side.CLIENT)
    default void registerModels(ModelRegistryEvent e) {
        if (this instanceof Item)
            sMRL("items", (Item) this, 0, "item=" + ((Item) this).getRegistryName().getPath());
        else if (this instanceof Block)
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) this), 0,
                    new ModelResourceLocation(((IForgeRegistryEntry<?>) this).getRegistryName(), "inventory"));
        else throw new IllegalStateException("");
    }
}