package jcm2606.mods.jccore.block;

import net.minecraft.block.material.Material;

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
}
