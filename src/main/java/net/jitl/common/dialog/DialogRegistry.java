package net.jitl.common.dialog;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;

//TODO class for saving/restoring, not yet needed
public class DialogRegistry {
    private final HashMap<ResourceLocation, Dialog> dialogs = new HashMap<>();

    public Dialog register(Dialog dialog) {
        if (dialogs.put(dialog.getName(), dialog) != null) {
            throw new IllegalArgumentException(String.format("Dialogue with name '%s' has been already registered!", dialog.getName()));
        }

        return dialog;
    }

    @Nullable
    public Dialog getDialog(ResourceLocation name) {
        return dialogs.get(name);
    }
}