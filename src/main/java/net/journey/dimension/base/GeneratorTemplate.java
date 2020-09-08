package net.journey.dimension.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * Should be used for all IDE-slowing auto-generated structures
 * <p>
 * All custom stuff MUST be put into the bound {@link JStructureComponent}.
 * You can access them via {@link #getComponent()}.
 */
public abstract class GeneratorTemplate<T extends JStructureComponent> {
	private final T component;
	private final StructureBoundingBox chunkBB;

	public GeneratorTemplate(T component, StructureBoundingBox chunkBB) {
		this.component = component;
		this.chunkBB = chunkBB;
	}

	public abstract void generate(World worldIn, Random rand);

	protected void setBlockState(World worldIn, int x, int y, int z, IBlockState state) {
		component.setBlockState(worldIn, state, x, y, z, chunkBB);
	}

	protected T getComponent() {
		return component;
	}
}
