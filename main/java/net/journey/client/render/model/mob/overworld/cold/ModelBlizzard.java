package net.journey.client.render.model.mob.overworld.cold;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelBlizzard ~ Dizzlepop12, Mojang
 * Created using Tabula 5.1.0
 */
public class ModelBlizzard extends ModelBase {

	public ModelRenderer head;
	private ModelRenderer[] sticks = new ModelRenderer[12];

	public ModelBlizzard() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		for(int i = 0; i < this.sticks.length; i++) {
			this.sticks[i] = new ModelRenderer(this, 0, 16);
			this.sticks[i].addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		}
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.render(f5);
		for(int i = 0; i < sticks.length; i++) this.sticks[i].render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f7, Entity e) {
		float f6 = f3 * (float)Math.PI * -0.1F;
		 for (int i = 0; i < 4; ++i)
	        {
	            this.sticks[i].rotationPointY = -2.0F + MathHelper.cos((i * 2 + f3) * 0.25F);
	            this.sticks[i].rotationPointX = MathHelper.cos(f) * 9.0F;
	            this.sticks[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
	            ++f;
	        }

	        f = ((float)Math.PI / 4F) + f3 * (float)Math.PI * 0.1F;

	        for (int j = 4; j < 8; ++j)
	        {
	            this.sticks[j].rotationPointY = 2.0F + MathHelper.cos((j * 2 + f3) * 0.25F);
	            this.sticks[j].rotationPointX = MathHelper.cos(f) * 7.0F;
	            this.sticks[j].rotationPointZ = MathHelper.sin(f) * 7.0F;
	            ++f;
	        }

	        f = 0.47123894F + f3 * (float)Math.PI * -0.1F;

	        for (int k = 8; k < 12; ++k)
	        {
	            this.sticks[k].rotationPointY = 11.0F + MathHelper.cos((k * 1.5F + f3) * 0.5F);
	            this.sticks[k].rotationPointX = MathHelper.cos(f) * 5.0F;
	            this.sticks[k].rotationPointZ = MathHelper.sin(f) * 5.0F;
	            ++f;
	        }

	        this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
	        this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
	    }
	}