package net.journey.init;

import net.journey.JITL;
import net.journey.enums.EnumArmor;
import net.journey.items.ItemModArmor;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Registrar {
	public static final ArrayList<Item> JOURNEY_ITEMS = new ArrayList<>();

	public static <T extends Item> T regAndSetupItem(String key, String enName, T item) {
		return regAndSetupItem(key, enName, item, JourneyTabs.ITEMS);
	}

	public static <T extends Item> T regAndSetupItem(String key, String enName, T item, @Nullable CreativeTabs tab) {
		key = key.toLowerCase();

		item.setRegistryName(JITL.MOD_ID, key)
				.setTranslationKey(key)
				.setCreativeTab(tab);

		JOURNEY_ITEMS.add(item);

		LangGeneratorFacade.addItemEntry(item, enName);

		return item;
	}

	public static <T extends ItemModArmor> T regAndSetupArmor(T item) {
		return regAndSetupArmor(item, null);
	}

	/**
	 * @param langSuffix if lang suffix is null, it will be added automatically.
	 *                   You can put the mark %material% into it, that will be replaced by current material name.
	 */
	public static <T extends ItemModArmor> T regAndSetupArmor(T item, @Nullable String langSuffix) {
		EnumArmor armorMaterial = item.getArmorMaterialType();
		EntityEquipmentSlot type = item.armorType;

		String materialPrefix = armorMaterial.getType();

		String postfix;
		if (type == EntityEquipmentSlot.HEAD) {
			postfix = "Helmet";
		} else if (type == EntityEquipmentSlot.CHEST) {
			postfix = "Body";
		} else if (type == EntityEquipmentSlot.LEGS) {
			postfix = "Legs";
		} else if (type == EntityEquipmentSlot.FEET) {
			postfix = "Boots";
		} else {
			throw new IllegalStateException("Can't process such Equipment Slot Type: " + type + ". Available: " + EntityEquipmentSlot.HEAD + ", " + EntityEquipmentSlot.CHEST + ", " + EntityEquipmentSlot.LEGS + ", " + EntityEquipmentSlot.FEET);
		}

		String key = materialPrefix + postfix;

		item.setRegistryName(JITL.rl(key))
				.setTranslationKey(key)
				.setCreativeTab(JourneyTabs.ARMOR);

		JOURNEY_ITEMS.add(item);

		if (langSuffix != null) {
			LangGeneratorFacade.addArmorEntry(item, armorMaterial, langSuffix);
		} else {
			LangGeneratorFacade.addArmorEntry(item, armorMaterial);
		}

		return item;
	}

	public static void regStructure(String structureName, Class<? extends StructureStart> structureStartClass) {
		MapGenStructureIO.registerStructure(structureStartClass, structureName);
	}

	public static void regStructureComponent(String componentName, Class<? extends StructureComponent> componentClass) {
		MapGenStructureIO.registerStructureComponent(componentClass, componentName);
	}
}
