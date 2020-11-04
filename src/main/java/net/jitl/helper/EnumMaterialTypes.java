package net.jitl.helper;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum EnumMaterialTypes {

    STONE(Material.STONE, SoundType.STONE),
    LEAVES(Material.LEAVES, SoundType.CROP),
    DIRT(Material.DIRT, SoundType.GRAVEL),
    WOOD(Material.WOOD, SoundType.WOOD),
    GRASS(Material.GRASS, SoundType.GRASS),
    GLASS(Material.GLASS, SoundType.GLASS),
    PORTAL(Material.PORTAL, SoundType.GLASS),
    TALL_PLANTS(Material.PLANT, SoundType.ROOTS),
    PLANT(Material.PLANT, SoundType.CROP),
    SNOW(Material.SNOW, SoundType.SNOW),
    FIRE(Material.FIRE, SoundType.WOOD),
    WOOL(Material.WOOL, SoundType.WOOL),
    GOURD(Material.DIRT, SoundType.WOOD),
    ICE(Material.ICE, SoundType.GLASS),
    GLASS_SOUND(Material.GLASS, SoundType.GLASS),
    METAL_SOUND(Material.STONE, SoundType.METAL),
    TROPHY(Material.STONE, SoundType.METAL),
    SLIME(Material.PLANT, SoundType.SLIME_BLOCK),
    SAND(Material.SAND, SoundType.SAND);

    private final Material m;
    private final SoundType s;

    EnumMaterialTypes(Material m, SoundType s) {
        this.m = m;
        this.s = s;
    }

    public Material getMaterial() {
        return m;
    }

    public SoundType getSound() {
        return s;
    }
}