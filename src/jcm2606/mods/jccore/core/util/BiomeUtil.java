package jcm2606.mods.jccore.core.util;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeUtil
{
    public static int getFreeBiomeID(int preferred)
    {
        int id = preferred;
        
        if (BiomeGenBase.biomeList[id] != null)
        {
            for (int i = preferred; i < BiomeGenBase.biomeList.length; i++)
            {
                if (BiomeGenBase.biomeList[i] == null)
                {
                    id = i;
                    break;
                }
            }
        }
        
        return id;
    }
}
