package jcm2606.mods.jccore.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public abstract class ItemRendererBase implements IItemRenderer {
    public float entityX = -1f;
    public float entityY = -1f;
    public float entityZ = -1f;
    
    public float equippedX = 0;
    public float equippedY = 0;
    public float equippedZ = 0;
    
    public float equippedFirstPersonX = 0;
    public float equippedFirstPersonY = 0;
    public float equippedFirstPersonZ = 0;
    
    public float invX = 0;
    public float invY = -0.1f;
    public float invZ = 0;
    
    public ItemRendererBase()
    {}
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case ENTITY:
                renderEntity(entityX, entityY, entityZ, item);
                break;
            case EQUIPPED:
                renderEquipped(equippedX, equippedY, equippedZ, item);
                break;
            case EQUIPPED_FIRST_PERSON:
                renderEquipped(equippedFirstPersonX, equippedFirstPersonY, equippedFirstPersonZ, item);
                break;
            case INVENTORY:
                renderInventory(invX, invY, invZ, item);
                break;
            default:
                break;
        }
    }
    
    public abstract void renderEquipped(float posX, float posY, float posZ, ItemStack stack);
    
    public abstract void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack);
    
    public abstract void renderEntity(float posX, float posY, float posZ, ItemStack stack);
    
    public abstract void renderInventory(float posX, float posY, float posZ, ItemStack stack);
}