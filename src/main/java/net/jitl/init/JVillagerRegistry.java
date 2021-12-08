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

    public static final RegistryObject<PointOfInterestType> ESKIMO_POI = POI_REGISTER.register("eskimo",
            () -> new PointOfInterestType("eskimo", PointOfInterestType.getBlockStates(JBlocks.BITTERWOOD_CAMPFIRE), 1, 1));

    public static final RegistryObject<VillagerProfession> ESKIMO_PROFESSION = PROFESSION_REGISTER.register("eskimo",
            () -> new VillagerProfession("eskimo", ESKIMO_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));
}
