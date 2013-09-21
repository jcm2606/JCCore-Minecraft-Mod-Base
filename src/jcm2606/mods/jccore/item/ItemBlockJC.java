package jcm2606.mods.jccore.item;

import java.util.List;

import jcm2606.mods.jccore.block.IBlockAddon;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockJC extends ItemBlock
{
    public ItemBlockJC(int par1)
    {
        super(par1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if (Block.blocksList[stack.itemID] instanceof IBlockAddon)
        {
            return ((IBlockAddon) Block.blocksList[stack.itemID]).getRarity(stack);
        }
        
        return EnumRarity.common;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        if (Block.blocksList[stack.itemID] instanceof IBlockAddon)
        {
            ((IBlockAddon) Block.blocksList[stack.itemID]).addInformation(stack, player, list, b);
        }
    }
}
