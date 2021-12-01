package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.treedecorator.FrozenTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JTreeDecorators {

    @AutoRegistrable
    private static final SimpleForgeRegister<TreeDecoratorType<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.TREE_DECORATOR_TYPES, JITL.MODID);

    public static final RegistryObject<TreeDecoratorType<FrozenTreeDecorator>> FROZEN_DECORATOR = REGISTER.register("frozen_tree_decorator", () -> new TreeDecoratorType<>(FrozenTreeDecorator.CODEC));
}
