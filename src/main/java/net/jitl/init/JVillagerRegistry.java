package net.jitl.init;

import com.google.common.collect.ImmutableSet;
import net.jitl.JITL;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JVillagerRegistry {

    public static final DeferredRegister<PointOfInterestType> POI_REGISTER = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final DeferredRegister<VillagerProfession> PROFESSION_REGISTER = DeferredRegister.create(ForgeRegistries.PROFESSIONS, JITL.MODID);

    public static final RegistryObject<PointOfInterestType> TEST_POI = POI_REGISTER.register("test",
            () -> new PointOfInterestType("test", PointOfInterestType.getBlockStates(JBlocks.FROZEN_PORTAL_FRAME), 1, 1));

    public static final RegistryObject<VillagerProfession> TEST_PROFESSION = PROFESSION_REGISTER.register("test",
            () -> new VillagerProfession("test", TEST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));
}
