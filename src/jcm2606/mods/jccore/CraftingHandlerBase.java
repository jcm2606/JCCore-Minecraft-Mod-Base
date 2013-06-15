package jcm2606.mods.jccore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public abstract class CraftingHandlerBase implements ICraftingHandler {
    public abstract void onCraft(ItemStack stack, Object result, EntityPlayer player);
    
    public abstract void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv);
    
    public abstract void onSmelt(Object result, EntityPlayer player);

    @Override
    public void onCrafting(EntityPlayer player, ItemStack stack, IInventory inv) {
        Object result = stack.getItem();

        if (result != null) {
            onCraft(stack, result, player);
            
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) != null) {
                    ItemStack matrixItem = inv.getStackInSlot(i);
                    if (matrixItem.getItem() != null) {
                        Item item = matrixItem.getItem();

                        onCraftingMatrixChange(player, matrixItem, item, stack, i, inv);
                    }
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack stack) {
        Object result = stack.getItem();
        
        onSmelt(result, player);
    }
    
    public void keepItemInMatrix(Item item, int slot, IInventory inv)
    {
        ItemStack k = new ItemStack(item, 2);
        inv.setInventorySlotContents(slot, k);
    }
    
    public void keepItemInMatrixAndDamage(ItemStack stack, int slot, IInventory inv)
    {
        ItemStack k = new ItemStack(stack.getItem(), 2, stack.getItemDamage() + 1);
        inv.setInventorySlotContents(slot, k);
    }
}
