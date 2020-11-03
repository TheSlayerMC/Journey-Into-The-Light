package net.jitl.block.base;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum EnumMaterialTypes {
    STONE(Material.ROCK, SoundType.STONE),
    LEAVES(Material.LEAVES, SoundType.PLANT),
    DIRT(Material.GOURD, SoundType.GROUND),
    WOOD(Material.WOOD, SoundType.WOOD),
    GRASS(Material.GOURD, SoundType.PLANT),
    GLASS(Material.GLASS, SoundType.GLASS),
    PORTAL(Material.PORTAL, SoundType.GLASS),
    TALL_PLANTS(Material.TALL_PLANTS, SoundType.PLANT),
    PLANT(Material.PLANTS, SoundType.PLANT),
    SNOW(Material.SNOW, SoundType.SNOW),
    FIRE(Material.FIRE, SoundType.WOOD),
    WOOL(Material.WOOL, SoundType.CLOTH),
    GOURD(Material.GOURD, SoundType.WOOD),
    ICE(Material.ICE, SoundType.GLASS),
    GLASS_SOUND(Material.GLASS, SoundType.GLASS),
    METAL_SOUND(Material.ROCK, SoundType.METAL),
    TROPHY(Material.ROCK, SoundType.METAL),
    SLIME(Material.PLANTS, SoundType.SLIME),
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
