package net.journey.util.recipes;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.common.JourneyCrops;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class JourneyRecipes {

    public JourneyRecipes() {
        JourneyBlocks b = new JourneyBlocks();
        JourneyItems i = new JourneyItems();
        addShapedRecipe(JourneyBlocks.greenGemBlock, "iii", "iii", "iii", 'i', JourneyItems.greenGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.purpleGemBlock, 1),
                "iii", "iii", "iii", 'i', JourneyItems.purpleGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.blueGemBlock, 1),
                "iii", "iii", "iii", 'i', JourneyItems.blueGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.yellowGemBlock, 1),
                "iii", "iii", "iii", 'i', JourneyItems.yellowGem);

        addShapedRecipe(new ItemStack(JourneyItems.greenGem, 9),
                "i", 'i', JourneyBlocks.greenGemBlock);
        addShapedRecipe(new ItemStack(JourneyItems.purpleGem, 9),
                "i", 'i', JourneyBlocks.purpleGemBlock);
        addShapedRecipe(new ItemStack(JourneyItems.blueGem, 9),
                "i", 'i', JourneyBlocks.blueGemBlock);
        addShapedRecipe(new ItemStack(JourneyItems.yellowGem, 9),
                "i", 'i', JourneyBlocks.yellowGemBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.igniter, 1),
                "iii", "jkj", "cfc", 'i', Blocks.NETHERRACK, 'j', Items.IRON_INGOT, 'k',
                JourneyBlocks.lavaRock, 'c', Blocks.COBBLESTONE, 'f', Items.FLINT_AND_STEEL);
        addShapedRecipe(new ItemStack(JourneyBlocks.eucaPortalFrame, 10),
                "iii", "iii", "iii", 'i', JourneyItems.eucaPortalGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.depthsPortalFrame, 12),
                "iii", "iii", "iii", 'i', JourneyItems.depthsPortalGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.cloudiaPortalFrame, 10),
                "iii", "iii", "iii", 'i', JourneyItems.cloudiaPortalGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.corbaPortalFrame, 12),
                "iii", "iii", 'i', JourneyItems.corbaPortalGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.terraniaPortalFrame, 10),
                "iii", "iii", "iii", 'i', JourneyItems.terraniaPortalGem);
        addShapedRecipe(new ItemStack(JourneyBlocks.frozenPortalFrame, 10),
                "iii", "idi", "iii", 'i', Items.SNOWBALL, 'd', Items.DIAMOND);
        addShapedRecipe(new ItemStack(JourneyBlocks.summoningTable, 1), "dsd", "iii", 'i',
                JourneyBlocks.bloodRock, 'd', JourneyItems.hellstoneIngot, 's', JourneyItems.bleedstone);
        addShapedRecipe(new ItemStack(JourneyBlocks.boilingBars, 4),
                "ddd", "ddd", 'd', Items.BLAZE_ROD);
        addShapedRecipe(new ItemStack(JourneyItems.obsidianRod, 2),
                "d", "d", 'd', Blocks.OBSIDIAN);
        addShapedRecipe(new ItemStack(JourneyBlocks.stoneCraftingTable, 1),
                "dd", "dd", 'd', Blocks.COBBLESTONE);
        addShapedRecipe(new ItemStack(JourneyBlocks.trophy, 1),
                "d d", "ddd", " d ", 'd', Items.GOLD_INGOT);
        addShapedRecipe(new ItemStack(JourneyBlocks.grindstone), " o ", "bib", "bbb", 'b',
                Blocks.BRICK_BLOCK, 'o', Blocks.OBSIDIAN, 'i', Items.IRON_INGOT);
        addShapedRecipe(new ItemStack(Blocks.WEB), "sss", "sss", "sss", 's', Items.STRING);
        addShapedRecipe(new ItemStack(JourneyBlocks.netherFurnace, 1),
                "ddd", "d d", "ddd", 'd', Blocks.NETHERRACK);
        addShapedRecipe(new ItemStack(JourneyItems.flameCoin),
                "iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND);
        addShapedRecipe(new ItemStack(JourneyItems.hellcrustIngot),
                "iii", "idi", "iii", 'i', JourneyItems.ash, 'd', JourneyItems.hellstoneIngot);
        addShapedRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot),
                "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', Blocks.STONE);
        addShapedRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot),
                "ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', JourneyItems.caveCrystal);
        addShapedRecipe(new ItemStack(JourneyItems.crystalBall), "idi", "iii", 'i', JourneyItems.MAGIC_DUST,
                'd', Items.ENDER_PEARL);
        addShapedRecipe(new ItemStack(JourneyBlocks.senterianPortalFrame, 4), "iii", "iii", 'i', JourneyBlocks.ANCIENT_STONE);
        addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16),
                "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall);
        addShapedRecipe(new ItemStack(JourneyWeapons.MAGIC_BOMB),
                "ddd", "did", "ddd", 'd', JourneyItems.MAGIC_DUST, 'i', JourneyWeapons.demonicBomb);
        addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 4),
                "ddd", "ddd", "ddd", 'd', JourneyItems.caveDust);
        addShapedRecipe(new ItemStack(JourneyItems.stoneStick, 16),
                "d", "d", "d", 'd', Blocks.STONE);
        addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16),
                "ddd", "ddd", "ddd", 'd', Blocks.STONE);

        addShapedRecipe(new ItemStack(JourneyBlocks.bloodBricks, 4),
                "dd", "dd", 'd', JourneyBlocks.bloodRock);
        addShapedRecipe(new ItemStack(JourneyBlocks.carvedBloodRock, 4),
                "dd", "dd", 'd', JourneyBlocks.bloodBricks);
        addShapedRecipe(new ItemStack(JourneyBlocks.bloodLamp, 4),
                "ddd", "did", "ddd", 'i', Blocks.GLOWSTONE, 'd', JourneyBlocks.bloodRock);
        addShapedRecipe(new ItemStack(JourneyBlocks.obelisk, 1),
                "ddd", "did", "ddd", 'i', JourneyBlocks.bloodRock, 'd', JourneyItems.boilPowder);
        addShapedRecipe(new ItemStack(JourneyBlocks.bloodCatalyst, 1),
                "ddd", "did", "ddd", 'i', JourneyBlocks.bloodRock, 'd', JourneyItems.blood);
        addShapedRecipe(new ItemStack(JourneyBlocks.bloodRune, 4),
                "ddd", "did", "ddd", 'd', JourneyBlocks.bloodRock, 'i', JourneyItems.BALMY_TEARDROP);
        addShapedRecipe(new ItemStack(JourneyBlocks.bloodPillar, 4),
                "dd", "dd", 'd', JourneyBlocks.carvedBloodRock);

        addShapedRecipe(new ItemStack(JourneyItems.stoneClump, 16),
                "ddd", "ddd", "ddd", 'd', Blocks.COBBLESTONE);
        addShapedRecipe(new ItemStack(JourneyItems.withicSoul, 1),
                "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.withicDust);
        addShapedRecipe(new ItemStack(JourneyItems.concentratedBlood, 1),
                "ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.blood);
        addShapedRecipe(new ItemStack(JourneyItems.boilKey, 1), "dgd", "did", "dgd", 'd',
                JourneyItems.flamingSpring, 'i', JourneyItems.flamingSprocket, 'g', Items.GOLD_INGOT);
        addShapedRecipe(new ItemStack(JourneyItems.essenceArrow, 4), "d", "i", "g", 'd',
                Items.IRON_INGOT, 'i', JourneyItems.stoneStick, 'g', JourneyItems.rocFeather);
        addShapedRecipe(new ItemStack(JourneyWeapons.withicHammer, 1), "ddd", "did", " i ", 'd',
                JourneyItems.hellcrustIngot, 'i', JourneyItems.withicSpine);
        addShapedRecipe(new ItemStack(JourneyWeapons.nethicHammer, 1), "jdj", "did", " i ", 'd',
                JourneyBlocks.hellstoneBlock, 'i', JourneyItems.hellstoneClump, 'j', JourneyItems.flamingSpring);
        /*
         * addShapedRecipe(new ItemStack(JourneyItems.pocketCrafting, 1),
         * new Object[] { "ddd", "did", "ddd", 'd',
         * JourneyItems.flamingHide, 'i', JourneyItems.concentratedBlood });
         */
        addShapelessRecipe(new ItemStack(JourneyItems.demonicDust, 5), JourneyItems.demonicBone);
        addShapelessRecipe(new ItemStack(JourneyItems.smithstonedust, 4), JourneyItems.smithstone);
        addShapelessRecipe(new ItemStack(JourneyItems.bleedstonedust, 4), JourneyItems.bleedstone);
        addShapelessRecipe(new ItemStack(JourneyItems.nethicgemstone, 1), JourneyItems.bleedstonedust, JourneyItems.smithstonedust);
        addShapelessRecipe(new ItemStack(JourneyItems.darkGem, 1), JourneyItems.shadiumIngot, Items.DIAMOND);

        addOPFood(JourneyConsumables.goldenPork, JourneyConsumables.goldenPorkOP, Items.PORKCHOP);
        addOPFood(JourneyConsumables.goldenSteak, JourneyConsumables.goldenSteakOP, Items.BEEF);
        addOPFood(JourneyConsumables.goldenPotato, JourneyConsumables.goldenPotatoOP, Items.POTATO);
        addOPFood(JourneyConsumables.goldenFish, JourneyConsumables.goldenFishOP, Items.FISH);
        addOPFood(JourneyConsumables.goldenChicken, JourneyConsumables.goldenChickenOP, Items.CHICKEN);
        addOPFood(JourneyConsumables.goldenRabbit, JourneyConsumables.goldenRabbitOP, Items.RABBIT);
        addOPFood(JourneyConsumables.goldenMutton, JourneyConsumables.goldenMuttonOP, Items.MUTTON);
        addOPFood(JourneyConsumables.goldenWing, JourneyConsumables.goldenWingOP, JourneyConsumables.rocMeat);

        addShapedRecipe(new ItemStack(JourneyItems.hellstoneClump),
                "iii", 'i', JourneyItems.hellstoneIngot);
        addShapedRecipe(new ItemStack(JourneyItems.shadiumClump),
                "iii", 'i', JourneyItems.shadiumIngot);
        addShapedRecipe(new ItemStack(JourneyItems.luniumClump),
                "iii", 'i', JourneyItems.luniumIngot);
        addShapelessRecipe(new ItemStack(JourneyItems.spawnerClump),
                JourneyItems.shadiumClump, JourneyItems.luniumClump, JourneyItems.hellstoneClump);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2),
                JourneyBlocks.glowshroomRed);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2),
                JourneyBlocks.glowshroomGreen);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 2),
                JourneyBlocks.glowshroomBlue);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
                JourneyBlocks.tallGlowshroomRed);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
                JourneyBlocks.tallGlowshroomGreen);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
                JourneyBlocks.tallGlowshroomBlue);
        addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4),
                JourneyConsumables.glowshroom);
        addShapelessRecipe(new ItemStack(JourneyCrops.floroSeeds, 4), JourneyConsumables.floroPedal);
        addShapelessRecipe(new ItemStack(JourneyItems.eucaPortalGem, 10), JourneyItems.eucaPortalPiece, JourneyItems.eucaPortalPiece_0, JourneyItems.eucaPortalPiece_1);
        addWood(JourneyBlocks.eucaGoldLog, JourneyBlocks.goldEucaPlank, JourneyBlocks.eucaGoldStairs, 0, true);
        addWood(JourneyBlocks.depthsLog, JourneyBlocks.depthsPlank, JourneyBlocks.depthsPlank, 1, true);
        addWood(JourneyBlocks.corbaLog, JourneyBlocks.corbaPlank, null, 1, true);
        addWood(JourneyBlocks.frozenLog, JourneyBlocks.frozenPlanks, null, 1, true);//TODO Add stairs

        addShapedRecipe(new ItemStack(JourneyArmory.blazehornHelmet),
                "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
        addShapedRecipe(new ItemStack(JourneyArmory.blazehornChest),
                "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
        addShapedRecipe(new ItemStack(JourneyArmory.blazehornLegs),
                "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);
        addShapedRecipe(new ItemStack(JourneyArmory.blazehornBoots),
                "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyItems.horn);

        addShapedRecipe(new ItemStack(JourneyArmory.fireboundHelmet),
                "idi", "d d", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
        addShapedRecipe(new ItemStack(JourneyArmory.fireboundChest),
                "i i", "did", "idi", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
        addShapedRecipe(new ItemStack(JourneyArmory.fireboundLegs),
                "idi", "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);
        addShapedRecipe(new ItemStack(JourneyArmory.fireboundBoots),
                "d d", "i i", 'd', Items.BLAZE_ROD, 'i', JourneyBlocks.hellstoneBlock);

        addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustHelmet),
                "idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
        addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustChest), "i i", "did", "idi", 'd',
                JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
        addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustLegs), "idi", "d d", "i i", 'd',
                JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);
        addShapedRecipe(new ItemStack(JourneyArmory.bloodcrustBoots),
                "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot);

        addShapedRecipe(new ItemStack(JourneyArmory.bleedrockHelmet),
                "idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
        addShapedRecipe(new ItemStack(JourneyArmory.bleedrockChest), "i i", "did", "idi", 'd',
                JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
        addShapedRecipe(new ItemStack(JourneyArmory.bleedrockLegs), "idi", "d d", "i i", 'd',
                JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
        addShapedRecipe(new ItemStack(JourneyArmory.bleedrockBoots),
                "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood);
        addShapelessRecipe(new ItemStack(JourneyArmory.woodMultiTool),
                Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE, Items.WOODEN_AXE);
        addShapelessRecipe(new ItemStack(JourneyArmory.stoneMultiTool),
                Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_HOE, Items.STONE_AXE);
        addShapelessRecipe(new ItemStack(JourneyArmory.ironMultiTool),
                Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_HOE, Items.IRON_AXE);
        addShapelessRecipe(new ItemStack(JourneyArmory.diamondMultiTool),
                Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE, Items.DIAMOND_AXE);

        addOre(JourneyBlocks.celestiumOre, JourneyItems.celestiumIngot, JourneyBlocks.celestiumBlock,
                JourneyArmory.celestiumAxe, JourneyArmory.celestiumPickaxe, JourneyArmory.celestiumShovel,
                JourneyArmory.celestiumHoe, JourneyWeapons.celestiumSword, JourneyArmory.celestiumMultiTool,
                JourneyArmory.celestiumHelmet, JourneyArmory.celestiumChest, JourneyArmory.celestiumLegs,
                JourneyArmory.celestiumBoots, JourneyItems.celestiumDust);
        addOre(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneIngot, JourneyBlocks.hellstoneBlock,
                JourneyArmory.hellstoneAxe, JourneyArmory.hellstonePickaxe, JourneyArmory.hellstoneShovel,
                JourneyArmory.hellstoneHoe, JourneyWeapons.hellstoneSword, JourneyArmory.hellstoneMultiTool,
                JourneyArmory.hellstoneHelmet, JourneyArmory.hellstoneChest, JourneyArmory.hellstoneLegs,
                JourneyArmory.hellstoneBoots, JourneyItems.hellstoneDust);
        addOre(JourneyBlocks.flairiumOre, JourneyItems.flairiumIngot, JourneyBlocks.flairiumBlock,
                JourneyArmory.flairiumAxe, JourneyArmory.flairiumPickaxe, JourneyArmory.flairiumShovel,
                JourneyArmory.flairiumHoe, JourneyWeapons.flairiumSword, JourneyArmory.flairiumMultiTool,
                JourneyArmory.flairiumHelmet, JourneyArmory.flairiumChest, JourneyArmory.flairiumLegs,
                JourneyArmory.flairiumBoots, JourneyItems.flairiumDust);
        addOre(JourneyBlocks.desOre, JourneyItems.desIngot, JourneyBlocks.desBlock, JourneyArmory.desAxe,
                JourneyArmory.desPickaxe, JourneyArmory.desShovel, JourneyArmory.desHoe, JourneyWeapons.desSword,
                JourneyArmory.desMultiTool, null, null, null, null, null);
        addOre(JourneyBlocks.shadiumOre, JourneyItems.shadiumIngot, JourneyBlocks.shadiumBlock,
                JourneyArmory.shadiumAxe, JourneyArmory.shadiumPickaxe, JourneyArmory.shadiumShovel,
                JourneyArmory.shadiumHoe, JourneyWeapons.shadiumSword, JourneyArmory.shadiumMultiTool,
                JourneyArmory.shadiumHelmet, JourneyArmory.shadiumChest, JourneyArmory.shadiumLegs,
                JourneyArmory.shadiumBoots, JourneyItems.shadiumDust);
        addOre(JourneyBlocks.luniumOre, JourneyItems.luniumIngot, JourneyBlocks.luniumBlock, JourneyArmory.luniumAxe,
                JourneyArmory.luniumPickaxe, JourneyArmory.luniumShovel, JourneyArmory.luniumHoe,
                JourneyWeapons.luniumSword, JourneyArmory.luniumMultiTool, JourneyArmory.luniumHelmet,
                JourneyArmory.luniumChest, JourneyArmory.luniumLegs, JourneyArmory.luniumBoots,
                JourneyItems.luniumDust);
        addOre(JourneyBlocks.sapphireOre, JourneyItems.sapphire, JourneyBlocks.sapphireBlock,
                JourneyArmory.sapphireAxe, JourneyArmory.sapphirePickaxe, JourneyArmory.sapphireShovel,
                JourneyArmory.sapphireHoe, JourneyWeapons.sapphireSword, JourneyArmory.sapphireMultiTool,
                JourneyArmory.sapphireHelmet, JourneyArmory.sapphireChest, JourneyArmory.sapphireLegs,
                JourneyArmory.sapphireBoots, JourneyItems.sapphireDust);
        addOre(JourneyBlocks.gorbiteOre, JourneyItems.gorbiteGem, JourneyBlocks.gorbiteBlock,
                JourneyArmory.gorbiteAxe, JourneyArmory.gorbitePickaxe, JourneyArmory.gorbiteShovel,
                JourneyArmory.gorbiteHoe, JourneyWeapons.gorbiteSword, JourneyArmory.gorbiteMultiTool,
                JourneyArmory.gorbiteHelmet, JourneyArmory.gorbiteChest, JourneyArmory.gorbiteLegs,
                JourneyArmory.gorbiteBoots, JourneyItems.gorbiteDust);
        addOre(JourneyBlocks.orbaditeOre, JourneyItems.orbaditeIngot, JourneyBlocks.orbaditeBlock,
                JourneyArmory.orbaditeAxe, JourneyArmory.orbaditePickaxe, JourneyArmory.orbaditeShovel,
                JourneyArmory.orbaditeHoe, JourneyWeapons.orbaditeSword, JourneyArmory.orbaditeMultiTool,
                JourneyArmory.orbaditeHelmet, JourneyArmory.orbaditeChest, JourneyArmory.orbaditeLegs,
                JourneyArmory.orbaditeBoots, JourneyItems.orbaditeDust);
        addOre(JourneyBlocks.koriteOre, JourneyItems.koriteIngot, JourneyBlocks.koriteBlock, JourneyArmory.koriteAxe,
                JourneyArmory.koritePickaxe, JourneyArmory.koriteShovel, JourneyArmory.koriteHoe,
                JourneyWeapons.koriteSword, JourneyArmory.koriteMultiTool, null, null, null, null, null);
        addOre(JourneyBlocks.storonOre, JourneyItems.storonIngot, JourneyBlocks.storonBlock, JourneyArmory.storonAxe,
                JourneyArmory.storonPickaxe, JourneyArmory.storonShovel, JourneyArmory.storonHoe,
                JourneyWeapons.storonSword, JourneyArmory.storonMultiTool, null, null, null, null, null);
        addOre(JourneyBlocks.mekyumOre, JourneyItems.mekyumIngot, JourneyBlocks.mekyumBlock, JourneyArmory.mekyumAxe,
                JourneyArmory.mekyumPickaxe, JourneyArmory.mekyumShovel, JourneyArmory.mekyumHoe,
                JourneyWeapons.mekyumSword, JourneyArmory.mekyumMultiTool, null, null, null, null, null);
        addOre(null, JourneyItems.nethicgemstone, JourneyBlocks.nethicGemstoneBlock, JourneyArmory.nethicAxe,
                JourneyArmory.nethicPickaxe, JourneyArmory.nethicShovel, null,
                null, null, null, null, null, null, null);
        //addOre(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null,
        // null, null, null, null, null, null, i.ashDust);

        addShapedRecipe(new ItemStack(JourneyWeapons.demonicBomb, 16),
                "ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall);
        addShapedRecipe(new ItemStack(JourneyWeapons.withicBlade),
                "i", "i", "d", 'd', JourneyItems.withicDust, 'i', JourneyItems.hellcrustIngot);
        addShapedRecipe(new ItemStack(JourneyWeapons.reinforcedStoneSword), "d", "d", "i", 'd',
                JourneyItems.reinforcedStoneIngot, 'i', JourneyItems.stoneStick);
        addShapedRecipe(new ItemStack(JourneyWeapons.reinforcedCrystalSword), "d", "d", "i", 'd',
                JourneyItems.reinforcedCrystalIngot, 'i', JourneyItems.stoneStick);
        addShapedRecipe(new ItemStack(JourneyWeapons.pedalSword),
                "d", "d", "i", 'd', JourneyConsumables.floroPedal, 'i', JourneyItems.stoneClump);
        addShapedRecipe(new ItemStack(JourneyWeapons.crystalBlade),
                "d", "d", "i", 'd', JourneyItems.caveCrystal, 'i', Blocks.STONE);
        addShapedRecipe(new ItemStack(JourneyWeapons.flameBow),
                " fs", "f s", " fs", 'f', Items.FIRE_CHARGE, 's', Items.STRING);
        addShapedRecipe(new ItemStack(JourneyWeapons.demonicSword),
                "d", "d", "i", 'd', JourneyItems.demonicBone, 'i', JourneyItems.demonicDust);

        addShapedRecipe(new ItemStack(JourneyBlocks.iridiumBlock), "iii", "iii", "iii", 'i', JourneyItems.iridium);
        addShapelessRecipe(new ItemStack(JourneyItems.iridium, 9), JourneyBlocks.iridiumBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.ashualBlock), "iii", "iii", "iii", 'i', JourneyItems.ash);
        addShapelessRecipe(new ItemStack(JourneyItems.ash, 9), JourneyBlocks.ashualBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.blaziumOre), "iii", "iii", "iii", 'i', JourneyItems.blazium);
        addShapelessRecipe(new ItemStack(JourneyItems.blazium, 9), JourneyBlocks.blaziumBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.enderilliumBlock), "iii", "iii", "iii", 'i', JourneyItems.enderilliumShard);
        addShapelessRecipe(new ItemStack(JourneyItems.enderilliumShard, 9), JourneyBlocks.enderilliumBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.luniteBlock), "iii", "iii", "iii", 'i', JourneyItems.luniteChunk);
        addShapelessRecipe(new ItemStack(JourneyItems.luniteChunk, 9), JourneyBlocks.luniteBlock);

        addShapedRecipe(new ItemStack(JourneyBlocks.smithstone), "ii", "ii", 'i', JourneyItems.smithstone);
        addShapelessRecipe(new ItemStack(JourneyItems.smithstone, 4), JourneyBlocks.smithstone);

        addShapedRecipe(new ItemStack(JourneyBlocks.bleedstone), "ii", "ii", 'i', JourneyItems.bleedstone);
        addShapelessRecipe(new ItemStack(JourneyItems.bleedstone, 4), JourneyBlocks.bleedstone);


        JourneyJSONGenerator.generateConstants();
    }

    public static void furnaceRecipes() {
        JourneyBlocks b = new JourneyBlocks();
        JourneyItems i = new JourneyItems();
        GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(JourneyBlocks.smoothGlass), 1f);
        GameRegistry.addSmelting(JourneyItems.spawnerClump, new ItemStack(JourneyItems.spawnerBar), 1.0F);
        GameRegistry.addSmelting(JourneyConsumables.flamingBeef, new ItemStack(JourneyConsumables.flamingBeefCooked), 0.5F);
        GameRegistry.addSmelting(JourneyItems.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
        GameRegistry.addSmelting(JourneyItems.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
        GameRegistry.addSmelting(JourneyItems.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
        GameRegistry.addSmelting(JourneyItems.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 0.5F);
        GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER), 0.5F);
        GameRegistry.addSmelting(Items.EGG, new ItemStack(JourneyConsumables.friedEgg), 0.5F);
        GameRegistry.addSmelting(JourneyConsumables.rocMeat, new ItemStack(JourneyConsumables.cookedRocMeat), 0.5F);
        GameRegistry.addSmelting(JourneyConsumables.ghastTentacle, new ItemStack(JourneyConsumables.friedGhastTentacale), 0.5F);
        GameRegistry.addSmelting(JourneyConsumables.flamingGhastTentacle, new ItemStack(JourneyConsumables.friedFlamingGhastTentacale), 0.5F);

    }

    public void addOredictRecipe(Block result, Object... materials) {
        JourneyJSONGenerator.addRecipe(new ItemStack(result, 1), materials);

    }

    public void addOredictRecipe(Item result, Object... materials) {
        JourneyJSONGenerator.addRecipe(new ItemStack(result, 1), materials);

    }

    public void addOredictRecipe(ItemStack result, Object... materials) {
        JourneyJSONGenerator.addShapedRecipe(result, materials);

    }

    protected void addShapedRecipe(Block b, Object... o) {
        JourneyJSONGenerator.addShapedRecipe(new ItemStack(b, 1), o);

    }

    protected void addShapedRecipe(Item i, Object... o) {
        JourneyJSONGenerator.addShapedRecipe(new ItemStack(i, 1), o);

    }

    protected void addShapedRecipe(ItemStack i, Object... o) {
        JourneyJSONGenerator.addShapedRecipe(i, o);

    }

    protected void addShapelessRecipe(Block b, Object... o) {
        JourneyJSONGenerator.addShapelessRecipe(new ItemStack(b, 1), o);

    }

    protected void addShapelessRecipe(Item i, Object... o) {
        JourneyJSONGenerator.addShapelessRecipe(new ItemStack(i, 1), o);

    }

    protected void addShapelessRecipe(ItemStack i, Object... o) {
        JourneyJSONGenerator.addShapelessRecipe(i, o);

    }

    protected void addSmelting(Block input, Block output, float XP) {
        JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);

    }

    protected void addSmelting(Block input, Item output, float XP) {
        JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);

    }

    protected void addSmelting(Item input, Block output, float XP) {
        JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);

    }

    protected void addSmelting(Item input, Item output, float XP) {
        JourneyJSONGenerator.addSmelting(new ItemStack(input, 1), new ItemStack(output, 1), XP);

    }

    protected void addSmelting(ItemStack input, ItemStack output, float XP) {
        JourneyJSONGenerator.addSmelting(input, output, XP);

    }

    protected void addBlock(Block made, Item used) {
        addShapedRecipe(new ItemStack(made), "iii", "iii", "iii", 'i', used);
    }

    protected void addBlock(Block made, Block used) {
        addShapedRecipe(new ItemStack(made), "iii", "iii", "iii", 'i', used);
    }

    protected void addOPFood(Item nonOP, Item OP, Item base) {
        addShapedRecipe(new ItemStack(nonOP), "iii", "ibi", "iii", 'i', Items.GOLD_INGOT, 'b', base);
        addShapedRecipe(new ItemStack(OP), "iii", "ibi", "iii", 'i', Blocks.GOLD_BLOCK, 'b', base);
    }

    protected void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
    	if(log != null && plank != null) addShapelessRecipe(new ItemStack(plank, 4), log);
        if(stair != null && plank != null) addShapedRecipe(new ItemStack(stair, 4), "i  ", "ii ", "iii", 'i', plank);
        if(plank != null)addShapedRecipe(new ItemStack(Items.STICK, 4), "i", "i", 'i', plank);
        if(plank != null) addShapedRecipe(new ItemStack(Blocks.CRAFTING_TABLE), "ii", "ii", 'i', plank);
        if(smelt) GameRegistry.addSmelting(log, new ItemStack(Items.COAL), 0.5F);
    }

    protected void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword,
                          Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
        if (SlayerAPI.DEVMODE) {
            if (axe != null) addAxe(axe, ingot);
            if (pick != null) addPickaxe(pick, ingot);
            if (shovel != null) addShovel(shovel, ingot);
            if (hoe != null) addHoe(hoe, ingot);
            if (sword != null)
                addShapedRecipe(new ItemStack(sword), "b", "b", "s", 'b', block, 's', JourneyItems.obsidianRod);
            if (block != null) addBlock(block, ingot);
            if (helmet != null) addHelmet(helmet, ingot);
            if (chest != null) addChestplate(chest, ingot);
            if (legs != null) addLeggings(legs, ingot);
            if (boots != null) addBoots(boots, ingot);
            if (multiTool != null) addShapelessRecipe(new ItemStack(multiTool), pick, shovel, hoe, axe);
            if (ingot != null) addShapelessRecipe(new ItemStack(ingot, 9), block);
        }
        if (ore != null) GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
        if (dust != null) GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
    }

    protected void addAxe(Item axe, Item ingot) {
        addShapedRecipe(new ItemStack(axe), " ii", " si", " s ", 'i', ingot, 's', JourneyItems.obsidianRod);
    }

    protected void addPickaxe(Item pick, Item ingot) {
        addShapedRecipe(new ItemStack(pick), "iii", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianRod);
    }

    protected void addShovel(Item shovel, Item ingot) {
        addShapedRecipe(new ItemStack(shovel), " i ", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianRod);
    }

    protected void addSword(Item sword, Item ingot) {
        addShapedRecipe(new ItemStack(sword), " i ", " i ", " s ", 'i', ingot, 's', JourneyItems.obsidianRod);
    }

    protected void addHoe(Item hoe, Item ingot) {
        addShapedRecipe(new ItemStack(hoe), " ii", " s ", " s ", 'i', ingot, 's', JourneyItems.obsidianRod);
    }

    protected void addHelmet(Item helmet, Item ingot) {
        addShapedRecipe(new ItemStack(helmet), "iii", "i i", 'i', ingot);
    }

    protected void addChestplate(Item chest, Item ingot) {
        addShapedRecipe(new ItemStack(chest), "i i", "iii", "iii", 'i', ingot);
    }

    protected void addLeggings(Item legs, Item ingot) {
        addShapedRecipe(new ItemStack(legs), "iii", "i i", "i i", 'i', ingot);
    }

    protected void addBoots(Item boots, Item ingot) {
        addShapedRecipe(new ItemStack(boots), "i i", "i i", 'i', ingot);
    }
}