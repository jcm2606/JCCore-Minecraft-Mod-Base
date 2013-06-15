package jcm2606.mods.jccore.block;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public abstract interface IBlockRarity {
    public abstract EnumRarity getRarity(ItemStack stack);
}
