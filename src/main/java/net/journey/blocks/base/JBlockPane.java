package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.FeatureProvider;
import net.journey.blocks.util.Features;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockPane;
import net.minecraft.block.state.BlockStateContainer;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;

public class JBlockPane extends BlockPane implements FeatureProvider {
	public JBlockPane(EnumMaterialTypes type, String name, String enName, boolean canDrop) {
		super(type.getMaterial(), canDrop);
		setSoundType(type.getSound());
		StuffConstructor.regAndSetupBlock(this, name, enName, JourneyTabs.DECORATION);
	}

	@Override
	protected @NotNull BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockPane.NORTH, BlockPane.EAST, BlockPane.SOUTH, BlockPane.WEST);
	}

	@Override
	public @NotNull Features getExtraFeatures() {
		return Features.Builder.create()
				.setCustomItemModelLocation(JITL.rl("block/pane/" + getRegistryName().getPath()))
				.build();
	}
}
