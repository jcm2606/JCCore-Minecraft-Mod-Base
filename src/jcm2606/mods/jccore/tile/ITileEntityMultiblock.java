package jcm2606.mods.jccore.tile;

import net.minecraft.tileentity.TileEntity;


public interface ITileEntityMultiblock
{
    /**
     *          FORWARD     BACKWARD
     * North:   -z              +z
     * South:   +z              -z
     * East:    +x              -x
     * West:    -x              +x
     * 
     * Should move BACKWARD for depth (facing = direction of block face, not direction of player looking at face)
     */
    public boolean isFormedCorrectly();
    public TileEntity getPrimaryTileEntity();
    public void invalidate();
    public void validate();
    public boolean isValid();
    public int getDimensionX();
    public int getDimensionY();
    public int getDimensionZ();
    public boolean hasCentreBlockClear();
    public int getStructureBlockID();
}
