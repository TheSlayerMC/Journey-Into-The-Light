package net.slayer.api.client.gui;

import net.journey.JITL;
import net.journey.entity.mob.boiling.npc.EntityBoilTrader;
import net.journey.entity.mob.boiling.npc.EntityEscapedConvict;
import net.journey.entity.mob.cloudia.npc.EntityStarlightBlacksmith;
import net.journey.entity.mob.cloudia.npc.EntityStarlightVillager;
import net.journey.entity.mob.corba.npc.EntityOvergrownMerchant;
import net.journey.entity.mob.corba.npc.EntityTordo;
import net.journey.entity.mob.depths.npc.EntityStaringGuardian;
import net.journey.entity.mob.euca.npc.EntityAlloyMender;
import net.journey.entity.mob.frozen.npc.EntityFrozenMerchant;
import net.journey.entity.mob.overworld.npc.EntityBlacksmith;
import net.journey.entity.mob.overworld.npc.EntityMage;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
import net.journey.entity.mob.terrania.npc.EntityTerranianTrader;
import net.journey.util.LangHelper;
import net.journey.util.Lazy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.slayer.api.entity.tileentity.container.JContainerMerchant;
import ru.timeconqueror.timecore.api.util.Wrapper;

import java.awt.*;
import java.util.Optional;

import static net.slayer.api.client.gui.MerchantGuis.GuiFactory.defaultFactory;

public enum MerchantGuis {
	ESCAPED(EntityEscapedConvict.class, "escapedConvict", defaultFactory(Color.WHITE)),
	MAGE(EntityMage.class, "mage", defaultFactory(Color.GRAY)),
	BLACKSMITH(EntityBlacksmith.class, "blacksmith", defaultFactory(Color.GRAY)),
	FROZEN_MERCHANT(EntityFrozenMerchant.class, "frozenMerchant", defaultFactory(Color.GRAY)),
	STARING_GUARDIAN(EntityStaringGuardian.class, "staringGuardian", defaultFactory(Color.WHITE)),
	TORDO(EntityTordo.class, "tordo", defaultFactory(Color.WHITE)),
	BOIL_TRADER(EntityBoilTrader.class, "boilTrader", defaultFactory(Color.GRAY)),
	ALLOY_MENDER(EntityAlloyMender.class, "alloyMender", defaultFactory(Color.GRAY)),
	STARLIGHT_VILLAGER(EntityStarlightVillager.class, "starlightVillager", defaultFactory(Color.WHITE)),
	STARLIGHT_BLACKSMITH(EntityStarlightBlacksmith.class, "starlightBlacksmith", defaultFactory(Color.WHITE)),
	TERRANIAN_TRADER(EntityTerranianTrader.class, "starlightBlacksmith", defaultFactory(Color.WHITE)),
	OVERGROWN_MERCHANT(EntityOvergrownMerchant.class, "overgrownMerchant", defaultFactory(Color.WHITE)),
	ROCKITE_GOLEM(EntityRockiteGolem.class, "rockiteGolem", defaultFactory(Color.WHITE));


	private final Lazy<String> entityNameKey;
	private final ResourceLocation guiTexture;
	private final GuiFactory factory;

	/**
	 * @param entityClass will be later used to find entity translation key,
	 * @param textureName name of the texture, full path: {@code "journey:textures/gui/" + textureName + ".png"}
	 * @param factory     factory for creating specified gui
	 */
	MerchantGuis(Class<? extends Entity> entityClass, String textureName, GuiFactory factory) {
		this.entityNameKey = () -> {
			Wrapper<String> name = new Wrapper<>("");
			Optional<EntityRegistry.EntityRegistration> entityEntry = getEntityEntry(entityClass);

			entityEntry.ifPresent(entry -> name.set(LangHelper.createTranslationKey(entry)));
			entityEntry.orElseThrow(() -> new IllegalArgumentException("Entity " + entityClass + " is not (or still not) registered."));

			return name.get();
		};

		this.guiTexture = new ResourceLocation(JITL.MOD_ID, "textures/gui/" + textureName + ".png");
		this.factory = factory;
	}

	public GuiContainer create(JContainerMerchant container, IMerchant merchant) {
		return factory.create(container, merchant, entityNameKey.get(), guiTexture);
	}

	interface GuiFactory {
		GuiContainer create(JContainerMerchant container, IMerchant merchant, String guiTranslationKey, ResourceLocation guiTexture);

		static GuiFactory defaultFactory(Color color) {
			return (container, merchant, guiTranslationKey, guiTexture) -> new JGuiMerchant(container, merchant, guiTranslationKey, guiTexture, color);
		}
	}

	private static Optional<EntityRegistry.EntityRegistration> getEntityEntry(Class<? extends Entity> entityClass) {
		return Optional.ofNullable(EntityRegistry.instance().lookupModSpawn(entityClass, false));
	}
}
