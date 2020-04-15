package net.journey.items;

import net.journey.entity.mob.boss.EntityCalcia;
import net.journey.entity.mob.boss.EntityNetherBeast;
import net.journey.entity.mob.boss.EntitySoulWatcher;
import net.journey.entity.mob.boss.EntityWitheringBeast;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.SlayerAPI.Colour;
import net.slayer.api.item.ItemMod;

import java.util.List;

public class ItemNetherBossSpawner extends ItemMod {

    public ItemNetherBossSpawner(String name, String f) {
        super(name, f, JourneyTabs.SPAWNERS);
        setMaxStackSize(1);
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer p, World w, BlockPos pos, EnumHand hand, EnumFacing fa, float par8, float par9, float par10) {
        ItemStack i = p.getHeldItem(hand);
        Item item = i.getItem();
        if (!w.isRemote)
            if (w.provider.getDimension() == -1) {
                EntityLightningBall light = new EntityLightningBall(w);
                EntityWitheringBeast wither = new EntityWitheringBeast(w);
                EntitySoulWatcher soul = new EntitySoulWatcher(w);
                EntityCalcia calcia = new EntityCalcia(w);
                EntityNetherBeast nether = new EntityNetherBeast(w);

                if (item == JourneyItems.calciaOrb) {
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    light.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    w.spawnEntity(light);
                    SlayerAPI.sendMessageToAll("Calcia has been summoned", true);
                    calcia.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    w.spawnEntity(calcia);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.netherBeastOrb) {
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    light.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    w.spawnEntity(light);
                    SlayerAPI.sendMessageToAll("The Nether Beast has been summoned", true);
                    nether.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    w.spawnEntity(nether);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.witheringBeastOrb) {
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    light.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    w.spawnEntity(light);
                    SlayerAPI.sendMessageToAll("The Withering Beast has been summoned", true);
                    wither.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    w.spawnEntity(wither);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.soulWatcherOrb) {
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    light.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    w.spawnEntity(light);
                    SlayerAPI.sendMessageToAll("The Soul Watcher has been summoned", true);
                    soul.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    w.spawnEntity(soul);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                } else {
                    SlayerAPI.addChatMessage(p, Colour.GREEN + "Cannot be spawned unless in the Nether.");
                }
            }
        return EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack i, World worldIn, List<String> list, ITooltipFlag flagIn) {
        Item item = i.getItem();
        String spawn = "ERROR";
        if (item == JourneyItems.calciaOrb) spawn = "Calcia";
        if (item == JourneyItems.netherBeastOrb) spawn = "Nether Beast";
        if (item == JourneyItems.witheringBeastOrb) spawn = "Withering Beast";
        if (item == JourneyItems.soulWatcherOrb) spawn = "Soul Watcher";
        list.add("Spawns the boss: " + spawn);
    }
}