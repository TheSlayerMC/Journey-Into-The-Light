package net.journey.init.items;

import net.journey.init.JourneyTabs;
import net.journey.init.Registrar;
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
    public static Item breathingFungus;
    public static Item breathingFungusCooked;

    public static void init() {
        friedFlamingGhastTentacale = Registrar.regAndSetupItem("friedFlamingGhastTentacle", "Fried Flaming Tentacale", new ItemModFood(4, 0.6F, 10, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F), JourneyTabs.CROPS);
        flamingGhastTentacle = Registrar.regAndSetupItem("flamingGhastTentacle", "Flaming Ghast Tentacale", new ItemModFood(1, 0.6F, 10, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F), JourneyTabs.CROPS);
        friedGhastTentacale = Registrar.regAndSetupItem("friedGhastTentacle", "Fried Ghast Tentacale", new ItemModFood(4, 0.6F, 10, true), JourneyTabs.CROPS);
        ghastTentacle = Registrar.regAndSetupItem("ghastTentacle", "Ghast Tentacale", new ItemModFood(1, 0.6F, 10, true), JourneyTabs.CROPS);
        friedEgg = Registrar.regAndSetupItem("friedEgg", "Fried Egg", new ItemModFood(2, 0.6F, 10, false), JourneyTabs.CROPS);
        floroPedal = Registrar.regAndSetupItem("floroPedal", "Floro Pedal", new ItemModFood(3, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 100, 40), 1.0F), JourneyTabs.CROPS);
        tomato = Registrar.regAndSetupItem("tomato", "Tomato", new ItemModFood(3, 0.6F, 10, false), JourneyTabs.CROPS);
        airMelon = Registrar.regAndSetupItem("airMelon", "Air Melon", new ItemModFood(10, 3.0F, 2, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 40), 1.0F), JourneyTabs.CROPS);
        glowshroom = Registrar.regAndSetupItem("glowshroom", "Glowshroom", new ItemModFood(4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F), JourneyTabs.CROPS);
        terrashroom = Registrar.regAndSetupItem("terrashroom", "Terrashroom", new ItemModFood(8, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F), JourneyTabs.CROPS);
        corveggies = Registrar.regAndSetupItem("corveggies", "Corveggies", new ItemModFood(4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.saturation, 200, 1), 1.0F), JourneyTabs.CROPS);
        crackenCanes = Registrar.regAndSetupItem("crackenCanes", "Kracken Canes", new ItemModFood(4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 200, 1), 1.0F), JourneyTabs.CROPS);
        crakeBulb = Registrar.regAndSetupItem("crakeBulb", "Crake Bulb", new ItemModFood(4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 200, 1), 1.0F), JourneyTabs.CROPS);
        spineberries = Registrar.regAndSetupItem("spineBerries", "Spine Berries", new ItemModFood(4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.absorption, 200, 1), 1.0F), JourneyTabs.CROPS);
        zatPedal = Registrar.regAndSetupItem("zatPedal", "Zat Pedal", new ItemModFood(4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 200, 1), 1.0F), JourneyTabs.CROPS);
        glowa = Registrar.regAndSetupItem("glowa", "Glowa", new ItemModFood(4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 5, 1), 1.0F), JourneyTabs.CROPS);
        mintCandyCane = Registrar.regAndSetupItem("mintCandyCane", "Mint Candy Cane", new ItemModFood(3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 1), 1.0F), JourneyTabs.CROPS);
        fruityCandyCane = Registrar.regAndSetupItem("fruityCandyCane", "Fruity Candy Cane", new ItemModFood(3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 100, 1), 1.0F), JourneyTabs.CROPS);
        cherryCandyCane = Registrar.regAndSetupItem("cherryCandyCane", "Cherry Candy Cane", new ItemModFood(3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F), JourneyTabs.CROPS);
        peppermint = Registrar.regAndSetupItem("peppermint", "Peppermint", new ItemModFood(1, 0.1F, 2, false), JourneyTabs.CROPS);
        jellyBeans = Registrar.regAndSetupItem("jellyBeans", "Jelly Beans", new ItemModFood(1, 0.1F, 2, false), JourneyTabs.CROPS);
        chocolate = Registrar.regAndSetupItem("chocolate", "Chocolate Bar", new ItemModFood(2, 0.1F, 2, false), JourneyTabs.CROPS);
        vanillaWafer = Registrar.regAndSetupItem("vanillaWafer", "Vanilla Wafer", new ItemModFood(1, 0.1F, 2, false), JourneyTabs.CROPS);

        goldenPotato = Registrar.regAndSetupItem("normalGoldenPotato", "Golden Potato", new ItemGoldenFood(1, 2.0F, false, false), JourneyTabs.CROPS);
        goldenPotatoOP = Registrar.regAndSetupItem("OPGoldenPotato", "Golden Potato", new ItemGoldenFood(2, 2.5F, false, true), JourneyTabs.CROPS);
        goldenWing = Registrar.regAndSetupItem("normalGoldenWing", "Golden Wing", new ItemGoldenFood(3, 4.5F, false, false), JourneyTabs.CROPS);
        goldenWingOP = Registrar.regAndSetupItem("OPGoldenWing", "Golden Wing", new ItemGoldenFood(4, 5.0F, false, true), JourneyTabs.CROPS);

        eucaMeat = Registrar.regAndSetupItem("eucaMeat", "Euca Meat", new ItemModFood(6, 0.6F, 10, true), JourneyTabs.CROPS);
        rocMeat = Registrar.regAndSetupItem("rocMeat", "Bird Wing", new ItemModFood(4, 0.6F, 10, true), JourneyTabs.CROPS);
        cookedRocMeat = Registrar.regAndSetupItem("cookedRocMeat", "Cooked Bird Wing", new ItemModFood(10, 0.6F, 10, true), JourneyTabs.CROPS);
        hongoShroom = Registrar.regAndSetupItem("hongoShroom", "Hongoshroom", new ItemModFood(4, 1.2F, 10, false), JourneyTabs.CROPS);
        greenHonglowShroom = Registrar.regAndSetupItem("greenHonglowShroom", "Green Honglowshroom", new ItemModFood(2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 100, 1), 1.0F), JourneyTabs.CROPS);
        redHonglowShroom = Registrar.regAndSetupItem("redHonglowShroom", "Red Honglowshroom", new ItemModFood(2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F), JourneyTabs.CROPS);
        blueHonglowShroom = Registrar.regAndSetupItem("blueHonglowShroom", "Blue Honglowshroom", new ItemModFood(2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 100, 1), 1.0F), JourneyTabs.CROPS);
        honglowShroom = Registrar.regAndSetupItem("honglowShroom", "Honglowshroom", new ItemModFood(6, 1.2F, false, false), JourneyTabs.CROPS);
        snakeFlesh = Registrar.regAndSetupItem("snakeFlesh", "Snake Flesh", new ItemModFood(6, 1.2F, false, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 60, 1), 1.0F), JourneyTabs.CROPS);
        flamingBeef = Registrar.regAndSetupItem("flamingBeef", "Raw Flaming Beef", new ItemModFood(6, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 90, 1), 1.0F), JourneyTabs.CROPS);
        flamingBeefCooked = Registrar.regAndSetupItem("flamingBeefCooked", "Cooked Flaming Beef", new ItemModFood(10, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 2), 2.0F), JourneyTabs.CROPS);

        breathingFungus = Registrar.regAndSetupItem("breathing_fungus", "Breathing Fungus", new ItemModFood(4, 3.5F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2), 0.5F), JourneyTabs.CROPS);
        breathingFungusCooked = Registrar.regAndSetupItem("breathing_fungus_cooked", "Cooked Fungus", new ItemModFood(6, 3.5F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2), 0.5F), JourneyTabs.CROPS);

        sizzleberry = Registrar.regAndSetupItem("sizzleberry", "Sizzleberry", new ItemModFood(1, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 50, 1), 1.0F).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 50, 1), 1.0F), JourneyTabs.CROPS);
        bradberry = Registrar.regAndSetupItem("bradberry", "Bradberry", new ItemModFood(1, 8, 4, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F), JourneyTabs.CROPS);
        tangleberry = Registrar.regAndSetupItem("tangleberry", "Tangleberry", new ItemModFood(2, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 50, 1), 1.0F).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F), JourneyTabs.CROPS);
        juiceberry = Registrar.regAndSetupItem("juiceberry", "Juiceberry", new ItemModFood(1, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F), JourneyTabs.CROPS);
        bogberry = Registrar.regAndSetupItem("bogberry", "Bogberry", new ItemModFood(2, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F), JourneyTabs.CROPS);
    }
}