package jcm2606.mods.jccore.compat;

import java.util.HashMap;

import jcm2606.mods.jccore.IObjectCore;
import jcm2606.mods.jccore.core.JCCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import apex.util.ApexIconIndexer;
import cpw.mods.fml.common.Loader;

public class ModCompatibility {
    public static boolean isSCInstalled;
    
    public static boolean globalModCompatFlag;
    
    public static boolean checkSCInstalled()
    {
        if(Loader.isModLoaded("SorceryCraft"))
        {
            JCCore.logger.info("SorceryCraft has been detected.");
            return true;
        }
        
        return false;
    }
    
    public static Block getBlock(String classPath, String varName)
    {
        try {
            return (Block) Class.forName(classPath).getField(varName).get(null);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static Item getItem(String classPath, String varName)
    {
        try {
            return (Item) Class.forName(classPath).getField(varName).get(null);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void startObjectLoadingInClass(Class<?> clazz)
    {
        try {
            Object obj = clazz.newInstance();
            
            if(obj instanceof IObjectCore)
            {
                IObjectCore objectCore = (IObjectCore) obj;
                
                objectCore.loadObjects();
            }
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public static void startObjectIdLoadingInClass(Class<?> clazz)
    {
        try {
            Object obj = clazz.newInstance();
            
            if(obj instanceof IObjectCore)
            {
                IObjectCore objectCore = (IObjectCore) obj;
                
                objectCore.loadIDs();
            }
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public static void startTextureLoadingInClass(Class<?> clazz, ApexIconIndexer index, HashMap<String, Icon> iconMap)
    {
        try {
            Object obj = clazz.newInstance();
            
            if(obj instanceof IObjectCore)
            {
                IObjectCore objectCore = (IObjectCore) obj;
                
                objectCore.loadTextures(iconMap, index);
            }
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
