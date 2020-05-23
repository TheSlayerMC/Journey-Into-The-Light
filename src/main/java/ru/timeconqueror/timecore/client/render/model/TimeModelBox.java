package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.List;

public class TimeModelBox {
	public final Vector3f pos1;
	public final Vector3f pos2;
	private final TexturedQuad[] quads;

	/**
	 * @param origin  The position of the cube, relative to the entity origin - located at the bottom front left point of the cube.
	 * @param size    The cube dimensions (x, y, z).
	 * @param uv      The starting point in the texture foo.png (x -> horizontal, y -> vertical) for that cube.
	 * @param inflate scale factor /Expands the cube, without expanding the UV mapping - useful for making armor look worn, and not part of the entity.
	 */
	public TimeModelBox(Vector3f origin, Vector3f size, Vector2f uv, float inflate, boolean mirror, int textureWidth, int textureHeight) {
		int dx = (int) size.getX();
		int dy = (int) size.getY();
		int dz = (int) size.getZ();

		size.set(size.getX() == 0 ? 0.008F : size.getX(), size.getY() == 0 ? 0.008F : size.getY(), size.getZ() == 0 ? 0.008F : size.getZ());
		float x = origin.getX();
		float y = origin.getY();
		float z = origin.getZ();

		int texU = (int) uv.getX();
		int texV = (int) uv.getY();

		List<TexturedQuad> quads = new ArrayList<>(6);

		this.pos1 = new Vector3f(x, y, z);
		this.pos2 = new Vector3f(x + size.getX(), y + size.getY(), z + size.getZ());

		float f = pos2.getX() + inflate;
		float f1 = pos2.getY() + inflate;
		float f2 = pos2.getZ() + inflate;
		x -= inflate;
		y -= inflate;
		z -= inflate;

		if (mirror) {
			float temp = f;
			f = x;
			x = temp;
		}

		PositionTextureVertex vertex7 = new PositionTextureVertex(x, y, z, 0.0F, 0.0F);
		PositionTextureVertex vertex = new PositionTextureVertex(f, y, z, 0.0F, 8.0F);
		PositionTextureVertex vertex1 = new PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
		PositionTextureVertex vertex2 = new PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
		PositionTextureVertex vertex3 = new PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
		PositionTextureVertex vertex4 = new PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
		PositionTextureVertex vertex5 = new PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
		PositionTextureVertex vertex6 = new PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);

		if (dz != 0 && dy != 0) {
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex4, vertex, vertex1, vertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, textureWidth, textureHeight));
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex7, vertex3, vertex6, vertex2}, texU, texV + dz, texU + dz, texV + dz + dy, textureWidth, textureHeight));
		}

		if (dx != 0 && dz != 0) {
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex4, vertex3, vertex7, vertex}, texU + dz, texV, texU + dz + dx, texV + dz, textureWidth, textureHeight));
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex1, vertex2, vertex6, vertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, textureWidth, textureHeight));
		}

		if (dx != 0 && dy != 0) {
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex, vertex7, vertex2, vertex1}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, textureWidth, textureHeight));
			quads.add(new TexturedQuad(new PositionTextureVertex[]{vertex3, vertex4, vertex5, vertex6}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, textureWidth, textureHeight));
		}

		if (mirror) {
			for (TexturedQuad texturedquad : quads) {
				texturedquad.flipFace();
			}
		}

		this.quads = quads.toArray(new TexturedQuad[0]);
	}

	public void render(BufferBuilder renderer, float scale) {
		for (TexturedQuad texturedquad : this.quads) {
			texturedquad.draw(renderer, scale);
		}

	}
}
