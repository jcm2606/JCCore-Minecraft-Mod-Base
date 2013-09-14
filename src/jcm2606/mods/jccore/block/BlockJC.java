package jcm2606.mods.jccore.block;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.tile.TileEntityJC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Base class for any {@link Block} classes created.
 * 
 * @author Jcm2606
 */
public class BlockJC extends Block implements IBlockAddon {
    String rarity;
    
    public BlockJC(int par1, Material par2Material, String par3) {
        super(par1, par2Material);
        this.rarity = par3;
    }
    
    public BlockJC(int par1, Material par2Material) {
        super(par1, par2Material);
        this.rarity = "";
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(rarity);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {}
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlock) {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onNeighborBlockChange(world, x, y, z, neighbourBlock);
            }
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
            }
        }
        
        return false;
    }
    
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onEntityWalking(world, x, y, z, entity);
            }
        }
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onBlockClicked(world, x, y, z, player);
            }
        }
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                return ((TileEntityJC) world.getBlockTileEntity(x, y, z)).isProvidingWeakPower(world, x, y, z, side);
            }
        }
        
        return 0;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(world.getBlockTileEntity(x, y, z) != null)
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityJC)
            {
                ((TileEntityJC) world.getBlockTileEntity(x, y, z)).onEntityCollidedWithBlock(world, x, y, z, entity);
            }
        }
    }
}
