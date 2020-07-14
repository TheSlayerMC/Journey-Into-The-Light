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
		float x1 = origin.getX();
		float y1 = origin.getY();
		float z1 = origin.getZ();

		int texU = (int) uv.getX();
		int texV = (int) uv.getY();

		List<TexturedQuad> quads = new ArrayList<>(6);

		float x2 = x1 + size.getX();
		float y2 = y1 + size.getY();
		float z2 = z1 + size.getZ();

		this.pos1 = new Vector3f(x1, y1, z1);
		this.pos2 = new Vector3f(x2, y2, z2);

		x2 += inflate;
		y2 += inflate;
		z2 += inflate;
		x1 -= inflate;
		y1 -= inflate;
		z1 -= inflate;

		if (mirror) {
			float temp = x2;
			x2 = x1;
			x1 = temp;
		}

		PositionTextureVertex vertex7 = new PositionTextureVertex(x1, y1, z1, 0.0F, 0.0F);
		PositionTextureVertex vertex = new PositionTextureVertex(x2, y1, z1, 0.0F, 8.0F);
		PositionTextureVertex vertex1 = new PositionTextureVertex(x2, y2, z1, 8.0F, 8.0F);
		PositionTextureVertex vertex2 = new PositionTextureVertex(x1, y2, z1, 8.0F, 0.0F);
		PositionTextureVertex vertex3 = new PositionTextureVertex(x1, y1, z2, 0.0F, 0.0F);
		PositionTextureVertex vertex4 = new PositionTextureVertex(x2, y1, z2, 0.0F, 8.0F);
		PositionTextureVertex vertex5 = new PositionTextureVertex(x2, y2, z2, 8.0F, 8.0F);
		PositionTextureVertex vertex6 = new PositionTextureVertex(x1, y2, z2, 8.0F, 0.0F);

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
