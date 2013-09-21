package jcm2606.mods.jccore.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thanks to ApexAPI for the code used for this class.
 */
public class ItemMeta extends Item
{
    private final String[] names;
    private final String[] icons;
    private Icon[] iconList;
    
    public ItemMeta(int id, String[] names, String[] icons)
    {
        super(id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.names = names;
        this.icons = icons;
    }
    
    @Override
    public void registerIcons(IconRegister register)
    {
        iconList = new Icon[icons.length];
        
        for (int i = 0; i < icons.length; i++)
        {
            iconList[i] = register.registerIcon(icons[i]);
        }
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return iconList[meta];
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.names[stack.getItemDamage()];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int i = 0; i < this.icons.length; i++)
        {
            list.add(new ItemStack(itemID, 1, i));
        }
    }
}
