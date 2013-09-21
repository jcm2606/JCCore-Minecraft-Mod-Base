package jcm2606.mods.jccore.compat.container;

/**
 * Base class for all {@link CompatibilityContainer} update post events
 */
public class ContainerEventBase
{
    
    /**
     * The ID of the particular event
     */
    public int id;
    
    public ContainerEventBase(int id)
    {
        this.id = id;
    }
}
