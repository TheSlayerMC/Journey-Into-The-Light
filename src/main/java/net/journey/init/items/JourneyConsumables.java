package net.journey.init.items;

import net.journey.items.ItemGoldenFood;
import net.journey.items.ItemHealth;
import net.journey.util.PotionEffects;
import net.minecraft.item.Item;
import net.slayer.api.item.ItemModFood;

public class JourneyConsumables {

    public static Item heartSml;
    public static Item heartMed;
    public static Item heartLrg;
    public static Item heartUlt;
    public static Item heartSentry;
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
    public static Item goldenSteak;
    public static Item goldenSteakOP;
    public static Item goldenPotato;
    public static Item goldenPotatoOP;
    public static Item goldenPork;
    public static Item goldenPorkOP;
    public static Item goldenFish;
    public static Item goldenFishOP;
    public static Item goldenChicken;
    public static Item goldenChickenOP;
    public static Item goldenRabbit;
    public static Item goldenRabbitOP;
    public static Item goldenMutton;
    public static Item goldenMuttonOP;
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

    public static void init() {

        friedFlamingGhastTentacale = new ItemModFood("friedFlamingGhastTentacle", "Fried Flaming Tentacale", 4, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
        flamingGhastTentacle = new ItemModFood("flamingGhastTentacle", "Flaming Ghast Tentacale", 1, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
        friedGhastTentacale = new ItemModFood("friedGhastTentacle", "Fried Ghast Tentacale", 4, 0.6F, 10, true);
        ghastTentacle = new ItemModFood("ghastTentacle", "Ghast Tentacale", 1, 0.6F, 10, true);
        friedEgg = new ItemModFood("friedEgg", "Fried Egg", 2, 0.6F, 10, false);
        floroPedal = new ItemModFood("floroPedal", "Floro Pedal", 3, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 100, 40), 1.0F);
        tomato = new ItemModFood("tomato", "Tomato", 3, 0.6F, 10, false);
        airMelon = new ItemModFood("airMelon", "Air Melon", 10, 3.0F, 2, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 40), 1.0F);
        glowshroom = new ItemModFood("glowshroom", "Glowshroom", 4, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F).setAlwaysEdible();
        terrashroom = new ItemModFood("terrashroom", "Terrashroom", 8, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 100, 1), 1.0F).setAlwaysEdible();
        corveggies = new ItemModFood("corveggies", "Corveggies", 4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.saturation, 200, 1), 1.0F).setAlwaysEdible();
        crackenCanes = new ItemModFood("crackenCanes", "Kracken Canes", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 200, 1), 1.0F).setAlwaysEdible();
        crakeBulb = new ItemModFood("crakeBulb", "Crake Bulb", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 200, 1), 1.0F).setAlwaysEdible();
        spineberries = new ItemModFood("spineBerries", "Spine Berries", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.absorption, 200, 1), 1.0F).setAlwaysEdible();
        zatPedal = new ItemModFood("zatPedal", "Zat Pedal", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 200, 1), 1.0F).setAlwaysEdible();
        glowa = new ItemModFood("glowa", "Glowa", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 5, 1), 1.0F).setAlwaysEdible();
        mintCandyCane = new ItemModFood("mintCandyCane", "Mint Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 100, 1), 1.0F).setAlwaysEdible();
        fruityCandyCane = new ItemModFood("fruityCandyCane", "Fruity Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 100, 1), 1.0F).setAlwaysEdible();
        cherryCandyCane = new ItemModFood("cherryCandyCane", "Cherry Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F).setAlwaysEdible();
        peppermint = new ItemModFood("peppermint", "Peppermint", 1, 0.1F, 2, false);
        jellyBeans = new ItemModFood("jellyBeans", "Jelly Beans", 1, 0.1F, 2, false);
        chocolate = new ItemModFood("chocolate", "Chocolate Bar", 2, 0.1F, 2, false);
        vanillaWafer = new ItemModFood("vanillaWafer", "Vanilla Wafer", 1, 0.1F, 2, false);

        goldenSteak = new ItemGoldenFood("normalGoldenSteak", "Golden Steak", 6, 1.2F, false, false);
        goldenSteakOP = new ItemGoldenFood("OPGoldenSteak", "Golden Steak", 10, 2.2F, false, true);
        goldenPotato = new ItemGoldenFood("normalGoldenPotato", "Golden Potato", 3, 2.0F, false, false);
        goldenPotatoOP = new ItemGoldenFood("OPGoldenPotato", "Golden Potato", 6, 2.5F, false, true);
        goldenPork = new ItemGoldenFood("normalGoldenPork", "Golden Porkchop", 10, 1.0F, false, false);
        goldenPorkOP = new ItemGoldenFood("OPGoldenPork", "Golden Porkchop", 12, 1.2F, false, true);
        goldenFish = new ItemGoldenFood("normalGoldenFish", "Golden Fish", 6, 2.0F, false, false);
        goldenFishOP = new ItemGoldenFood("OPGoldenFish", "Golden Fish", 8, 2.5F, false, true);
        goldenChicken = new ItemGoldenFood("normalGoldenChicken", "Golden Chicken", 2, 3.0F, false, false);
        goldenChickenOP = new ItemGoldenFood("OPGoldenChicken", "Golden Chicken", 4, 3.5F, false, true);
        goldenRabbit = new ItemGoldenFood("normalGoldenRabbit", "Golden Rabbit", 2, 3.5F, false, false);
        goldenRabbitOP = new ItemGoldenFood("OPGoldenRabbit", "Golden Rabbit", 4, 4.0F, false, true);
        goldenMutton = new ItemGoldenFood("normalGoldenMutton", "Golden Mutton", 2, 3.5F, false, false);
        goldenMuttonOP = new ItemGoldenFood("OPGoldenMutton", "Golden Mutton", 4, 4.0F, false, true);
        goldenWing = new ItemGoldenFood("normalGoldenWing", "Golden Wing", 2, 4.5F, false, false);
        goldenWingOP = new ItemGoldenFood("OPGoldenWing", "Golden Wing", 4, 5.0F, false, true);

        eucaMeat = new ItemModFood("eucaMeat", "Euca Meat", 6, 0.6F, 10, true);
        rocMeat = new ItemModFood("rocMeat", "Bird Wing", 4, 0.6F, 10, true);
        cookedRocMeat = new ItemModFood("cookedRocMeat", "Cooked Bird Wing", 10, 0.6F, 10, true);
        hongoShroom = new ItemModFood("hongoShroom", "Hongoshroom", 4, 1.2F, 10, false);
        greenHonglowShroom = new ItemModFood("greenHonglowShroom", "Green Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 100, 1), 1.0F).setAlwaysEdible();
        redHonglowShroom = new ItemModFood("redHonglowShroom", "Red Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1), 1.0F).setAlwaysEdible();
        blueHonglowShroom = new ItemModFood("blueHonglowShroom", "Blue Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 100, 1), 1.0F).setAlwaysEdible();
        honglowShroom = new ItemModFood("honglowShroom", "Honglowshroom", 6, 1.2F, false, false).setAlwaysEdible();
        snakeFlesh = new ItemModFood("snakeFlesh", "Snake Flesh", 6, 1.2F, false, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 60, 1), 1.0F).setAlwaysEdible();
        flamingBeef = new ItemModFood("flamingBeef", "Raw Flaming Beef", 6, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 90, 1), 1.0F).setAlwaysEdible();
        flamingBeefCooked = new ItemModFood("flamingBeefCooked", "Cooked Flaming Beef", 16, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 2), 2.0F).setAlwaysEdible();

        sizzleberry = new ItemModFood("sizzleberry", "Sizzleberry", 1, 4.0F, 5, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 50, 1), 1.0F).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 50, 1), 1.0F);
        bradberry = new ItemModFood("bradberry", "Bradberry", 1, 8, 4, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        tangleberry = new ItemModFood("tangleberry", "Tangleberry", 2, 4, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 50, 1), 1.0F).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        juiceberry = new ItemModFood("juiceberry", "Juiceberry", 1, 6, 1, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);
        bogberry = new ItemModFood("bogberry", "Bogberry", 2, 6, 3, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.confusion, 50, 1), 1.0F);

        heartSml = new ItemHealth("heartSml", "Heart: Tier 1", 1, 2, 0.2F, false, false);
        heartMed = new ItemHealth("heartMed", "Heart: Tier 2", 2, 2, 0.2F, false, false);
        heartLrg = new ItemHealth("heartLrg", "Heart: Tier 3", 4, 2, 0.2F, false, false);
        heartUlt = new ItemHealth("heartUlt", "Heart: Ultimate", 8, 2, 0.2F, false, false);
        heartSentry = new ItemHealth("heartSen", "Sentry's Heart", 10, 2, 0.2F, false, true);

    }
}