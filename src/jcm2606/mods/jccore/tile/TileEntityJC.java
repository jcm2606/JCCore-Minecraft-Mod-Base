package jcm2606.mods.jccore.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TileEntityJC extends TileEntity {
    protected long ticks = 0;
    
    @Override
    public void updateEntity()
    {
        if(this.ticks == 0)
        {
            this.load();
        }
        
        if(this.ticks >= Long.MAX_VALUE)
        {
            this.ticks = 1;
        }
        
        ticks++;
    }
    
    /**
     * Called on the tile entities first tick
     */
    public void load() {}
    
    public long getTicks()
    {
        return this.ticks;
    }
    
    public Block getBlockAtCoords(int x, int y, int z)
    {
        return Block.blocksList[getWorldObj().getBlockId(x, y, z)];
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlock) {}
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        return false;
    }
    
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {}
    
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {}
    
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        return 0;
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {}
}
