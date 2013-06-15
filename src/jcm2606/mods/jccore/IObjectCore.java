package jcm2606.mods.jccore;

import java.util.HashMap;

import net.minecraft.util.Icon;
import apex.util.ApexIconIndexer;

public interface IObjectCore {
    public void loadObjects();
    public void registerBlocks();
    public void loadIDs();
    public void loadEnchantments();
    
    public void registerFluids();
    public void addBlockHarvestLevels();
    
    public void addNames();
    public void addRecipes();
    public void addSmeltingRecipes();
    
    public void loadTextures(HashMap<String, Icon> iconMap, ApexIconIndexer index);
}
