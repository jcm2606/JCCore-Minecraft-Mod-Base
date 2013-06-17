package jcm2606.mods.jccore.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    public static NBTTagCompound getNBTCompoundForItemStack(ItemStack itemStack)
    {
        if (itemStack != null) {
            NBTTagCompound tag = itemStack.getTagCompound();
            if (tag == null) {
                tag = new NBTTagCompound();
                itemStack.setTagCompound(tag);
            }
            return tag;
        }
        return null;
    }

    public static void setInt(NBTTagCompound nbt, String valueName, int value)
    {
        nbt.setInteger(valueName, value);
    }

    public static void setString(NBTTagCompound nbt, String valueName, String value)
    {
        nbt.setString(valueName, value);
    }

    public static void setBoolean(NBTTagCompound nbt, String valueName, boolean value)
    {
        nbt.setBoolean(valueName, value);
    }

    public static void setIntArray(NBTTagCompound nbt, String valueName, int[] value)
    {
        nbt.setIntArray(valueName, value);
    }

    public static int[] getIntArray(NBTTagCompound nbt, String valueName)
    {
        return nbt.getIntArray(valueName);
    }

    public static int getInt(NBTTagCompound nbt, String valueName)
    {
        if (nbt != null) {
            return nbt.getInteger(valueName);
        }

        return -1;
    }

    public static String getString(NBTTagCompound nbt, String valueName)
    {
        if (nbt != null) {
            return nbt.getString(valueName);
        }

        return "";
    }

    public static boolean getBoolean(NBTTagCompound nbt, String valueName)
    {
        if (nbt != null) {
            return nbt.getBoolean(valueName);
        }

        return false;
    }
}
