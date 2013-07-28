package jcm2606.mods.jccore.core;


/**
 * Interface template for any classes revolved around object handling ({@link Item} and {@link Block} handling, name handling, recipe handling, etc).
 * 
 * @author Jcm2606
 */
public interface IObjectCore {
    /**
     * Used to load the objects this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void loadObjects();
    
    /**
     * Used to register the blocks this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void registerBlocks();
    
    /**
     * Used to load the object IDs this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectIdLoadingInClass()} is called.
     */
    public void loadIDs();
    
    /**
     * Used to load the enchantments this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void loadEnchantments();
    
    /**
     * Used to add the harvest levels for the blocks this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void addBlockHarvestLevels();
    
    /**
     * Used to add the names for the objects this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void addNames();
    
    /**
     * Used to add the names for the objects this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void addRecipes();
    
    /**
     * Used to add the names for the objects this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void addSmeltingRecipes();
    
    /**
     * Loads the biomes this {@link IObjectCore} is for. Called when {@link ModCompatibility.startObjectLoadingInClass()} is called.
     */
    public void loadBiomes();
}
