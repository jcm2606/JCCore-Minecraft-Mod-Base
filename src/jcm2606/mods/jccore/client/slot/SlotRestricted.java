package jcm2606.mods.jccore.client.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRestricted extends Slot
{
    private final ItemStack[] allowedItems;
    private boolean damageRestrictions;
    
    public SlotRestricted(IInventory iinv, int id, int x, int y, ItemStack[] allowedItems, boolean damageRestrictions)
    {
        super(iinv, id, x, y);
        this.allowedItems = allowedItems;
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        for(ItemStack stack2 : allowedItems)
        {
            if(stack.isItemEqual(stack2))
            {
                if(damageRestrictions)
                {
                    return stack.getItemDamage() == stack2.getItemDamage();
                } else {
                    return true;
                }
            }
        }
        
        return false;
    }
}
