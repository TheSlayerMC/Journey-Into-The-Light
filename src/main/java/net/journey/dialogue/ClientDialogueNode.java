package net.journey.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class ClientDialogueNode {
	private final String textKey;
	private final List<String> optionTextKeys;
	private final EntityLivingBase npc;

	public ClientDialogueNode(ResourceLocation entityKey, String textKey, List<String> optionTextKeys) {
		this.textKey = textKey;
		this.optionTextKeys = optionTextKeys;

		EntityEntry entry = Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(entityKey));
		npc = (EntityLivingBase) entry.newInstance(Minecraft.getMinecraft().world);
	}

	public List<String> getOptionTextKeys() {
		return optionTextKeys;
	}

	public String getTextKey() {
		return textKey;
	}

	public EntityLivingBase getNpc() {
		return npc;
	}
}
