package jcm2606.mods.jccore.client.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLocked extends Slot
{
    public boolean isLocked;
    
    public SlotLocked(IInventory par1iInventory, int par2, int par3, int par4)
    {
        super(par1iInventory, par2, par3, par4);
        isLocked = true;
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return !isLocked;
    }
}
