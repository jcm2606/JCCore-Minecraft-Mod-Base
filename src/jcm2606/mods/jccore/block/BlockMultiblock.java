package jcm2606.mods.jccore.block;

import jcm2606.mods.jccore.tile.ITileEntityMultiblock;
import jcm2606.mods.jccore.tile.TileEntityJC;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class BlockMultiblock extends BlockContainerJC
{
    protected BlockMultiblock(int par1, Material par2Material)
    {
        super(par1, par2Material);
    }
    
    protected BlockMultiblock(int par1, Material par2Material, String par3)
    {
        super(par1, par2Material, par3);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof ITileEntityMultiblock)
            {
                ITileEntityMultiblock multiblock = (ITileEntityMultiblock) world.getBlockTileEntity(x, y, z);
                
                if(multiblock.getPrimaryTileEntity() != null)
                {
                    return ((TileEntityJC) multiblock.getPrimaryTileEntity()).onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
                }
            }
        }
        
        return false;
    }
}
