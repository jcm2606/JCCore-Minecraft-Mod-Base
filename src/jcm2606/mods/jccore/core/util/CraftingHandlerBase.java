package jcm2606.mods.jccore.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

/**
 * Base class for any {@link ICraftingHandler} classes.
 * 
 * @author Jcm2606
 */
public abstract class CraftingHandlerBase implements ICraftingHandler {
    /**
     * Hook for an {@link Item} being pulled out of a crafting slot inside a crafting interface.
     * 
     * @param stack
     * @param result
     * @param player
     */
    public abstract void onCraft(ItemStack stack, Object result, EntityPlayer player);
    
    /**
     * Hook for any time the crafting matrix (main crafting square) changes. Pretty much also called when {@link CraftingHandlerBase.onCraft} is called.
     * 
     * @param player
     * @param stack
     * @param item
     * @param resultStack
     * @param slot
     * @param iinv
     */
    public abstract void onCraftingMatrixChange(EntityPlayer player, ItemStack stack, Item item, ItemStack resultStack, int slot, IInventory iinv);
    
    /**
     * Hook for any time an {@link Item} is pulled out of a smelting slot inside any smelting interface.
     * 
     * @param result
     * @param player
     */
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
    
    /**
     * Keep a particular {@link Item} inside the given {@link IInventory}.
     * 
     * @param item
     * @param slot
     * @param inv
     */
    public void keepItemInMatrix(Item item, int slot, IInventory inv)
    {
        ItemStack k = new ItemStack(item, 2);
        inv.setInventorySlotContents(slot, k);
    }
    
    /**
     * Keep a particular {@link Item} inside the given {@link IInventory} while also damaging it by 1 point.
     * 
     * @param item
     * @param slot
     * @param inv
     */
    public void keepItemInMatrixAndDamage(ItemStack stack, int slot, IInventory inv)
    {
        ItemStack k = new ItemStack(stack.getItem(), 2, stack.getItemDamage() + 1);
        inv.setInventorySlotContents(slot, k);
    }
}
