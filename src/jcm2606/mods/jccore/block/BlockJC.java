package jcm2606.mods.jccore.block;

import jcm2606.mods.jccore.helper.RarityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class BlockJC extends Block implements IBlockRarity {
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
}
