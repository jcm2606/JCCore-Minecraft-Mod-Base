package jcm2606.mods.jccore.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Base class for the indexing of any icons.
 * 
 * @author ApexAPI Dev Team
 */
public class IconIndexer {
    private final String modid;
    
    private final TextureStitchEvent.Pre event;
    
    /**
     * Initialise when a {@link TextureStitchEvent.Pre} event has been posted
     * 
     * @param modid
     * @param event
     */
    public IconIndexer(String modid, TextureStitchEvent.Pre event) {
        this.modid = modid;
        this.event = event;
    }
    
    /**
     * Returns the {@link Icon} for the given name.
     * 
     * @param name
     * @param isBlock
     * @return {@link Icon}
     */
    public Icon getIcon(String name, boolean isBlock) {
        
        RenderEngine eng = Minecraft.getMinecraft().renderEngine;
        TextureMap map = this.event.map;
        
        String toReg = this.modid + ":" + name;
        
        if(map == eng.textureMapBlocks && isBlock) {
            
            return map.registerIcon(toReg);
        } else if(map == eng.textureMapItems && !isBlock) {
            
            return map.registerIcon(toReg);
        } else {
            
            return map.registerIcon(toReg);
        }
    }
    

    /**
     * Returns the {@link TextureStitchEvent.Pre} event that is bound to this {@link IconIndexer}
     * 
     * @return {@link TextureStitchEvent.Pre}
     */
    public TextureStitchEvent.Pre getEvent()
    {
        return this.event;
    }
}
