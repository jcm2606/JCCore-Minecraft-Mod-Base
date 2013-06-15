package jcm2606.mods.jccore.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderUtil {
    private static final RenderUtil INSTANCE = new RenderUtil();
    
    public static FontRenderer     fontRenderer           = Minecraft
                                                                  .getMinecraft().fontRenderer;
    public static FontRenderer     fontRendererGlyph      = Minecraft
                                                                  .getMinecraft().standardGalacticFontRenderer;

    public static ScaledResolution sr;                                                                          ;

    public static int              width;
    public static int              height;

    public static RenderItem renderItem = new RenderItem();
    public static RenderBlocks renderBlocks;
    public static RenderEngine renderEngine;
    public static RenderManager renderManager = RenderManager.instance;
    public static RenderGlobal renderGlobal;

    public static float prevVignetteBrightness = 1.0F;

    public static void loadRenderingUtils()
    {
        renderGlobal = Minecraft.getMinecraft().renderGlobal;
        renderBlocks = renderGlobal.globalRenderBlocks;
        renderEngine = Minecraft.getMinecraft().renderEngine;
    }
    
    public static RenderUtil getInstance()
    {
        return INSTANCE;
    }
    
    public int setBrightness(IBlockAccess blockAccess, int i, int j,
            int k, Block block, int brightness)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(brightness);

        float f = 1.0F;

        int l = block.colorMultiplier(blockAccess, i, j, k);
        float f1 = (l >> 16 & 0xFF) / 255.0F;
        float f2 = (l >> 8 & 0xFF) / 255.0F;
        float f3 = (l & 0xFF) / 255.0F;
        if (EntityRenderer.anaglyphEnable) {
            float f6 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f7 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f6;
            f2 = f4;
            f3 = f7;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        return brightness;
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, xscale, yscale, zscale, colourR, colourG, colourB
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4,
            int par5, int par6, float xscale, float yscale, float zscale,
            double colourR, double colourG, double colourB)
    {
        GL11.glPushMatrix();
        GL11.glScalef(xscale, yscale, zscale);
        GL11.glDepthMask(false);
        GL11.glColor4d(colourR, colourG, colourB, 1.0);
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, 1.0f, (par3 + 0) * var7,
                (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, 1.0f, (par3 + par5)
                * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, 1.0f, (par3 + par5) * var7,
                (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, 1.0f, (par3 + 0) * var7,
                (par4 + 0) * var8);
        var9.draw();
        GL11.glDepthMask(true);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, xscale, yscale, zscale
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4,
            int par5, int par6, float xscale, float yscale, float zscale)
    {
        drawTextureRect(par1, par2, par3, par4, par5, par6, xscale, yscale,
                zscale, 1.0, 1.0, 1.0);
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, scale
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4,
            int par5, int par6, float scale)
    {
        drawTextureRect(par1, par2, par3, par4, par5, par6, scale, scale, scale);
    }

    public static void disableLightMapTexture()
    {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static void enableLightMapTexture()
    {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public void drawFullScreenOverlay(int x, int y, String texturePath)
    {
        drawFullScreenOverlay(x, y, texturePath, 1.0f, 1.0f, 1.0f);
    }

    public void drawFullScreenOverlay(int x, int y, String texturePath,
            float colourR, float colourG, float colourB)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(colourR, colourG, colourB, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,
                Minecraft.getMinecraft().renderEngine.getTexture(texturePath));
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(0.0D, y, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV(x, y, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV(x, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void translateToWorldCoords(Entity entity, float frame)
    {
        double interpPosX = entity.lastTickPosX
                + (entity.posX - entity.lastTickPosX) * frame;
        double interpPosY = entity.lastTickPosY
                + (entity.posY - entity.lastTickPosY) * frame;
        double interpPosZ = entity.lastTickPosZ
                + (entity.posZ - entity.lastTickPosZ) * frame;

        GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);
    }

    public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
    {
        Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
                par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
                par1AxisAlignedBB.maxZ);
        var2.draw();
    }
    
    public void renderVignette(float par1, int par2, int par3)
    {
        par1 = 1.0F - par1;

        if (par1 < 0.0F)
        {
            par1 = 0.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        GL11.glColor4f(par1, par1, par1, 1.0F);
        renderEngine.bindTexture("%blur%/misc/vignette.png");
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, par3, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(par2, par3, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(par2, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    public void renderLabel(String par2Str, double par3, double par5, double par7)
    {
        FontRenderer fontrenderer = fontRenderer;
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par3 + 0.0F, (float)par5, (float)par7);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-f1, -f1, f1);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.instance;
        byte b0 = 0;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        int j = fontrenderer.getStringWidth(par2Str) / 2;
        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tessellator.addVertex(-j - 1, -1 + b0, 0.0D);
        tessellator.addVertex(-j - 1, 8 + b0, 0.0D);
        tessellator.addVertex(j + 1, 8 + b0, 0.0D);
        tessellator.addVertex(j + 1, -1 + b0, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, 553648127);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
    
    public void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, Icon tex)
    {
      renderAllSides(world, x, y, z, block, renderer, tex, true);
    }

    public void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, Icon tex, boolean allsides)
    {
      if ((allsides) || (block.shouldSideBeRendered(world, x + 1, y, z, 6))) {
        renderer.renderFaceZPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x - 1, y, z, 6))) {
        renderer.renderFaceZNeg(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y, z + 1, 6))) {
        renderer.renderFaceXPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y, z - 1, 6))) {
        renderer.renderFaceXNeg(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y + 1, z, 6))) {
        renderer.renderFaceYPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y - 1, z, 6))) {
        renderer.renderFaceYNeg(block, x, y, z, tex);
    }
    }

    public void renderAllSidesInverted(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, Icon tex, boolean allsides)
    {
      if ((allsides) || (!block.shouldSideBeRendered(world, x - 1, y, z, 6))) {
        renderer.renderFaceZPos(block, x - 1, y, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x + 1, y, z, 6))) {
        renderer.renderFaceZNeg(block, x + 1, y, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y, z - 1, 6))) {
        renderer.renderFaceXPos(block, x, y, z - 1, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y, z + 1, 6))) {
        renderer.renderFaceXNeg(block, x, y, z + 1, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y - 1, z, 6))) {
        renderer.renderFaceYPos(block, x, y - 1, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y + 1, z, 6))) {
        renderer.renderFaceYNeg(block, x, y + 1, z, tex);
    }
    }

    public void renderAllSides(int x, int y, int z, Block block, RenderBlocks renderer, Icon tex)
    {
      renderer.renderFaceZPos(block, x - 1, y, z, tex);
      renderer.renderFaceZNeg(block, x + 1, y, z, tex);
      renderer.renderFaceXPos(block, x, y, z - 1, tex);
      renderer.renderFaceZNeg(block, x, y, z + 1, tex);
      renderer.renderFaceYPos(block, x, y - 1, z, tex);
      renderer.renderFaceYNeg(block, x, y + 1, z, tex);
    }
}
