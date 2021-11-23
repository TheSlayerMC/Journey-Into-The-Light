package net.jitl.init;

import net.jitl.JITL;
import net.minecraftforge.fml.RegistryObject;
import ru.timeconqueror.timecore.api.common.sound.TimeSound;
import ru.timeconqueror.timecore.api.registry.SoundRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JSounds {

    @AutoRegistrable
    private static final SoundRegister REGISTER = new SoundRegister(JITL.MODID);

    public static final RegistryObject<TimeSound> ICE_CRYSTAL_BREAK = REGISTER.register("block.ice_crystal.break");

    public static final RegistryObject<TimeSound> MUD_BLOCK_BREAK = REGISTER.register("block.mud.break");
    public static final RegistryObject<TimeSound> MUD_BLOCK_DIG = REGISTER.register("block.mud.dig");

    public static final RegistryObject<TimeSound> LUNIUM_ORE_BREAK = REGISTER.register("block.lunium_ore.break");
    public static final RegistryObject<TimeSound> LUNIUM_BLOCK_BREAK = REGISTER.register("block.lunium_block.break");
    public static final RegistryObject<TimeSound> LUNIUM_BLOCK_DIG = REGISTER.register("block.lunium_block.dig");

    public static final RegistryObject<TimeSound> SHADIUM_ORE_BREAK = REGISTER.register("block.shadium_ore.break");
    public static final RegistryObject<TimeSound> SHADIUM_BLOCK_BREAK = REGISTER.register("block.shadium_block.break");
    public static final RegistryObject<TimeSound> SHADIUM_BLOCK_DIG = REGISTER.register("block.shadium_block.dig");

    public static final RegistryObject<TimeSound> BRICK_BLOCK_BREAK = REGISTER.register("block.brick.break");
    public static final RegistryObject<TimeSound> BRICK_BLOCK_DIG = REGISTER.register("block.brick.dig");

    public static final RegistryObject<TimeSound> FUMICE_BLOCK_BREAK = REGISTER.register("block.fumice.break");
    public static final RegistryObject<TimeSound> FUMICE_BLOCK_DIG = REGISTER.register("block.fumice.dig");

    public static final RegistryObject<TimeSound> RUNE_ACTIVATE = REGISTER.register("block.rune.activate");
    public static final RegistryObject<TimeSound> ESSENCIA_ALTAR_ACTIVATE = REGISTER.register("block.essencia_altar.activate");

    public static final RegistryObject<TimeSound> STAFF_0 = REGISTER.register("items.staff.staff_0");
    public static final RegistryObject<TimeSound> LOOT = REGISTER.register("items.loot");
    public static final RegistryObject<TimeSound> MINERS_PEARL = REGISTER.register("items.miners_pearl");

    public static final RegistryObject<TimeSound> HONGO_AMBIENT = REGISTER.register("entities.hongo_ambient");
    public static final RegistryObject<TimeSound> HONGO_HURT = REGISTER.register("entities.hongo_hurt");
    public static final RegistryObject<TimeSound> HONGO_DEATH = REGISTER.register("entities.hongo_death");
    public static final RegistryObject<TimeSound> HONGO_SPORE_RELEASE = REGISTER.register("entities.hongo_spore_release");

    public static final RegistryObject<TimeSound> GLUMP_AMBIENT = REGISTER.register("entities.glump_ambient");
    public static final RegistryObject<TimeSound> GLUMP_HURT = REGISTER.register("entities.glump_hurt");
    public static final RegistryObject<TimeSound> GLUMP_DEATH = REGISTER.register("entities.glump_death");

    public static final RegistryObject<TimeSound> ILLAGER_MECH_STEP = REGISTER.register("entities.illager_mech_step");
    public static final RegistryObject<TimeSound> ILLAGER_MECH_HURT = REGISTER.register("entities.illager_mech_hurt");
    public static final RegistryObject<TimeSound> ILLAGER_MECH_DEATH = REGISTER.register("entities.illager_mech_death");
    public static final RegistryObject<TimeSound> ILLAGER_MECH_THROW = REGISTER.register("entities.illager_mech_throw");

    public static final RegistryObject<TimeSound> KNIFE = REGISTER.register("entities.knife");
    public static final RegistryObject<TimeSound> PIERCER = REGISTER.register("entities.piercer");

    public static final RegistryObject<TimeSound> MENU_MUSIC = REGISTER.register("music.menu");
    public static final RegistryObject<TimeSound> TOWER_THEME = REGISTER.register("music.guardian_tower");
    public static final RegistryObject<TimeSound> TEMPLE_GUARDIAN_MUSIC = REGISTER.register("music.temple_guardian");
    public static final RegistryObject<TimeSound> EUCA_AMBIENCE = REGISTER.register("music.euca.ambience");
    public static final RegistryObject<TimeSound> GOLD_PLAINS_MUSIC = REGISTER.register("music.biome.gold_plains");
}