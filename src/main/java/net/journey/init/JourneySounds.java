package net.journey.init;

import net.journey.JITL;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class JourneySounds {
    private static List<SoundEvent> soundsToReg = new ArrayList<>();

    public static final SoundEvent CAVE_MOB = registerSound("cave_mob");
    public static final SoundEvent ROCK = registerSound("rock");
    public static final SoundEvent KNIFE = registerSound("knife");
    public static final SoundEvent BASE_MOB_HURT = registerSound("base_mob_hurt");
    public static final SoundEvent BOSS_DEATH = registerSound("boss_death");
    public static final SoundEvent TERRA_SLUG = registerSound("terraslug");
    public static final SoundEvent TERRA_SLUG_HURT = registerSound("terraslug_hurt");
    public static final SoundEvent TERRA_SLUG_DEATH = registerSound("terraslug_death");
    public static final SoundEvent SUMMON_BOSS = registerSound("summon_boss");
    public static final SoundEvent SUMMON_TABLE = registerSound("summon_table");
    public static final SoundEvent SORCERER_DEATH = registerSound("sorcerer_death");
    public static final SoundEvent SORCERER_HURT = registerSound("sorcerer_hurt");
    public static final SoundEvent SORCERER = registerSound("sorcerer");
    public static final SoundEvent CHEST_OPEN_0 = registerSound("chest_open_0");
    public static final SoundEvent CHEST_OPEN = registerSound("chest_open");
    public static final SoundEvent CHEST_CLOSE = registerSound("chest_close");
    public static final SoundEvent ROBOT = registerSound("robot");
    public static final SoundEvent ROBOT_HURT = registerSound("robot_hurt");
    public static final SoundEvent ROBOT_DEATH = registerSound("robot_death");
    public static final SoundEvent PSYOLLOM = registerSound("psyollom");
    public static final SoundEvent PSYOLLOM_HURT = registerSound("psyollom_hurt");
    public static final SoundEvent INSECTO = registerSound("insecto");
    public static final SoundEvent INSECTO_HURT = registerSound("insecto_hurt");
    public static final SoundEvent SPIKED_BEAST = registerSound("spiked_beast");
    public static final SoundEvent SPIKED_BEAST_HURT = registerSound("spiked_beast_hurt");
    public static final SoundEvent MAGMA_GIANT = registerSound("magma_giant");
    public static final SoundEvent MAGMA_GIANT_HURT = registerSound("magma_giant_hurt");
    public static final SoundEvent SPYCLOPS = registerSound("spyclops");
    public static final SoundEvent SPYCLOPS_HURT = registerSound("spyclops_hurt");
    public static final SoundEvent TURTLE_HURT = registerSound("turtle_hurt");
    public static final SoundEvent HONGO = registerSound("hongo");
    public static final SoundEvent SMALL_HONGO = registerSound("small_hongo");
    public static final SoundEvent SMALL_HONGO_HURT = registerSound("small_hongo_hurt");
    public static final SoundEvent HONGO_HURT = registerSound("hongo_hurt");
    public static final SoundEvent REAPER = registerSound("reaper");
    public static final SoundEvent REAPER_HURT = registerSound("reaper_hurt");
    public static final SoundEvent SAND_CRAWLER = registerSound("sand_crawler");
    public static final SoundEvent DEPTHS_HUNTER = registerSound("depths_hunter");
    public static final SoundEvent DEPTHS_HUNTER_HURT = registerSound("depths_hunter_hurt");
    public static final SoundEvent BEAST_OF_THE_NETHER = registerSound("beast_of_the_nether");
    public static final SoundEvent BEAST_OF_THE_NETHER_HURT = registerSound("beast_of_the_nether_hurt");
    public static final SoundEvent CALCIA = registerSound("calcia");
    public static final SoundEvent CALCIA_HURT = registerSound("calcia_hurt");
    public static final SoundEvent STAFF = registerSound("staff");
    public static final SoundEvent WRAPPER = registerSound("wrapper");
    public static final SoundEvent PAGE_FLIP = registerSound("page_flip");
    public static final SoundEvent CRYSTAL_ALL_RETRIEVED = registerSound("crystal");
    public static final SoundEvent CRYSTAL_ERROR = registerSound("crystal_error");
    public static final SoundEvent MAGIC_SPARKLE = registerSound("magic_sparkle");
    public static final SoundEvent CANNON = registerSound("cannon");
    public static final SoundEvent PLASMA = registerSound("plasma");
    public static final SoundEvent DYNASTER = registerSound("dynaster");
    public static final SoundEvent DYNASTER_HURT = registerSound("dynaster_hurt");
    public static final SoundEvent DYNASTER_DEATH = registerSound("dynaster_death");
    public static final SoundEvent SHIMMERER = registerSound("shimmerer");
    public static final SoundEvent SHIMMERER_HURT = registerSound("shimmerer_hurt");
    public static final SoundEvent SHIMMERER_DEATH = registerSound("shimmerer_death");
    public static final SoundEvent BUSH = registerSound("bush");
    public static final SoundEvent BUSH_HURT = registerSound("bush_hurt");
    public static final SoundEvent BUSH_DEATH = registerSound("bush_death");
    public static final SoundEvent WRAITH = registerSound("wraith");
    public static final SoundEvent WRAITH_DEATH = registerSound("wraith_death");
    public static final SoundEvent WRAITH_HURT = registerSound("wraith_hurt");
    public static final SoundEvent OVERSEER = registerSound("overseer");
    public static final SoundEvent OVERSEER_HURT = registerSound("overseer_hurt");
    public static final SoundEvent OVERSEER_DEATH = registerSound("overseer_death");
    public static final SoundEvent HAMMER = registerSound("hammer");
    public static final SoundEvent BIRD = registerSound("bird");
    public static final SoundEvent BIRD_HURT = registerSound("bird_hurt");
    public static final SoundEvent BIRD_DEATH = registerSound("bird_death");
    public static final SoundEvent GATE_CREAK = registerSound("gate_creak");
    public static final SoundEvent UNLOCK = registerSound("unlock");
    public static final SoundEvent BOTTLE_PLUG = registerSound("bottle_plug");
    public static final SoundEvent ETERNAL_NIGHT = registerSound("eternal_night");
    public static final SoundEvent FLORO_SHOOT = registerSound("floro_shoot");
    public static final SoundEvent LAVASNAKE_IDLE = registerSound("lavasnake_idle");
    public static final SoundEvent LAVASNAKE_HURT = registerSound("lavasnake_hurt");
    public static final SoundEvent SENTRY_ALTAR_ACTIVATE = registerSound("sentry_altar_activate");
    public static final SoundEvent SENTRY_ALTAR_DEACTIVATE = registerSound("sentry_altar_deactivate");
    public static final SoundEvent SENTRY_ALTAR_SMOKE = registerSound("sentry_altar_smoke");
    public static final SoundEvent SENTRY_AMBIENT_1 = registerSound("sentry_ambient_1");
    public static final SoundEvent SENTRY_AMBIENT_2 = registerSound("sentry_ambient_2");
    public static final SoundEvent SENTRY_AMBIENT_3 = registerSound("sentry_ambient_3");
    public static final SoundEvent SENTRY_GHOST = registerSound("sentry_ghost");
    public static final SoundEvent SENTRY_HURT_1 = registerSound("sentry_hurt_1");
    public static final SoundEvent SENTRY_HURT_2 = registerSound("sentry_hurt_2");

    public static final SoundEvent OBELISK_IDLE = registerSound("obelisk_idle");
    public static final SoundEvent OBELISK_OPEN = registerSound("obelisk_open");

    public static final SoundEvent SENTRY_HEART_BEATING = registerSound("sentry_heart_beating");
    public static final SoundEvent SENTRY_HEART_HIT = registerSound("sentry_heart_hit");
    public static final SoundEvent SENTRY_HEART_DEATH = registerSound("sentry_heart_death");
    public static final SoundEvent SENTRY_DESTRUCTION_MOVING = registerSound("sentry_destruction_moving");

    public static final SoundEvent BLAZIER_IDLE = registerSound("blazier_idle");
    public static final SoundEvent BLAZIER_HURT = registerSound("blazier_hurt");
    public static final SoundEvent BLAZIER_DEATH = registerSound("blazier_death");

    public static final SoundEvent EMPTY = registerSound("empty");
	public static final SoundEvent UNTITLED_DISC = registerSound("untitled_disk");
	public static final SoundEvent RACE_TO_SHORE = registerSound("race_shore");
    public static final SoundEvent DEEP_BLUE = registerSound("deep_blue");
    public static final SoundEvent COMPETITION_BEGINS = registerSound("comp_begins");
    public static final SoundEvent RACE_STAR = registerSound("race_star");
    public static final SoundEvent BLUE_WATER = registerSound("blue_water");
    public static final SoundEvent UNDERWATER_WORLD = registerSound("underwater_world");
    public static final SoundEvent EUCA_1 = registerSound("euca_1");
    public static final SoundEvent EUCA_2 = registerSound("euca_2");
    public static final SoundEvent EUCA_3 = registerSound("euca_3");
    public static final SoundEvent DEPTHS_1 = registerSound("depths_1");
    public static final SoundEvent DEPTHS_2 = registerSound("depths_2");
    public static final SoundEvent CORBA_1 = registerSound("corba_1");
    public static final SoundEvent CLOUDIA_1 = registerSound("cloudia_1");
    public static final SoundEvent CLOUDIA_2 = registerSound("cloudia_2");
    public static final SoundEvent BOIL_1 = registerSound("boil_1");
    public static final SoundEvent TERRANIA_1 = registerSound("terrania_1");
    public static final SoundEvent FROZEN_1 = registerSound("frozen_1");

    public static SoundEvent registerSound(String soundName) {
        ResourceLocation rl = JITL.rl(soundName);
	    SoundEvent sound = new SoundEvent(rl);
        sound.setRegistryName(rl);

        soundsToReg.add(sound);
        return sound;
    }

    public static void playSound(double x, double y, double z, SoundEvent sound, World w, float volume, float pitch) {
        w.playSound(x, y, z, sound, SoundCategory.NEUTRAL, volume, pitch, false);
    }

    public static void playSound(SoundEvent sound, World w, EntityPlayer e) {
        w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    public static void playSound(SoundEvent sound, EntityLiving e) {
        e.playSound(sound, 1.0F, 1.0F);
    }

    public static void playSound(SoundEvent sound, World w, EntityLiving e, float volume, float pitch) {
        e.playSound(sound, volume, pitch);
    }

    public static void playSound(SoundEvent sound, World w, EntityPlayer e, float volume, float pitch) {
        w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, volume, pitch);
    }

    @SubscribeEvent
    public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent sound : soundsToReg) {
            event.getRegistry().register(sound);
        }

        JITL.LOGGER.info("Successfully registered {} sounds", soundsToReg.size());

        soundsToReg = null;
    }

}