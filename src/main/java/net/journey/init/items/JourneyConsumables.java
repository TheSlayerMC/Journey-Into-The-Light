package net.journey.init.items;

import net.journey.items.ItemGoldenFood;
import net.journey.util.PotionEffects;
import net.minecraft.item.Item;
import net.slayer.api.item.ItemModFood;

public class JourneyConsumables {

    public static Item sizzleberry;
    public static Item bradberry;
    public static Item tangleberry;
    public static Item juiceberry;
    public static Item bogberry;
    public static Item friedFlamingGhastTentacale;
    public static Item flamingGhastTentacle;
    public static Item friedGhastTentacale;
    public static Item ghastTentacle;
    public static Item friedEgg;
    public static Item floroPedal;
    public static Item tomato;
    public static Item airMelon;
    public static Item glowshroom;
    public static Item terrashroom;
    public static Item corveggies;
    public static Item crackenCanes;
    public static Item crakeBulb;
    public static Item spineberries;
    public static Item zatPedal;
    public static Item glowa;
    public static Item mintCandyCane;
    public static Item fruityCandyCane;
    public static Item cherryCandyCane;
    public static Item peppermint;
    public static Item jellyBeans;
    public static Item chocolate;
    public static Item vanillaWafer;
    public static Item goldenPotato;
    public static Item goldenPotatoOP;
    public static Item goldenWing;
    public static Item goldenWingOP;
    public static Item eucaMeat;
    public static Item rocMeat;
    public static Item cookedRocMeat;
    public static Item hongoShroom;
    public static Item greenHonglowShroom;
    public static Item redHonglowShroom;
    public static Item blueHonglowShroom;
    public static Item honglowShroom;
    public static Item snakeFlesh;
    public static Item flamingBeef;
    public static Item flamingBeefCooked;
    public static Item breathing_fungus;
    public static Item breathing_fungus_cooked;

    public static void init() {

        friedFlamingGhastTentacale = new ItemModFood("friedFlamingGhastTentacle", "Fried Flaming Tentacale", 4, 0.6F, 10, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
        flamingGhastTentacle = new ItemModFood("flamingGhastTentacle", "Flaming Ghast Tentacale", 1, 0.6F, 10, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
        friedGhastTentacale = new ItemModFood("friedGhastTentacle", "Fried Ghast Tentacale", 4, 0.6F, 10, true);
        ghastTentacle = new ItemModFood("ghastTentacle", "Ghast Tentacale", 1, 0.6F, 10, true);
        friedEgg = new ItemModFood("friedEgg", "Fried Egg", 2, 0.6F, 10, false);
        floroPedal = new ItemModFood("floroPedal", "Floro Pedal", 3, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 100, 40), 1.0F);
        tomato = new ItemModFood("tomato", "Tomato", 3, 0.6F, 10, false);
        airMelon = new ItemModFood("airMelon", "Air Melon", 10, 3.0F, 2, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 40), 1.0F);
        glowshroom = new ItemModFood("glowshroom", "Glowshroom", 4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F);
        terrashroom = new ItemModFood("terrashroom", "Terrashroom", 8, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F);
        corveggies = new ItemModFood("corveggies", "Corveggies", 4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.saturation, 200, 1), 1.0F);
        crackenCanes = new ItemModFood("crackenCanes", "Kracken Canes", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 200, 1), 1.0F);
        crakeBulb = new ItemModFood("crakeBulb", "Crake Bulb", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 200, 1), 1.0F);
        spineberries = new ItemModFood("spineBerries", "Spine Berries", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.absorption, 200, 1), 1.0F);
        zatPedal = new ItemModFood("zatPedal", "Zat Pedal", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 200, 1), 1.0F);
        glowa = new ItemModFood("glowa", "Glowa", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 5, 1), 1.0F);
        mintCandyCane = new ItemModFood("mintCandyCane", "Mint Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 1), 1.0F);
        fruityCandyCane = new ItemModFood("fruityCandyCane", "Fruity Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 100, 1), 1.0F);
        cherryCandyCane = new ItemModFood("cherryCandyCane", "Cherry Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F);
        peppermint = new ItemModFood("peppermint", "Peppermint", 1, 0.1F, 2, false);
        jellyBeans = new ItemModFood("jellyBeans", "Jelly Beans", 1, 0.1F, 2, false);
        chocolate = new ItemModFood("chocolate", "Chocolate Bar", 2, 0.1F, 2, false);
        vanillaWafer = new ItemModFood("vanillaWafer", "Vanilla Wafer", 1, 0.1F, 2, false);

        goldenPotato = new ItemGoldenFood("normalGoldenPotato", "Golden Potato", 1, 2.0F, false, false);
        goldenPotatoOP = new ItemGoldenFood("OPGoldenPotato", "Golden Potato", 2, 2.5F, false, true);
        goldenWing = new ItemGoldenFood("normalGoldenWing", "Golden Wing", 3, 4.5F, false, false);
        goldenWingOP = new ItemGoldenFood("OPGoldenWing", "Golden Wing", 4, 5.0F, false, true);

        eucaMeat = new ItemModFood("eucaMeat", "Euca Meat", 6, 0.6F, 10, true);
        rocMeat = new ItemModFood("rocMeat", "Bird Wing", 4, 0.6F, 10, true);
        cookedRocMeat = new ItemModFood("cookedRocMeat", "Cooked Bird Wing", 10, 0.6F, 10, true);
        hongoShroom = new ItemModFood("hongoShroom", "Hongoshroom", 4, 1.2F, 10, false);
        greenHonglowShroom = new ItemModFood("greenHonglowShroom", "Green Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 100, 1), 1.0F);
        redHonglowShroom = new ItemModFood("redHonglowShroom", "Red Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F);
        blueHonglowShroom = new ItemModFood("blueHonglowShroom", "Blue Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 100, 1), 1.0F);
        honglowShroom = new ItemModFood("honglowShroom", "Honglowshroom", 6, 1.2F, false, false);
        snakeFlesh = new ItemModFood("snakeFlesh", "Snake Flesh", 6, 1.2F, false, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 60, 1), 1.0F);
        flamingBeef = new ItemModFood("flamingBeef", "Raw Flaming Beef", 6, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 90, 1), 1.0F);
        flamingBeefCooked = new ItemModFood("flamingBeefCooked", "Cooked Flaming Beef", 10, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 2), 2.0F);

        breathing_fungus = new ItemModFood("breathing_fungus", "Breathing Fungus", 4, 3.5F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2), 0.5F);
        breathing_fungus_cooked = new ItemModFood("breathing_fungus_cooked", "Cooked Fungus", 6, 3.5F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2), 0.5F);

        sizzleberry = new ItemModFood("sizzleberry", "Sizzleberry", 1, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 50, 1), 1.0F).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 50, 1), 1.0F);
        bradberry = new ItemModFood("bradberry", "Bradberry", 1, 8, 4, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        tangleberry = new ItemModFood("tangleberry", "Tangleberry", 2, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 50, 1), 1.0F).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        juiceberry = new ItemModFood("juiceberry", "Juiceberry", 1, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        bogberry = new ItemModFood("bogberry", "Bogberry", 2, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
    }
}