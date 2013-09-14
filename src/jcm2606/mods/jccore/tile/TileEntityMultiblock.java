package jcm2606.mods.jccore.tile;

public abstract class TileEntityMultiblock extends TileEntityJC implements ITileEntityMultiblock
{
    public int primaryBlockX;
    public int primaryBlockY;
    public int primaryBlockZ;
    
    public boolean isValid;
    
    @Override
    public void updateEntity()
    {
        this.isValid = this.isFormedCorrectly();
    }
}
