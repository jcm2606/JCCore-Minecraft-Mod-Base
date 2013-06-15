package jcm2606.mods.jccore;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityJC extends TileEntity {
    public Block getBlockAtCoords(int x, int y, int z)
    {
        return Block.blocksList[getWorldObj().getBlockId(x, y, z)];
    }
}
