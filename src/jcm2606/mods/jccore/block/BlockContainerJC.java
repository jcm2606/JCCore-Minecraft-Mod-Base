package jcm2606.mods.jccore.block;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public abstract class BlockContainerJC extends BlockContainer implements IBlockAddon
{
    String rarity;
    
    protected BlockContainerJC(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.rarity = "";
    }
    
    protected BlockContainerJC(int par1, Material par2Material, String par3)
    {
        super(par1, par2Material);
        this.rarity = par3;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(rarity);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {}
}
