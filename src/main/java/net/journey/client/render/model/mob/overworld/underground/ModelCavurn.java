package net.journey.client.render.model.mob.overworld.underground;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCavurn extends ModelBase {
	
	    ModelRenderer body;
	    ModelRenderer rightleg;
	    ModelRenderer leftleg;
	    ModelRenderer head;
	    ModelRenderer head1;
	    ModelRenderer head2;
	    ModelRenderer head3;

	public ModelCavurn() {
	 	textureWidth = 64;
		textureHeight = 64;

		  body = new ModelRenderer(this, 16, 16);
	      body.addBox(-4F, 0F, -2F, 6, 10, 4);
	      body.setRotationPoint(1F, 2F, 0F);
	      body.setTextureSize(64, 64);
	      setRotation(body, 0F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 0, 32);
	      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      rightleg.setRotationPoint(-2F, 12F, 0F);
	      rightleg.setTextureSize(64, 64);
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 0, 16);
	      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
	      leftleg.setRotationPoint(2F, 12F, 0F);
	      leftleg.setTextureSize(64, 64);
	      setRotation(leftleg, 0F, 0F, 0F);
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-3F, -6F, -3F, 6, 6, 6);
	      head.setRotationPoint(0F, 2F, 0F);
	      head.setTextureSize(64, 64);
	      setRotation(head, 0F, 0F, 0F);
	      head1 = new ModelRenderer(this, 0, 0);
	      head1.addBox(-3F, -6F, -3F, 6, 6, 6);
	      head1.setRotationPoint(0F, -4F, 0F);
	      head1.setTextureSize(64, 64);
	      setRotation(head1, -0.3490659F, 0F, 0F);
	      head2 = new ModelRenderer(this, 0, 0);
	      head2.addBox(0F, -3F, -2F, 6, 6, 6);
	      head2.setRotationPoint(3F, -1F, -1F);
	      head2.setTextureSize(64, 64);
	      setRotation(head2, 0F, -0.3490659F, 0F);
	      head3 = new ModelRenderer(this, 0, 0);
	      head3.addBox(-6F, -3F, -2F, 6, 6, 6);
	      head3.setRotationPoint(-3F, -1F, -1F);
	      head3.setTextureSize(64, 64);
	      setRotation(head3, 0F, 0.3490659F, 0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        body.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
   	    head.render(f5);
	    head1.render(f5);
	    head2.render(f5);
	    head3.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
		
	}
		@Override
		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
			this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
			this.head1.rotateAngleY = f4 / (180F / (float)Math.PI);
			this.head1.rotateAngleX = f5 / (180F / (float)Math.PI);
			this.head2.rotateAngleY = f4 / (180F / (float)Math.PI);
			this.head2.rotateAngleX = f5 / (180F / (float)Math.PI);
			this.head3.rotateAngleY = f4 / (180F / (float)Math.PI);
			this.head3.rotateAngleX = f5 / (180F / (float)Math.PI);
			this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
			this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		
	}
}