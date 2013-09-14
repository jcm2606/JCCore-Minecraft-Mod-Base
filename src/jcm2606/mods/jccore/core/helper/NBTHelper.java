package jcm2606.mods.jccore.core.helper;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLLog;

public class NBTHelper
{
    public static NBTTagCompound getNBTCompoundForItemStack(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            NBTTagCompound tag = itemStack.getTagCompound();
            if (tag == null)
            {
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
        if (nbt != null)
        {
            return nbt.getInteger(valueName);
        }

        return -1;
    }

    public static String getString(NBTTagCompound nbt, String valueName)
    {
        if (nbt != null)
        {
            return nbt.getString(valueName);
        }

        return "";
    }

    public static boolean getBoolean(NBTTagCompound nbt, String valueName)
    {
        if (nbt != null)
        {
            return nbt.getBoolean(valueName);
        }

        return false;
    }

    public static void saveData(WorldServer world, NBTTagCompound tag, String fileName) {
        try
        {
          File file = new File(world.getChunkSaveLocation(), fileName + ".dat");
          DataOutputStream dos = null;
        try
        {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        }
        catch (FileNotFoundException e1)
        {}
          try
          {
            CompressedStreamTools.write(tag, dos);
          }
          catch (IOException e) {
            FMLLog.log(Level.WARNING, e, "Unable to save custom NBT map '" + fileName + "' in world directory '" + world.getSaveHandler().getWorldDirectoryName() + "'.", new Object[] { file.getAbsolutePath() });
            return;
          }
          finally {
            try {
              if (dos != null)
              {
                  dos.close();
              } 
            }
            catch (IOException f) {}
          }
        } finally {}
    }

    public static NBTTagCompound loadData(WorldServer world, String fileName)
    {
        WorldServer worldServer = world;
        File file = new File(worldServer.getChunkSaveLocation(), fileName + ".dat");
        if ((file.exists()) && (file.isFile()))
        {
          NBTTagCompound nbt;
          try
          {
              nbt = CompressedStreamTools.read(file);
          }
          catch (IOException e)
          {
            FMLLog.log(Level.WARNING, e, "Unable to save custom NBT map '" + fileName + "' in world directory '" + world.getSaveHandler().getWorldDirectoryName() + "'.", new Object[] { file.getAbsolutePath() });
            return null;
          }
          return nbt;
        }
        return null;
    }
}
