package jcm2606.mods.jccore.core.helper;

import net.minecraft.item.EnumRarity;

public class RarityHelper
{
    public static EnumRarity getCustomRarityType(String customRarity)
    {
        for (EnumRarity rarity : EnumRarity.class.getEnumConstants())
        {
            if (rarity.name().equals(customRarity))
                return rarity;
        }
        return EnumRarity.common;
    }
}
