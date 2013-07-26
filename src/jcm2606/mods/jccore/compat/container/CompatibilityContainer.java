package jcm2606.mods.jccore.compat.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Compatibility Container base class. Central class for all classes which are a form of {@link CompatibilityContainer}.
 * 
 * A compatibility container is essentially a custom event handling class which allows a specific mod using JCCore to
 * handle events. The Forge/FML event system CAN be used, however this system plans to have certain features the Forge/FML
 * event system and EventBus do not have.
 */
public abstract class CompatibilityContainer {
    private static HashMap<String, Object> subContainerList = new HashMap<String, Object>();
    private static HashMap<String, CompatibilityContainer> containerList = new HashMap<String, CompatibilityContainer>();
    
    /**
     * The prefix for {@link CompatibilityContainer} names
     */
    public static final String COMPATIBILITY_CONTAINER_NAME_PREFIX = "CompatContainer:";
    
    public static void registerSubContainer(Class<?> clazz)
    {
        Object obj = null;
        try {
            obj = clazz.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        if(obj == null)
        {
            throw new RuntimeException("Error: Could not get instance of class " + clazz.getSimpleName() + ".");
        }
        
        if(clazz.isAnnotationPresent(SubContainer.class))
        {
            SubContainer annot = clazz.getAnnotation(SubContainer.class);
            
            if(annot == null)
            {
                throw new RuntimeException("Error: Could not get instance of annotation.");
            }
            
            if(annot.value() != "")
            {
                subContainerList.put(annot.value(), obj);
            } else {
                throw new RuntimeException("Unexpected error ocurred.");
            }
        }
    }
    
    /**
     * Post an update to all {@link SubContainer}s registered under this container with the update id given. -1 for wildcard.
     * 
     * @param id
     * @param args
     */
    public static void postUpdateToSubContainers(int id, ContainerEventBase args)
    {
        for(Object obj : subContainerList.values())
        {
            Class<?> clazz = obj.getClass();
            
            for(Method method : clazz.getMethods())
            {
                if(method.isAnnotationPresent(HandlerMethod.class))
                {
                    HandlerMethod annot = method.getAnnotation(HandlerMethod.class);
                    
                    if(annot.value() == id || annot.value() == -1)
                    {
                        try {
                            method.invoke(obj, args);
                        }
                        catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Register the given {@link CompatibilityContainer}. Not required, however is recommended for compatibility reasons.
     * 
     * @param container
     */
    public static void registerContainer(CompatibilityContainer container)
    {
        containerList.put(COMPATIBILITY_CONTAINER_NAME_PREFIX + container.getContainerName(), container);
    }
    
    public static HashMap<String, CompatibilityContainer> getContainerList()
    {
        return containerList;
    }
    
    /**
     * Returns the mod class for this {@link CompatibilityContainer}.
     * 
     * @return The mod class
     */
    public abstract Object getModClass();
    
    /**
     * Returns the name of this {@link CompatibilityContainer}.
     * 
     * @return The name of this {@link CompatibilityContainer}.
     */
    public abstract String getContainerName();
}
