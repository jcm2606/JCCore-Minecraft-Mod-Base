package jcm2606.mods.jccore.client.render;

import jcm2606.mods.jccore.core.JCCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockRenderHandler implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        if(block instanceof IBlockRenderHandler)
        {
            ((IBlockRenderHandler) block).renderInv(block, metadata, modelID, renderer);
        }
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        if(block instanceof IBlockRenderHandler)
        {
            return ((IBlockRenderHandler) block).renderWorld(world, x, y, z, block, modelId, renderer);
        }
        
        return false;
    }
    
    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }
    
    @Override
    public int getRenderId()
    {
        return JCCore.renderIDUniversal;
    }
}
