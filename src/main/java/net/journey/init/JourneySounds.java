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

    public static SoundEvent CAVE_MOB = registerSound("cave_mob");
    public static SoundEvent ROCK = registerSound("rock");
    public static SoundEvent KNIFE = registerSound("knife");
    public static SoundEvent BASE_MOB_HURT = registerSound("base_mob_hurt");
    public static SoundEvent BOSS_DEATH = registerSound("boss_death");
    public static SoundEvent TERRA_SLUG = registerSound("terraslug");
    public static SoundEvent TERRA_SLUG_HURT = registerSound("terraslug_hurt");
    public static SoundEvent TERRA_SLUG_DEATH = registerSound("terraslug_death");
    public static SoundEvent SUMMON_BOSS = registerSound("summon_boss");
    public static SoundEvent SUMMON_TABLE = registerSound("summon_table");
    public static SoundEvent SORCERER_DEATH = registerSound("sorcerer_death");
    public static SoundEvent SORCERER_HURT = registerSound("sorcerer_hurt");
    public static SoundEvent SORCERER = registerSound("sorcerer");
    public static SoundEvent CHEST_OPEN_0 = registerSound("chest_open_0");
    public static SoundEvent CHEST_OPEN = registerSound("chest_open");
    public static SoundEvent CHEST_CLOSE = registerSound("chest_close");
    public static SoundEvent ROBOT = registerSound("robot");
    public static SoundEvent ROBOT_HURT = registerSound("robot_hurt");
    public static SoundEvent ROBOT_DEATH = registerSound("robot_death");
    public static SoundEvent PSYOLLOM = registerSound("psyollom");
    public static SoundEvent PSYOLLOM_HURT = registerSound("psyollom_hurt");
    public static SoundEvent INSECTO = registerSound("insecto");
    public static SoundEvent INSECTO_HURT = registerSound("insecto_hurt");
    public static SoundEvent SPIKED_BEAST = registerSound("spiked_beast");
    public static SoundEvent SPIKED_BEAST_HURT = registerSound("spiked_beast_hurt");
    public static SoundEvent MAGMA_GIANT = registerSound("magma_giant");
    public static SoundEvent MAGMA_GIANT_HURT = registerSound("magma_giant_hurt");
    public static SoundEvent SPYCLOPS = registerSound("spyclops");
    public static SoundEvent SPYCLOPS_HURT = registerSound("spyclops_hurt");
    public static SoundEvent TURTLE_HURT = registerSound("turtle_hurt");
    public static SoundEvent HONGO = registerSound("hongo");
    public static SoundEvent SMALL_HONGO = registerSound("small_hongo");
    public static SoundEvent SMALL_HONGO_HURT = registerSound("small_hongo_hurt");
    public static SoundEvent HONGO_HURT = registerSound("hongo_hurt");
    public static SoundEvent REAPER = registerSound("reaper");
    public static SoundEvent REAPER_HURT = registerSound("reaper_hurt");
    public static SoundEvent SAND_CRAWLER = registerSound("sand_crawler");
    public static SoundEvent DEPTHS_HUNTER = registerSound("depths_hunter");
    public static SoundEvent DEPTHS_HUNTER_HURT = registerSound("depths_hunter_hurt");
    public static SoundEvent BEAST_OF_THE_NETHER = registerSound("beast_of_the_nether");
    public static SoundEvent BEAST_OF_THE_NETHER_HURT = registerSound("beast_of_the_nether_hurt");
    public static SoundEvent CALCIA = registerSound("calcia");
    public static SoundEvent CALCIA_HURT = registerSound("calcia_hurt");
    public static SoundEvent STAFF = registerSound("staff");
    public static SoundEvent WRAPPER = registerSound("wrapper");
    public static SoundEvent PAGE_FLIP = registerSound("page_flip");
    public static SoundEvent MAGIC_SPARKLE = registerSound("magic_sparkle");
    public static SoundEvent CANNON = registerSound("cannon");
    public static SoundEvent PLASMA = registerSound("plasma");
    public static SoundEvent DYNASTER = registerSound("dynaster");
    public static SoundEvent DYNASTER_HURT = registerSound("dynaster_hurt");
    public static SoundEvent DYNASTER_DEATH = registerSound("dynaster_death");
    public static SoundEvent SHIMMERER = registerSound("shimmerer");
    public static SoundEvent SHIMMERER_HURT = registerSound("shimmerer_hurt");
    public static SoundEvent SHIMMERER_DEATH = registerSound("shimmerer_death");
    public static SoundEvent BUSH = registerSound("bush");
    public static SoundEvent BUSH_HURT = registerSound("bush_hurt");
    public static SoundEvent BUSH_DEATH = registerSound("bush_death");
    public static SoundEvent WRAITH = registerSound("wraith");
    public static SoundEvent WRAITH_DEATH = registerSound("wraith_death");
    public static SoundEvent WRAITH_HURT = registerSound("wraith_hurt");
    public static SoundEvent OVERSEER = registerSound("overseer");
    public static SoundEvent OVERSEER_HURT = registerSound("overseer_hurt");
    public static SoundEvent OVERSEER_DEATH = registerSound("overseer_death");
    public static SoundEvent HAMMER = registerSound("hammer");
    public static SoundEvent BIRD = registerSound("bird");
    public static SoundEvent BIRD_HURT = registerSound("bird_hurt");
    public static SoundEvent BIRD_DEATH = registerSound("bird_death");
    public static SoundEvent GATE_CREAK = registerSound("gate_creak");
    public static SoundEvent UNLOCK = registerSound("unlock");
    public static SoundEvent BOTTLE_PLUG = registerSound("bottle_plug");
    public static SoundEvent ETERNAL_NIGHT = registerSound("eternal_night");
    public static SoundEvent FLORO_SHOOT = registerSound("floro_shoot");
    public static SoundEvent SENTRY_ALTAR_ACTIVATE = registerSound("sentry_altar_activate");
    public static SoundEvent SENTRY_ALTAR_DEACTIVATE = registerSound("sentry_altar_deactivate");
    public static SoundEvent SENTRY_ALTAR_SMOKE = registerSound("sentry_altar_smoke");
    public static SoundEvent SENTRY_AMBIENT_1 = registerSound("sentry_ambient_1");
    public static SoundEvent SENTRY_AMBIENT_2 = registerSound("sentry_ambient_2");
    public static SoundEvent SENTRY_AMBIENT_3 = registerSound("sentry_ambient_3");
    public static SoundEvent SENTRY_GHOST = registerSound("sentry_ghost");
    public static SoundEvent SENTRY_HURT_1 = registerSound("sentry_hurt_1");
    public static SoundEvent SENTRY_HURT_2 = registerSound("sentry_hurt_2");

    public static SoundEvent OBELISK_IDLE = registerSound("obelisk_idle");
    public static SoundEvent OBELISK_OPEN = registerSound("obelisk_open");

    public static SoundEvent SENTRY_HEART_BEATING = registerSound("sentry_heart_beating");
    public static SoundEvent SENTRY_HEART_HIT = registerSound("sentry_heart_hit");
    public static SoundEvent SENTRY_HEART_DEATH = registerSound("sentry_heart_death");
    public static SoundEvent SENTRY_DESTRUCTION_MOVING = registerSound("sentry_destruction_moving");

    public static SoundEvent BLAZIER_IDLE = registerSound("blazier_idle");
    public static SoundEvent BLAZIER_HURT = registerSound("blazier_hurt");
    public static SoundEvent BLAZIER_DEATH = registerSound("blazier_death");

    public static SoundEvent EMPTY = registerSound("empty");
    public static SoundEvent UNTITLED_DISK = registerSound("untitled_disk");
    public static SoundEvent RACE_TO_SHORE = registerSound("race_shore");
    public static SoundEvent DEEP_BLUE = registerSound("deep_blue");
    public static SoundEvent COMPETITION_BEGINS = registerSound("comp_begins");
    public static SoundEvent RACE_STAR = registerSound("race_star");
    public static SoundEvent BLUE_WATER = registerSound("blue_water");
    public static SoundEvent UNDERWATER_WORLD = registerSound("underwater_world");
    public static SoundEvent EUCA_1 = registerSound("euca_1");
    public static SoundEvent EUCA_2 = registerSound("euca_2");
    public static SoundEvent EUCA_3 = registerSound("euca_3");
    public static SoundEvent DEPTHS_1 = registerSound("depths_1");
    public static SoundEvent DEPTHS_2 = registerSound("depths_2");
    public static SoundEvent CORBA_1 = registerSound("corba_1");
    public static SoundEvent CLOUDIA_1 = registerSound("cloudia_1");
    public static SoundEvent CLOUDIA_2 = registerSound("cloudia_2");
    public static SoundEvent BOIL_1 = registerSound("boil_1");
    public static SoundEvent TERRANIA_1 = registerSound("terrania_1");
    public static SoundEvent FROZEN_1 = registerSound("frozen_1");

    public static SoundEvent registerSound(String soundName) {
        ResourceLocation rl = new ResourceLocation(JITL.MOD_ID, soundName);
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