package jcm2606.mods.jccore.util;

import net.minecraft.nbt.NBTTagCompound;

public class Coord {
    public double x;
    public double y;
    public double z;
    
    public Coord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Coord(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Coord(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static Coord constructFromNBT(NBTTagCompound tag)
    {
        if(tag.hasKey("x") && tag.hasKey("y") && tag.hasKey("z"))
        {
            return new Coord(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
        } else {
            return null;
        }
    }
    
    public static NBTTagCompound constructNBTFromCoord(Coord coord)
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        return constructNBTFromCoord(tag, coord);
    }
    
    public static NBTTagCompound constructNBTFromCoord(NBTTagCompound tag, Coord coord)
    {
        tag.setInteger("x", (int) coord.x);
        tag.setInteger("y", (int) coord.y);
        tag.setInteger("z", (int) coord.z);
        
        return tag;
    }
}
