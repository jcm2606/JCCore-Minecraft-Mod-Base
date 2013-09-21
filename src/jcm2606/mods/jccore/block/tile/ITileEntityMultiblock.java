package jcm2606.mods.jccore.block.tile;

import net.minecraft.tileentity.TileEntity;

/**
 * Base interface for any {@link TileEntity} based Multi-Block structures
 */
public interface ITileEntityMultiblock
{
    /**
     * Call when you wish to detect whether this multiblock structure is formed correctly
     * 
     * @return Is the multiblock structure formed correctly
     */
    public boolean isFormedCorrectly();
}
