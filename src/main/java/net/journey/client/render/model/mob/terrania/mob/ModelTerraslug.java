package net.journey.client.render.model.mob.terrania.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTerraslug extends ModelBase {
    private static final int[]
            [] terraslugBoxLength = new int[]
            []{{3, 2, 2
    }, {4, 3, 2
    }, {6, 4, 3
    }, {3, 3, 3
    }, {2, 2, 3
    }, {2, 1, 2
    }, {1, 1, 2
    }
    };
    private static final int[]
            [] terraslugTexturePositions = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};
    private ModelRenderer[] terraslugBodyParts = new ModelRenderer[7];
    private ModelRenderer[] terraslugWings;
    private float[] field_78170_c = new float[7];

    public ModelTerraslug() {
        float f = -3.5F;

        for (int i = 0; i < this.terraslugBodyParts.length; ++i) {
            this.terraslugBodyParts[i] = new ModelRenderer(this, terraslugTexturePositions[i][0], terraslugTexturePositions[i][1]);
            this.terraslugBodyParts[i].addBox
                    (terraslugBoxLength[i][0] * -0.5F, 0.0F,
                            terraslugBoxLength[i][2] * -0.5F,
                            terraslugBoxLength[i][0],
                            terraslugBoxLength[i][1],
                            terraslugBoxLength[i][2]);
            this.terraslugBodyParts[i].setRotationPoint(0.0F, 24 - terraslugBoxLength[i][1], f);
            this.field_78170_c[i] = f;

            if (i < this.terraslugBodyParts.length - 1) {
                f += (terraslugBoxLength[i][2] + terraslugBoxLength[i + 1][2]) * 0.5F;
            }
        }

        this.terraslugWings = new ModelRenderer[3];
        this.terraslugWings[0] = new ModelRenderer(this, 20, 0);
        this.terraslugWings[0].addBox(-5.0F, 0.0F, terraslugBoxLength[2][2] * -0.5F, 10, 8, terraslugBoxLength[2][2]);
        this.terraslugWings[0].setRotationPoint(0.0F, 16.0F, this.field_78170_c[2]);
        this.terraslugWings[1] = new ModelRenderer(this, 20, 11);
        this.terraslugWings[1].addBox(-3.0F, 0.0F, terraslugBoxLength[4][2] * -0.5F, 6, 4, terraslugBoxLength[4][2]);
        this.terraslugWings[1].setRotationPoint(0.0F, 20.0F, this.field_78170_c[4]);
        this.terraslugWings[2] = new ModelRenderer(this, 20, 18);
        this.terraslugWings[2].addBox(-3.0F, 0.0F, terraslugBoxLength[4][2] * -0.5F, 6, 5, terraslugBoxLength[1][2]);
        this.terraslugWings[2].setRotationPoint(0.0F, 19.0F, this.field_78170_c[1]);
    }

    @Override
    public void render(Entity par1, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1);
        int i;

        for (i = 0; i < this.terraslugBodyParts.length; ++i) {
            this.terraslugBodyParts[i].render(par7);
        }

        for (i = 0; i < this.terraslugWings.length; ++i) {
            this.terraslugWings[i].render(par7);
        }
    }

    @Override
    public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity f7) {
        for (int i = 0; i < this.terraslugBodyParts.length; ++i) {
            this.terraslugBodyParts[i].rotateAngleY = MathHelper.cos(f3 * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.05F * (1 + Math.abs(i - 2));
            this.terraslugBodyParts[i].rotationPointX = MathHelper.sin(f3 * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.2F * Math.abs(i - 2);
        }

        this.terraslugWings[0].rotateAngleY = this.terraslugBodyParts[2].rotateAngleY;
        this.terraslugWings[1].rotateAngleY = this.terraslugBodyParts[4].rotateAngleY;
        this.terraslugWings[1].rotationPointX = this.terraslugBodyParts[4].rotationPointX;
        this.terraslugWings[2].rotateAngleY = this.terraslugBodyParts[1].rotateAngleY;
        this.terraslugWings[2].rotationPointX = this.terraslugBodyParts[1].rotationPointX;
    }
}