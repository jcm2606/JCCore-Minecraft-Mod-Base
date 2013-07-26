package jcm2606.mods.jccore.compat;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.IObjectCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Core class for inter-mod compatibility.
 * 
 * @author Jcm2606
 */
public class ModCompatibility {
    private static final ModCompatibility instance = new ModCompatibility();
    
    public static ModCompatibility get()
    {
        return instance;
    }
    
    /**
     * Get the mod class from the given {@link CompatibilityContainer}
     * 
     * @param container
     * @return Mod class
     */
    public Object getModClassFromCompatContainer(CompatibilityContainer container)
    {
        return container.getModClass();
    }
    
    /**
     * Get a {@link Block} instance of the given variable inside the given class.
     * 
     * @param classPath
     * @param varName
     * @return The {@link Block}
     */
    public Block getBlock(String classPath, String varName)
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
    
    /**
     * Get an {@link Item} instance of the given variable inside the given class.
     * 
     * @param classPath
     * @param varName
     * @return The {@link Item}
     */
    public Item getItem(String classPath, String varName)
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
    
    /**
     * Starts object loading in the given {@link Class}. Given {@link Class} MUST implement {@link IObjectCore} for this to work.
     * 
     * @param clazz
     */
    public void startObjectLoadingInClass(Class<?> clazz)
    {
        try {
            Object obj = clazz.newInstance();
            
            if(obj instanceof IObjectCore)
            {
                IObjectCore objectCore = (IObjectCore) obj;
                
                objectCore.loadObjects();
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Starts object ID loading in the given {@link Class}. Given {@link Class} MUST implement {@link IObjectCore} for this to work.
     * 
     * @param clazz
     */
    public void startObjectIdLoadingInClass(Class<?> clazz)
    {
        try {
            Object obj = clazz.newInstance();
            
            if(obj instanceof IObjectCore)
            {
                IObjectCore objectCore = (IObjectCore) obj;
                
                objectCore.loadIDs();
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
