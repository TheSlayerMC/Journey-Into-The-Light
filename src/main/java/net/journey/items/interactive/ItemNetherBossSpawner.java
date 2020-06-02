package net.journey.items.interactive;

import net.journey.entity.mob.boss.EntityCalcia;
import net.journey.entity.mob.boss.EntityNetherBeast;
import net.journey.entity.mob.boss.EntitySoulWatcher;
import net.journey.entity.mob.boss.EntityWitheringBeast;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.items.base.JItem;
import net.journey.util.ChatUtils;
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

import java.util.List;

public class ItemNetherBossSpawner extends JItem {

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
                    calcia.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, calcia.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    ChatUtils.sendColored(p, TextFormatting.RED, "Calcia has been summoned");
                    w.spawnEntity(calcia);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.netherBeastOrb) {
                    nether.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, nether.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    ChatUtils.sendColored(p, TextFormatting.RED, "The Nether Beast has been summoned");
                    w.spawnEntity(nether);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.witheringBeastOrb) {
                    wither.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, wither.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    ChatUtils.sendColored(p, TextFormatting.RED, "The Withering Beast has been summoned");
                    w.spawnEntity(wither);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                }
                if (item == JourneyItems.soulWatcherOrb) {
                    soul.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                    if (!w.getCollisionBoxes(null, soul.getEntityBoundingBox()).isEmpty()) {
                        ChatUtils.sendColored(p, TextFormatting.DARK_PURPLE, "Not enough space for boss to spawn");
                        return EnumActionResult.PASS;
                    }
                    JourneySounds.playSound(JourneySounds.SUMMON_BOSS, w, p);
                    ChatUtils.sendColored(p, TextFormatting.RED, "The Soul Watcher has been summoned");
                    w.spawnEntity(soul);
                    if (!p.capabilities.isCreativeMode) i.shrink(1);
                } else {
                    ChatUtils.sendColored(p, TextFormatting.RED, "Cannot be spawned unless in the Nether.");
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