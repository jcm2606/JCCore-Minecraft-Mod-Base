package jcm2606.mods.jccore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityFXJC extends EntityFX {
    public int blendMode = 771;
    
    public EntityFXJC(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }
    
    protected EntityFXJC(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    
    public void drawParticle(String spriteSheet, Tessellator tessellator, float f, float f1,
            float f2, float f3, float f4, float f5) {
        boolean wasDrawing = tessellator.isDrawing;

        if (wasDrawing) {
            tessellator.draw();
        }
        
        Minecraft.getMinecraft().renderEngine.bindTexture(spriteSheet);
        
        GL11.glEnable(32826);
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, blendMode);
        
        GL11.glPushMatrix();
        
        GL11.glDepthMask(false);

        float f6 = this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleIcon != null)
        {
            f6 = this.particleIcon.getMinU();
            f7 = this.particleIcon.getMaxU();
            f8 = this.particleIcon.getMinV();
            f9 = this.particleIcon.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
        float f14 = 1.0F;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(300);
        tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
        tessellator.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, f7, f8);
        tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f6, f8);
        tessellator.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, f6, f9);
        
        tessellator.draw();
        
        GL11.glDepthMask(true);
        
        GL11.glPopMatrix();

        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glDisable(3042);
        
        Minecraft.getMinecraft().renderEngine.bindTexture("/particles.png");
        
        if (wasDrawing) {
            tessellator.startDrawingQuads();
        }
    }
}
