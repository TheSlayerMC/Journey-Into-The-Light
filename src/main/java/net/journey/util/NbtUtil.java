package net.journey.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.slayer.api.SlayerAPI;

import javax.annotation.Nullable;

public class NbtUtil {
    /**
     * Read tag from persistent entity data
     *
     * @param entity - current entity
     * @param name   - name of tag
     * @return
     */
    @Nullable
    public static <T extends NBTBase> T readTagFromEntity(Entity entity, String name, Class<T> clazz) {
        if (entity == null || name == null || name.isEmpty())
            return null;

        NBTTagCompound data = entity.getEntityData();
        name = correctName(name);
        if (!data.hasKey(name))
            return null;

        NBTBase tag = data.getTag(name);
        if (tag.getClass() != clazz)
            return null;

        return (T) tag;
    }

    /**
     * Writes data to persistent entity tag
     *
     * @param e    - current entity
     * @param tag  - tag to set
     * @param name - name of tag
     */
    public static void writeToEntity(Entity e, NBTBase tag, String name) {
        if (e == null || tag == null || name == null || name.isEmpty())
            return;

        NBTTagCompound data = e.getEntityData();
        name = correctName(name);

        data.setTag(name, tag);
    }

    /**
     * To prevent from overriding important information , we need to create unique name.
     * Will use mod ID
     *
     * @param name - name of tag
     * @return
     */
    private static String correctName(String name) {
        return SlayerAPI.MOD_ID + "_" + name;
    }
}
