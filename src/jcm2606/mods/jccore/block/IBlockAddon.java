package jcm2606.mods.jccore.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public abstract interface IBlockAddon
{
    public abstract EnumRarity getRarity(ItemStack stack);
    
    public abstract void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b);
}
