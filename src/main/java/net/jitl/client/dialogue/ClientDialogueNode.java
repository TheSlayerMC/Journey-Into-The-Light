package net.jitl.client.dialogue;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class ClientDialogueNode {
    private final String textKey;
    private final List<String> optionTextKeys;
    private final LivingEntity npc;

    public ClientDialogueNode(ResourceLocation entityKey, String textKey, List<String> optionTextKeys) {
        this.textKey = textKey;
        this.optionTextKeys = optionTextKeys;

        EntityType entityType = Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(entityKey));
        assert Minecraft.getInstance().level != null;
        npc = (LivingEntity) entityType.create(Minecraft.getInstance().level);
    }

    public List<String> getOptionTextKeys() {
        return optionTextKeys;
    }

    public String getTextKey() {
        return textKey;
    }

    public LivingEntity getNpc() {
        return npc;
    }
}