package jcm2606.mods.jccore.core;

/**
 * Base interface for any ForgeModLoader CommonProxy class.
 * 
 * @author Jcm2606
 */
public interface IProxyCommon {
    /**
     * Loads the rendering features.
     * 
     * Does nothing on the server.
     */
    public void loadRendering();

    /**
     * Loads the TileEntity's for a mod.
     */
    public void loadTileEntities();

    /**
     * Loads the custom rarities for a mod.
     * 
     * Does nothing on the server.
     */
    public void loadCustomRarities();

    /**
     * Registers the handlers for a mod.
     */
    public void registerHandlers();
    
    /**
     * Loads the keybindings for a mod.
     * 
     * Does nothing on the server.
     */
    public void loadKeyBindings();
    
    /**
     * Loads the render ids for a mod.
     * 
     * Does nothing on the server.
     */
    public void loadRenderIds();
    
    /**
     * Loads the mobs for a mod.
     */
    public void loadMobs();
}
