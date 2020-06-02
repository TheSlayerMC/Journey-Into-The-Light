package net.journey.items.interactive;

import net.journey.entity.mob.boss.*;
import net.journey.entity.mob.pet.EntityTameRoc;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.items.base.JItem;
import net.journey.util.ChatUtils;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.SlayerAPI.Colour;

import java.util.List;

public class ItemBossSpawner extends JItem {

    public String dimName;
    public int dimID;

    public ItemBossSpawner(int dimID, String name, String f, String dimName) {
        super(name, f, JourneyTabs.SPAWNERS);
        setMaxStackSize(1);
        this.dimID = dimID;
        this.dimName = dimName;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer p, World w, BlockPos pos, EnumHand hand, EnumFacing fa, float par8, float par9, float par10) {
        ItemStack i = p.getHeldItem(hand);
        Item item = i.getItem();
        if (!w.isRemote) {
            if (w.provider.getDimension() == dimID) {
                EntityBlazier blaze = new EntityBlazier(w);
                EntitySoulWatcher soul = new EntitySoulWatcher(w);
                EntitySentryKing sentry = new EntitySentryKing(w);
                EntitySkyStalker sky = new EntitySkyStalker(w);
                EntityTameRoc roc = new EntityTameRoc(w, p);
                EntityScale scale = new EntityScale(w);
                EntityCorallator corallator = new EntityCorallator(w);
                EntityLogger logger = new EntityLogger(w);
                EntityThunderbird thunder = new EntityThunderbird(w);
                EntityTerranianProtector terrastar = new EntityTerranianProtector(w);

                if (item == JourneyItems.blazierOrb) {
                    blaze.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, blaze.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    SlayerAPI.addFormattedChatMessage(p, "You're playing with hot fire. It's not too late to turn back...");
                    w.spawnEntity(blaze);
                }
                if (item == JourneyItems.rocSpawnEgg) {
                    roc.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, roc.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(roc);
                    //p.triggerAchievement(JourneyAchievements.achievementRoc);
                }
                if (item == JourneyItems.sentryKingOrb) {
                    sentry.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, sentry.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "It sucked all the energy out of this world, don't let it suck the energy out of you...");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(sentry);
                }
                if (item == JourneyItems.mysteriousDisk) {
                    sky.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, sky.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "The purple flying fish monster is not happy...");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(sky);
                }
                if (item == JourneyItems.corallatorOrb) {
                    corallator.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, corallator.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "You will regret this mistake for the rest of your life - if you'll still have one, that is...");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(corallator);
                }
                if (item == JourneyItems.loggerOrb) {
                    logger.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, logger.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "You'll get chopped to pieces with this one...");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(logger);
                }
                if (item == JourneyItems.scaleOrb) {
                    scale.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, scale.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "(The blue blubby fish monster has been summoned!");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(scale);
                }
                EntityEudor eudor = new EntityEudor(w);
                if (item == JourneyItems.eudorOrb) {
                    eudor.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, eudor.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    SlayerAPI.addFormattedChatMessage(p, "The King has been summoned");
                    w.spawnEntity(eudor);
                }
                if (item == JourneyItems.thunderbirdOrb) {
                    thunder.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, thunder.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "The thunderbird is not pleased with its awakening...");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(thunder);
                }
                if (item == JourneyItems.enchantedTerrastar) {
                    terrastar.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, terrastar.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    SlayerAPI.addFormattedChatMessage(p, "It's sole purpose was to protect this land. Why would you try to destroy it?");
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    w.spawnEntity(terrastar);

                }
                if (!p.capabilities.isCreativeMode) i.shrink(1);
            } else {
                SlayerAPI.addChatMessage(p, Colour.GREEN + "Cannot be called upon unless in the " + dimName + " dimension.");
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack i, World worldIn, List<String> list, ITooltipFlag flagIn) {
        Item item = i.getItem();
        if (item == JourneyItems.blazierOrb) list.add(LangHelper.setBossSpawner("Blazier"));
        if (item == JourneyItems.sentryKingOrb) list.add(LangHelper.setBossSpawner("Sentry King"));
        if (item == JourneyItems.mysteriousDisk) list.add(LangHelper.setBossSpawner("Sky Stalker"));
        if (item == JourneyItems.corallatorOrb) list.add(LangHelper.setBossSpawner("Corallator"));
        if (item == JourneyItems.loggerOrb) list.add(LangHelper.setBossSpawner("Logger"));
        if (item == JourneyItems.scaleOrb) list.add(LangHelper.setBossSpawner("Scale"));
        if (item == JourneyItems.thunderbirdOrb) list.add(LangHelper.setBossSpawner("Thunderbird"));
        if (item == JourneyItems.enchantedTerrastar) list.add(LangHelper.setBossSpawner("Terranian Protector"));
        if (item == JourneyItems.rocSpawnEgg) list.add(LangHelper.setPetSpawner("Roc"));
        if (item == JourneyItems.rocSpawnEgg)
            list.add(SlayerAPI.Colour.DARK_GREEN + "More powerful than a dog, less cuddly.");

    }
}
