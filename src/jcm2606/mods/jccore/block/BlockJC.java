package jcm2606.mods.jccore.block;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

/**
 * Base class for any {@link Block} classes created.
 * 
 * @author Jcm2606
 */
public class BlockJC extends Block implements IBlockCustomItem {
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
}
