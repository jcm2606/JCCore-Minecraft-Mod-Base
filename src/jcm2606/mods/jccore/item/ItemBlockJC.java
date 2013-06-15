package jcm2606.mods.jccore.item;

import jcm2606.mods.jccore.block.IBlockRarity;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockJC extends ItemBlock
{
    public ItemBlockJC(int par1) {
        super(par1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if(Block.blocksList[stack.itemID] instanceof IBlockRarity)
        {
            return ((IBlockRarity) Block.blocksList[stack.itemID]).getRarity(stack);
        }
        
        return EnumRarity.common;
    }
}