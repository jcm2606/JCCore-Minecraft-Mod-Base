package jcm2606.mods.jccore.core.util;

import java.util.HashMap;

/**
 * Base class for the indexing of any icons.
 * 
 * @author ApexAPI Dev Team
 */
public class IconIndexer {
    public static String modid;
    
    public static HashMap<String, String> iconMap = new HashMap<String, String>();
    
    public IconIndexer(String modid) {
        this.modid = modid;
    }
    
    public static String getIcon(String name) {
        return iconMap.get(modid + ":" + name);
    }
    
    public static void registerIcon(String name)
    {
        iconMap.put(name, modid + ":" + name);
    }
    
    public static void registerIcon(String name, String texture)
    {
        iconMap.put(name, modid + ":" + texture);
    }
}
