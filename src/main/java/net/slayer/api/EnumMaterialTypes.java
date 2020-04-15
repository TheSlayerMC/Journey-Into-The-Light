package net.slayer.api;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum EnumMaterialTypes {

    STONE(Material.ROCK, SoundType.STONE),
    LEAVES(Material.LEAVES, SoundType.PLANT),
    DIRT(Material.GROUND, SoundType.GROUND),
    WOOD(Material.WOOD, SoundType.WOOD),
    GRASS(Material.GRASS, SoundType.PLANT),
    GLASS(Material.GLASS, SoundType.GLASS),
    PORTAL(Material.PORTAL, SoundType.GLASS),
    VINES(Material.VINE, SoundType.PLANT),
    PLANT(Material.PLANTS, SoundType.PLANT),
    SNOW(Material.SNOW, SoundType.SNOW),
    FIRE(Material.FIRE, SoundType.WOOD),
    WOOL(Material.CLOTH, SoundType.CLOTH),
    GOURD(Material.GOURD, SoundType.WOOD),
    ICE(Material.ICE, SoundType.GLASS),
    GLASS_SOUND(Material.GROUND, SoundType.GLASS),
    METAL_SOUND(Material.ROCK, SoundType.METAL),
    TROPHY(Material.GROUND, SoundType.METAL),
    SLIME(Material.GROUND, SoundType.SLIME),
    SAND(Material.SAND, SoundType.SAND);

    private Material m;
    private SoundType s;

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