package jcm2606.mods.jccore.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockRenderHandler
{
    public void renderInv(Block block, int metadata, int modelID, RenderBlocks renderer);
    public boolean renderWorld(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer);
}
