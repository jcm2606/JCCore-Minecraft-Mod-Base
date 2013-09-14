package jcm2606.mods.jccore.core;

import jcm2606.mods.jccore.block.IBlockHighlightRenderHandler;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class JCCoreEventHandler
{
    @ForgeSubscribe
    public void onBlockHighlightRender(DrawBlockHighlightEvent event)
    {
        Block block = Block.blocksList[event.player.worldObj.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ)];
        
        if(block instanceof IBlockHighlightRenderHandler)
        {
            ((IBlockHighlightRenderHandler) block).onHighlightRender(event.context, event.player, event.target, event.subID, event.currentItem, event.partialTicks);
        }
    }
}
