package ru.timeconqueror.timecore.client.render.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Vector2f;

public class TimeModelBox {
	public final float posX1;
	public final float posY1;
	public final float posZ1;
	public final float posX2;
	public final float posY2;
	public final float posZ2;
	private final TexturedQuad[] quads;

	/**
	 * @param origin  The position of the cube, relative to the entity origin - located at the bottom front left point of the cube.
	 * @param size    The cube dimensions (x, y, z).
	 * @param uv      The starting point in the texture foo.png (x -> horizontal, y -> vertical) for that cube.
	 * @param inflate scale factor /Expands the cube, without expanding the UV mapping - useful for making armor look worn, and not part of the entity.
	 */
	public TimeModelBox(Vector3f origin, Vector3f size, Vector2f uv, float inflate, boolean mirror, int textureWidth, int textureHeight) {
		float x = origin.getX();
		float y = origin.getY();
		float z = origin.getZ();

		int dx = (int) size.getX();
		int dy = (int) size.getY();
		int dz = (int) size.getZ();

		int texU = (int) uv.getX();
		int texV = (int) uv.getY();

		this.posX1 = x;
		this.posY1 = y;
		this.posZ1 = z;
		this.posX2 = x + size.getX();
		this.posY2 = y + size.getY();
		this.posZ2 = z + size.getZ();
		this.quads = new TexturedQuad[6];
		float f = posX2;
		float f1 = posY2;
		float f2 = posZ2;
		x = x - inflate;
		y = y - inflate;
		z = z - inflate;
		f = f + inflate;
		f1 = f1 + inflate;
		f2 = f2 + inflate;
		if (mirror) {
			float f3 = f;
			f = x;
			x = f3;
		}

		PositionTextureVertex vertex7 = new PositionTextureVertex(x, y, z, 0.0F, 0.0F);
		PositionTextureVertex vertex = new PositionTextureVertex(f, y, z, 0.0F, 8.0F);
		PositionTextureVertex vertex1 = new PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
		PositionTextureVertex vertex2 = new PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
		PositionTextureVertex vertex3 = new PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
		PositionTextureVertex vertex4 = new PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
		PositionTextureVertex vertex5 = new PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
		PositionTextureVertex vertex6 = new PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
		this.quads[0] = new TexturedQuad(new PositionTextureVertex[]{vertex4, vertex, vertex1, vertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, textureWidth, textureHeight);
		this.quads[1] = new TexturedQuad(new PositionTextureVertex[]{vertex7, vertex3, vertex6, vertex2}, texU, texV + dz, texU + dz, texV + dz + dy, textureWidth, textureHeight);
		this.quads[2] = new TexturedQuad(new PositionTextureVertex[]{vertex4, vertex3, vertex7, vertex}, texU + dz, texV, texU + dz + dx, texV + dz, textureWidth, textureHeight);
		this.quads[3] = new TexturedQuad(new PositionTextureVertex[]{vertex1, vertex2, vertex6, vertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, textureWidth, textureHeight);
		this.quads[4] = new TexturedQuad(new PositionTextureVertex[]{vertex, vertex7, vertex2, vertex1}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, textureWidth, textureHeight);
		this.quads[5] = new TexturedQuad(new PositionTextureVertex[]{vertex3, vertex4, vertex5, vertex6}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, textureWidth, textureHeight);
		if (mirror) {
			for (TexturedQuad texturedquad : this.quads) {
				texturedquad.flipFace();
			}
		}
	}

	public void render(BufferBuilder renderer, float scale) {
		for (TexturedQuad texturedquad : this.quads) {
			texturedquad.draw(renderer, scale);
		}

	}
}
