package net.jitl.core.init.world;

import net.jitl.common.world.gen.treedecorator.*;
import net.jitl.core.JITL;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JTreeDecorators {

    //FIXME forge tree decorator registry is broken. currently using vanilla registry
    @AutoRegistrable
    private static final SimpleVanillaRegister<TreeDecoratorType<?>> REGISTER = new SimpleVanillaRegister<>(JITL.MODID, Registry.TREE_DECORATOR_TYPES);

    public static final Promised<TreeDecoratorType<FrozenTreeDecorator>> FROZEN_DECORATOR =
            REGISTER.register("frozen_tree_decorator", () -> new TreeDecoratorType<>(FrozenTreeDecorator.CODEC));

    public static final Promised<TreeDecoratorType<CrystalFruitTreeDecorator>> CRYSTAL_FRUIT_DECORATOR = REGISTER.register("crystal_fruit_tree_decorator", () -> new TreeDecoratorType<>(CrystalFruitTreeDecorator.CODEC));

    public static final Promised<TreeDecoratorType<GlimmerRootTreeDecorator>> GLIMMER_ROOT_DECORATOR = REGISTER.register("glimmer_root_tree_decorator", () -> new TreeDecoratorType<>(GlimmerRootTreeDecorator.CODEC));

    public static final Promised<TreeDecoratorType<IceShroomTreeDecorator>> ICE_SHROOM_TREE_DECORATOR = REGISTER.register("ice_shroom_tree_decorator", () -> new TreeDecoratorType<>(IceShroomTreeDecorator.CODEC));

    public static final Promised<TreeDecoratorType<IcyBrushTreeDecorator>> ICY_BRUSH_TREE_DECORATOR = REGISTER.register("icy_brush_tree_decorator", () -> new TreeDecoratorType<>(IcyBrushTreeDecorator.CODEC));

    public static final Promised<TreeDecoratorType<CharredBrushTreeDecorator>> CHARRED_BRUSH_TREE_DECORATOR = REGISTER.register("charred_brush_tree_decorator", () -> new TreeDecoratorType<>(CharredBrushTreeDecorator.CODEC));

}
