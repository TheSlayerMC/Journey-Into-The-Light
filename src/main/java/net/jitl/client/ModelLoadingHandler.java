package net.jitl.client;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelLoadingHandler {

    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(JITL.rl("emissive"), EmissiveModelGeometry.Loader.INSTANCE);


    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(JBlocks.LUNIUM_ORE, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JBlocks.FIRESTONE_ORE, RenderType.cutout());
    }

//    private static void onRegisterBlockColors(ColorHandlerEvent.Block event) {
////        event.getBlockColors().register(BlockColorHandlers::getBurntWizardGritTint, BlockRegistrar.WIZARD_GRIT.get());
//    }
//
//    private static void onBakeModels(ModelBakeEvent event) {
//        // we want to replace some of the regular baked block models with models that
//        // have emissive/fullbright textures
//        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
//
//        // the model registry uses ModelResourceLocations that can't easily be compared
//        // to regular resource locations
//        // they have an additional field for the blockstate properties of a blockstate
//        // so we need to replace models on a per-blockstate bases
//
//        // we need to use existing models to create our enhanced models, so we'll need to make sure they're in the registry first and get them
//        // let's make a reusable model override function
//        // the resourcelocations we specify in the FullbrightBakedModel constructor are *texture* locations
//        Consumer<ModelResourceLocation> modelOverrider = getModelOverrider(modelRegistry, baseModel ->
//                new FullbrightBakedModel(baseModel,
//                        new ResourceLocation("magus:block/lunium_ore")));
//
//
////        // now we get all the blockstates from our block, narrow them down to the only ones we want to have fullbright textures,
////        // and replace the models with fullbright-enabled models
//        JBlocks.LUNIUM_ORE.getStateDefinition().getPossibleStates().stream()
////                .filter(state -> !state.get(WizardGritBlock.BURNT))
//                .map(BlockModelShapes::stateToModelLocation)
//                .forEach(modelOverrider);
//    }
//
//    public static Consumer<ModelResourceLocation> getModelOverrider(Map<ResourceLocation, IBakedModel> registry, Function<IBakedModel, IBakedModel> modelFunction) {
//        return key -> {
//            if (registry.containsKey(key)) {
//                registry.put(key, modelFunction.apply(registry.get(key)));
//            }
//        };
//    }
//
//    private static void onClientSetup(FMLClientSetupEvent event) {
////        RenderTypeLookup.setRenderLayer(BlockRegistrar.WIZARD_GRIT.get(), RenderType.getCutout());
//    }
}