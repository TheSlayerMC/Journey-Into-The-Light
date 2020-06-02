package net.journey.items.interactive;

import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.items.base.JItem;
import net.journey.util.ChatUtils;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;

public class ItemBossSpawner extends JItem {
	private Supplier<DimensionType> accessibleDimSupplier;
	private Class<? extends Entity> entityClass;

	/**
	 * Constructor to create an entity. Will be null, if there is an error while searching the constructor of class.
	 */
	private Constructor<? extends Entity> entityConstructor;

	private String langKey;

	public ItemBossSpawner(String key, String enName, Supplier<DimensionType> accessibleDimSupplier, Class<? extends Entity> entityToSummonClass) {
		super(key, enName, JourneyTabs.SPAWNERS);
		this.accessibleDimSupplier = accessibleDimSupplier;
		this.entityClass = entityToSummonClass;

		try {
			this.entityConstructor = entityClass.getConstructor(World.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		setMaxStackSize(1);
	}

	public ItemBossSpawner setBossSummonMessage(String langKey) {
		this.langKey = langKey;
		return this;
	}

	@NotNull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		DimensionType dimensionType = accessibleDimSupplier.get();

		if (!world.isRemote) {
			if (world.provider.getDimensionType() == dimensionType) {
				Entity entity = null;

				try {
					entity = entityConstructor.newInstance(world);
				} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
					@SuppressWarnings("unchecked")
					Pair<String, Object>[] extra = new Pair[]{Pair.of("Item", this), Pair.of("Entity Constructor", entityConstructor != null ? "Found" : "Not found")};
					ChatUtils.sendInformativeError(player, "Something went wrong in attempt to spawn " + entityClass, e, extra);
				}

				if (entity == null) return EnumActionResult.SUCCESS;

				entity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
				if (!world.getCollisionBoxes(null, entity.getEntityBoundingBox()).isEmpty()) {
					ChatUtils.sendColoredTranslated(player, TextFormatting.DARK_PURPLE, "msg.journey.summon.no_space");
					return EnumActionResult.SUCCESS;
				}

				JourneySounds.playSound(JourneySounds.SUMMON_BOSS, world, player);
				if (langKey != null) ChatUtils.sendColoredTranslated(player, TextFormatting.GOLD, langKey);
				world.spawnEntity(entity);

				if (!player.capabilities.isCreativeMode) player.getHeldItem(hand).shrink(1);
			} else {
				ChatUtils.sendColoredTranslated(player, TextFormatting.DARK_PURPLE, "msg.journey.summon.wrong_dimension", new TextComponentTranslation(LangHelper.createI18nKey(dimensionType)));
			}
		}

		return EnumActionResult.SUCCESS;
	}

	@Override
	public void addInformation(ItemStack i, World worldIn, List<String> list, ITooltipFlag flagIn) {
		Item item = i.getItem();
		String spawn = "ERROR";
		if (item == JourneyItems.calciaOrb) spawn = "Calcia";
		if (item == JourneyItems.netherBeastOrb) spawn = "Nether Beast";
		if (item == JourneyItems.witheringBeastOrb) spawn = "Withering Beast";
		if (item == JourneyItems.soulWatcherOrb) spawn = "Soul Watcher";
		if (!spawn.equals("ERROR")) list.add("Spawns the boss: " + spawn);

		if (item == JourneyItems.blazierOrb) list.add(LangHelper.setBossSpawner("Blazier"));
		if (item == JourneyItems.sentryKingOrb) list.add(LangHelper.setBossSpawner("Sentry King"));
		if (item == JourneyItems.mysteriousDisk) list.add(LangHelper.setBossSpawner("Sky Stalker"));
		if (item == JourneyItems.corallatorOrb) list.add(LangHelper.setBossSpawner("Corallator"));
		if (item == JourneyItems.loggerOrb) list.add(LangHelper.setBossSpawner("Logger"));
		if (item == JourneyItems.scaleOrb) list.add(LangHelper.setBossSpawner("Scale"));
		if (item == JourneyItems.thunderbirdOrb) list.add(LangHelper.setBossSpawner("Thunderbird"));
		if (item == JourneyItems.enchantedTerrastar) list.add(LangHelper.setBossSpawner("Terranian Protector"));
			list.add(SlayerAPI.Colour.DARK_GREEN + "More powerful than a dog, less cuddly.");

	}
}
