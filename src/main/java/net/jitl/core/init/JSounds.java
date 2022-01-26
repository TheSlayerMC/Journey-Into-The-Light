package net.jitl.core.init;

import net.jitl.core.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SoundRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JSounds {

    @AutoRegistrable
    private static final SoundRegister REGISTER = new SoundRegister(JITL.MODID);

    public static final RegistryObject<SoundEvent> ICE_CRYSTAL_BREAK = REGISTER.register("block.ice_crystal.break");
    public static final RegistryObject<SoundEvent> TOAST = REGISTER.register("music.toast");
    public static final RegistryObject<SoundEvent> TOAST_SPECIAL = REGISTER.register("music.toast_special");

    public static final RegistryObject<SoundEvent> MUD_BLOCK_BREAK = REGISTER.register("block.mud.break");
    public static final RegistryObject<SoundEvent> MUD_BLOCK_DIG = REGISTER.register("block.mud.dig");

    public static final RegistryObject<SoundEvent> POTTERY_BREAK = REGISTER.register("block.pottery.break");
    public static final RegistryObject<SoundEvent> POTTERY_DIG = REGISTER.register("block.pottery.dig");
    public static final RegistryObject<SoundEvent> BOTTLE_PLUG = REGISTER.register("items.bottle_plug");

    public static final RegistryObject<SoundEvent> GRASSY_PERMAFROST_BREAK = REGISTER.register("block.grassy_permafrost.break");
    public static final RegistryObject<SoundEvent> GRASSY_PERMAFROST_DIG = REGISTER.register("block.grassy_permafrost.dig");

    public static final RegistryObject<SoundEvent> CRUMBLED_PERMAFROST_BREAK = REGISTER.register("block.crumbled_permafrost.break");
    public static final RegistryObject<SoundEvent> CRUMBLED_PERMAFROST_DIG = REGISTER.register("block.crumbled_permafrost.dig");

    public static final RegistryObject<SoundEvent> PERMAFROST_BREAK = REGISTER.register("block.permafrost.break");
    public static final RegistryObject<SoundEvent> PERMAFROST_DIG = REGISTER.register("block.permafrost.dig");

    public static final RegistryObject<SoundEvent> LUNIUM_ORE_BREAK = REGISTER.register("block.lunium_ore.break");
    public static final RegistryObject<SoundEvent> LUNIUM_BLOCK_BREAK = REGISTER.register("block.lunium_block.break");
    public static final RegistryObject<SoundEvent> LUNIUM_BLOCK_DIG = REGISTER.register("block.lunium_block.dig");

    public static final RegistryObject<SoundEvent> SHADIUM_ORE_BREAK = REGISTER.register("block.shadium_ore.break");
    public static final RegistryObject<SoundEvent> SHADIUM_BLOCK_BREAK = REGISTER.register("block.shadium_block.break");
    public static final RegistryObject<SoundEvent> SHADIUM_BLOCK_DIG = REGISTER.register("block.shadium_block.dig");

    public static final RegistryObject<SoundEvent> BRICK_BLOCK_BREAK = REGISTER.register("block.brick.break");
    public static final RegistryObject<SoundEvent> BRICK_BLOCK_DIG = REGISTER.register("block.brick.dig");

    public static final RegistryObject<SoundEvent> FUMICE_BLOCK_BREAK = REGISTER.register("block.fumice.break");
    public static final RegistryObject<SoundEvent> FUMICE_BLOCK_DIG = REGISTER.register("block.fumice.dig");

    public static final RegistryObject<SoundEvent> RUNE_ACTIVATE = REGISTER.register("block.rune.activate");
    public static final RegistryObject<SoundEvent> ESSENCIA_ALTAR_ACTIVATE = REGISTER.register("block.essencia_altar.activate");

    public static final RegistryObject<SoundEvent> CRYSTAL_APPLE_FREEZE = REGISTER.register("items.crystal_apple.freeze");
    public static final RegistryObject<SoundEvent> CRYSTAL_APPLE_UNFREEZE = REGISTER.register("items.crystal_apple.unfreeze");

    public static final RegistryObject<SoundEvent> STAFF_0 = REGISTER.register("items.staff.staff_0");
    public static final RegistryObject<SoundEvent> LOOT = REGISTER.register("items.loot");
    public static final RegistryObject<SoundEvent> MINERS_PEARL = REGISTER.register("items.miners_pearl");

    public static final RegistryObject<SoundEvent> HONGO_AMBIENT = REGISTER.register("entities.hongo_ambient");
    public static final RegistryObject<SoundEvent> HONGO_HURT = REGISTER.register("entities.hongo_hurt");
    public static final RegistryObject<SoundEvent> HONGO_DEATH = REGISTER.register("entities.hongo_death");
    public static final RegistryObject<SoundEvent> HONGO_SPORE_RELEASE = REGISTER.register("entities.hongo_spore_release");

    public static final RegistryObject<SoundEvent> GLUMP_AMBIENT = REGISTER.register("entities.glump_ambient");
    public static final RegistryObject<SoundEvent> GLUMP_HURT = REGISTER.register("entities.glump_hurt");
    public static final RegistryObject<SoundEvent> GLUMP_DEATH = REGISTER.register("entities.glump_death");

    public static final RegistryObject<SoundEvent> FROZEN_TROLL_AMBIENT = REGISTER.register("entities.frozen_troll_ambient");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_HURT = REGISTER.register("entities.frozen_troll_hurt");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_DEATH = REGISTER.register("entities.frozen_troll_death");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_INTRIGUED = REGISTER.register("entities.frozen_troll_intrigued");

    public static final RegistryObject<SoundEvent> FROZEN_GUARDIAN_DEATH = REGISTER.register("entities.frozen_guardian_death");

    public static final RegistryObject<SoundEvent> ILLAGER_MECH_STEP = REGISTER.register("entities.illager_mech_step");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_HURT = REGISTER.register("entities.illager_mech_hurt");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_DEATH = REGISTER.register("entities.illager_mech_death");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_THROW = REGISTER.register("entities.illager_mech_throw");

    public static final RegistryObject<SoundEvent> KNIFE = REGISTER.register("entities.knife");
    public static final RegistryObject<SoundEvent> PIERCER = REGISTER.register("entities.piercer");
    public static final RegistryObject<SoundEvent> PIERCER_RETURN = REGISTER.register("entities.piercer_return");

    public static final RegistryObject<SoundEvent> MENU_MUSIC = REGISTER.register("music.menu");
    public static final RegistryObject<SoundEvent> TOWER_THEME = REGISTER.register("music.guardian_tower");
    public static final RegistryObject<SoundEvent> TEMPLE_GUARDIAN_MUSIC = REGISTER.register("music.temple_guardian");
    public static final RegistryObject<SoundEvent> EUCA_AMBIENCE = REGISTER.register("music.euca.ambience");
    public static final RegistryObject<SoundEvent> GOLD_PLAINS_MUSIC = REGISTER.register("music.biome.gold_plains");

    public static final RegistryObject<SoundEvent> HAUNT_MUSKIE_2 = REGISTER.register("music.haunt_muskie_2");
    public static final RegistryObject<SoundEvent> SNOWFLAKESSS = REGISTER.register("music.snowflakesss");
}