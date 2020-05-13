package net.journey.init;

import net.journey.JITL;
import net.journey.Registries;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@EventBusSubscriber(modid = JITL.MOD_ID)
public class JourneySounds {

    public static SoundEvent CAVE_MOB;
    public static SoundEvent ROCK;
    public static SoundEvent KNIFE;
    public static SoundEvent BASE_MOB_HURT;
    public static SoundEvent BOSS_DEATH;
    public static SoundEvent TERRA_SLUG;
    public static SoundEvent TERRA_SLUG_HURT;
    public static SoundEvent TERRA_SLUG_DEATH;
    public static SoundEvent SUMMON_BOSS;
    public static SoundEvent SUMMON_TABLE;
    public static SoundEvent SORCERER_DEATH;
    public static SoundEvent SORCERER_HURT;
    public static SoundEvent SORCERER;
    public static SoundEvent CHEST_OPEN_0;
    public static SoundEvent CHEST_OPEN;
    public static SoundEvent CHEST_CLOSE;
    public static SoundEvent ROBOT;
    public static SoundEvent ROBOT_HURT;
    public static SoundEvent ROBOT_DEATH;
    public static SoundEvent PSYOLLOM;
    public static SoundEvent PSYOLLOM_HURT;
    public static SoundEvent INSECTO;
    public static SoundEvent INSECTO_HURT;
    public static SoundEvent SPIKED_BEAST;
    public static SoundEvent SPIKED_BEAST_HURT;
    public static SoundEvent MAGMA_GIANT;
    public static SoundEvent MAGMA_GIANT_HURT;
    public static SoundEvent SPYCLOPS;
    public static SoundEvent SPYCLOPS_HURT;
    public static SoundEvent TURTLE_HURT;
    public static SoundEvent HONGO;
    public static SoundEvent SMALL_HONGO;
    public static SoundEvent SMALL_HONGO_HURT;
    public static SoundEvent HONGO_HURT;
    public static SoundEvent REAPER;
    public static SoundEvent REAPER_HURT;
    public static SoundEvent SAND_CRAWLER;
    public static SoundEvent DEPTHS_HUNTER;
    public static SoundEvent DEPTHS_HUNTER_HURT;
    public static SoundEvent BEAST_OF_THE_NETHER;
    public static SoundEvent BEAST_OF_THE_NETHER_HURT;
    public static SoundEvent CALCIA;
    public static SoundEvent CALCIA_HURT;
    public static SoundEvent STAFF;
    public static SoundEvent WRAPPER;
    public static SoundEvent PAGE_FLIP;
    public static SoundEvent MAGIC_SPARKLE;
    public static SoundEvent CANNON;
    public static SoundEvent PLASMA;
    public static SoundEvent DYNASTER;
    public static SoundEvent DYNASTER_HURT;
    public static SoundEvent DYNASTER_DEATH;
    public static SoundEvent SHIMMERER;
    public static SoundEvent SHIMMERER_HURT;
    public static SoundEvent SHIMMERER_DEATH;
    public static SoundEvent BUSH;
    public static SoundEvent BUSH_HURT;
    public static SoundEvent BUSH_DEATH;
    public static SoundEvent WRAITH;
    public static SoundEvent WRAITH_DEATH;
    public static SoundEvent WRAITH_HURT;
    public static SoundEvent OVERSEER;
    public static SoundEvent OVERSEER_HURT;
    public static SoundEvent OVERSEER_DEATH;
    public static SoundEvent HAMMER;
    public static SoundEvent BIRD;
    public static SoundEvent BIRD_HURT;
    public static SoundEvent BIRD_DEATH;
    public static SoundEvent GATE_CREAK;
    public static SoundEvent UNLOCK;
    public static SoundEvent BOTTLE_PLUG;
    public static SoundEvent ETERNAL_NIGHT;
    
    public static SoundEvent SENTRY_ALTAR_ACTIVATE;
    public static SoundEvent SENTRY_ALTAR_DEACTIVATE;
    public static SoundEvent SENTRY_ALTAR_SMOKE;
    public static SoundEvent SENTRY_AMBIENT_1;
    public static SoundEvent SENTRY_AMBIENT_2;
    public static SoundEvent SENTRY_AMBIENT_3;
    public static SoundEvent SENTRY_GHOST;
    public static SoundEvent SENTRY_HURT_1;
    public static SoundEvent SENTRY_HURT_2;
    
    public static SoundEvent OBELISK_IDLE;
    public static SoundEvent OBELISK_OPEN;
    public static SoundEvent SENTRY_HEART_DEATH;
    public static SoundEvent SENTRY_HEART_BEATING;
    public static SoundEvent SENTRY_HEART_HIT;
    public static SoundEvent SENTRY_DESTRUCTION_MOVING;
    
    public static SoundEvent BLAZIER_IDLE;
    public static SoundEvent BLAZIER_HURT;
    public static SoundEvent BLAZIER_DEATH;
    
    public static SoundEvent EMPTY;

    public static SoundEvent UNTITLED_DISK;
    public static SoundEvent RACE_TO_SHORE;
    public static SoundEvent DEEP_BLUE;
    public static SoundEvent COMPETITION_BEGINS;
    public static SoundEvent RACE_STAR;
    public static SoundEvent BLUE_WATER;
    public static SoundEvent UNDERWATER_WORLD;
    public static SoundEvent EUCA_1, EUCA_2, EUCA_3;
    public static SoundEvent DEPTHS_1, DEPTHS_2;
    public static SoundEvent BOIL_1;
    public static SoundEvent CORBA_1;
    public static SoundEvent CLOUDIA_1;
    public static SoundEvent CLOUDIA_2;
    public static SoundEvent TERRANIA_1;
    public static SoundEvent FROZEN_1;


    public static void init() {

        CAVE_MOB = registerSound("cave_mob");
        ROCK = registerSound("rock");
        KNIFE = registerSound("knife");
        BASE_MOB_HURT = registerSound("base_mob_hurt");
        BOSS_DEATH = registerSound("boss_death");
        TERRA_SLUG = registerSound("terraslug");
        TERRA_SLUG_HURT = registerSound("terraslug_hurt");
        TERRA_SLUG_DEATH = registerSound("terraslug_death");
        SUMMON_BOSS = registerSound("summon_boss");
        SUMMON_TABLE = registerSound("summon_table");
        SORCERER_DEATH = registerSound("sorcerer_death");
        SORCERER_HURT = registerSound("sorcerer_hurt");
        SORCERER = registerSound("sorcerer");
        CHEST_OPEN_0 = registerSound("chest_open_0");
        CHEST_OPEN = registerSound("chest_open");
        CHEST_CLOSE = registerSound("chest_close");
        ROBOT = registerSound("robot");
        ROBOT_HURT = registerSound("robot_hurt");
        ROBOT_DEATH = registerSound("robot_death");
        PSYOLLOM = registerSound("psyollom");
        PSYOLLOM_HURT = registerSound("psyollom_hurt");
        INSECTO = registerSound("insecto");
        INSECTO_HURT = registerSound("insecto_hurt");
        SPIKED_BEAST = registerSound("spiked_beast");
        SPIKED_BEAST_HURT = registerSound("spiked_beast_hurt");
        MAGMA_GIANT = registerSound("magma_giant");
        MAGMA_GIANT_HURT = registerSound("magma_giant_hurt");
        SPYCLOPS = registerSound("spyclops");
        SPYCLOPS_HURT = registerSound("spyclops_hurt");
        TURTLE_HURT = registerSound("turtle_hurt");
        HONGO = registerSound("hongo");
        SMALL_HONGO = registerSound("small_hongo");
        SMALL_HONGO_HURT = registerSound("small_hongo_hurt");
        HONGO_HURT = registerSound("hongo_hurt");
        REAPER = registerSound("reaper");
        REAPER_HURT = registerSound("reaper_hurt");
        SAND_CRAWLER = registerSound("sand_crawler");
        DEPTHS_HUNTER = registerSound("depths_hunter");
        DEPTHS_HUNTER_HURT = registerSound("depths_hunter_hurt");
        BEAST_OF_THE_NETHER = registerSound("beast_of_the_nether");
        BEAST_OF_THE_NETHER_HURT = registerSound("beast_of_the_nether_hurt");
        CALCIA = registerSound("calcia");
        CALCIA_HURT = registerSound("calcia_hurt");
        STAFF = registerSound("staff");
        WRAPPER = registerSound("wrapper");
        PAGE_FLIP = registerSound("page_flip");
        MAGIC_SPARKLE = registerSound("magic_sparkle");
        CANNON = registerSound("cannon");
        PLASMA = registerSound("plasma");
        DYNASTER = registerSound("dynaster");
        DYNASTER_HURT = registerSound("dynaster_hurt");
        DYNASTER_DEATH = registerSound("dynaster_death");
        SHIMMERER = registerSound("shimmerer");
        SHIMMERER_HURT = registerSound("shimmerer_hurt");
        SHIMMERER_DEATH = registerSound("shimmerer_death");
        BUSH = registerSound("bush");
        BUSH_HURT = registerSound("bush_hurt");
        BUSH_DEATH = registerSound("bush_death");
        WRAITH = registerSound("wraith");
        WRAITH_DEATH = registerSound("wraith_death");
        WRAITH_HURT = registerSound("wraith_hurt");
        OVERSEER = registerSound("overseer");
        OVERSEER_HURT = registerSound("overseer_hurt");
        OVERSEER_DEATH = registerSound("overseer_death");
        HAMMER = registerSound("hammer");
        BIRD = registerSound("bird");
        BIRD_HURT = registerSound("bird_hurt");
        BIRD_DEATH = registerSound("bird_death");
        GATE_CREAK = registerSound("gate_creak");
        UNLOCK = registerSound("unlock");
        BOTTLE_PLUG = registerSound("bottle_plug");
        ETERNAL_NIGHT = registerSound("eternal_night");
        SENTRY_ALTAR_ACTIVATE = registerSound("sentry_altar_activate");
        SENTRY_ALTAR_DEACTIVATE = registerSound("sentry_altar_deactivate");
        SENTRY_ALTAR_SMOKE = registerSound("sentry_altar_smoke");
        SENTRY_AMBIENT_1 = registerSound("sentry_ambient_1");
        SENTRY_AMBIENT_2 = registerSound("sentry_ambient_2");
        SENTRY_AMBIENT_3 = registerSound("sentry_ambient_3");
        SENTRY_GHOST = registerSound("sentry_ghost");
        SENTRY_HURT_1 = registerSound("sentry_hurt_1");
        SENTRY_HURT_2 = registerSound("sentry_hurt_2");
        
        OBELISK_IDLE = registerSound("obelisk_idle");
        OBELISK_OPEN = registerSound("obelisk_open");
        
        SENTRY_HEART_BEATING = registerSound("sentry_heart_beating");
        SENTRY_HEART_HIT = registerSound("sentry_heart_hit");
        SENTRY_HEART_DEATH = registerSound("sentry_heart_death");
        SENTRY_DESTRUCTION_MOVING = registerSound("sentry_destruction_moving");
        
        BLAZIER_IDLE = registerSound("blazier_idle");
        BLAZIER_HURT = registerSound("blazier_hurt");
        BLAZIER_DEATH = registerSound("blazier_death");
        
        EMPTY = registerSound("empty");
        UNTITLED_DISK = registerSound("untitled_disk");
        RACE_TO_SHORE = registerSound("race_shore");
        DEEP_BLUE = registerSound("deep_blue");
        COMPETITION_BEGINS = registerSound("comp_begins");
        RACE_STAR = registerSound("race_star");
        BLUE_WATER = registerSound("blue_water");
        UNDERWATER_WORLD = registerSound("underwater_world");
        EUCA_1 = registerSound("euca_1");
        EUCA_2 = registerSound("euca_2");
        EUCA_3 = registerSound("euca_3");
        DEPTHS_1 = registerSound("depths_1");
        DEPTHS_2 = registerSound("depths_2");
        CORBA_1 = registerSound("corba_1");
        CLOUDIA_1 = registerSound("cloudia_1");
        CLOUDIA_2 = registerSound("cloudia_2");
        BOIL_1 = registerSound("boil_1");
        TERRANIA_1 = registerSound("terrania_1");
        FROZEN_1 = registerSound("frozen_1");

    }

    public static SoundEvent registerSound(String sound) {
        ResourceLocation LOC = new ResourceLocation(JITL.MOD_ID, sound);
        SoundEvent s = new SoundEvent(LOC);
        s.setRegistryName(LOC);

        ForgeRegistries.SOUND_EVENTS.register(s);
        Registries.SOUNDS.add(s);
        return s;
    }

    public static void playSound(SoundEvent sound, World w, EntityPlayer e) {
        w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    public static void playSound(SoundEvent sound, World w, EntityLiving e) {
        e.playSound(sound, 1.0F, 1.0F);
    }

    public static void playSound(SoundEvent sound, World w, EntityLiving e, float volume, float pitch) {
        e.playSound(sound, volume, pitch);
    }

    public static void playSound(SoundEvent sound, World w, EntityPlayer e, float volume, float pitch) {
        w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, volume, pitch);
    }
}