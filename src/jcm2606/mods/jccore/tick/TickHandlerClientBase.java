package jcm2606.mods.jccore.tick;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base class for any {@link ITickHandler} classes for the client-side of the game's logic.
 * 
 * @author Jcm2606
 */
@SideOnly(Side.CLIENT)
public abstract class TickHandlerClientBase implements ITickHandler
{
    boolean clientWorldLoaded;

    /**
     * Hook for client world loading.
     * 
     * @param mc
     * @param world
     */
    public abstract void onClientWorldLoad(Minecraft mc, World world);
    
    /**
     * Hook for client world unloading.
     * 
     * @param mc
     * @param world
     */
    public abstract void onClientWorldUnload(Minecraft mc, World world);
    
    /**
     * Hook for client world ticking.
     * 
     * @param mc
     * @param world
     */
    public abstract void onClientWorldTick(Minecraft mc, World world);
    
    /**
     * Hook for HUD ticking.
     * 
     * @param mc
     */
    public abstract void onHUDTick(Minecraft mc);
    
    /**
     * Hook for GUI ticking.
     * 
     * @param mc
     * @param guiscreen
     */
    public abstract void onGUITick(Minecraft mc, GuiScreen guiscreen);
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            onHUDTick(Minecraft.getMinecraft());
        }
        else if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            if (guiscreen != null)
            {
                onGUITick(Minecraft.getMinecraft(), guiscreen);
                
                if(guiscreen != new GuiIngameMenu())
                {
                    if(clientWorldLoaded && FMLClientHandler.instance().getClient().theWorld == null)
                    {
                        clientWorldLoaded = false;
                        onClientWorldUnload(Minecraft.getMinecraft(), FMLClientHandler.instance().getClient().theWorld);
                    }
                }
            } else {
                onClientWorldTick(Minecraft.getMinecraft(), FMLClientHandler.instance().getClient().theWorld);
                
                if(!clientWorldLoaded && FMLClientHandler.instance().getClient().theWorld != null)
                {
                    clientWorldLoaded = true;
                    onClientWorldLoad(Minecraft.getMinecraft(), FMLClientHandler.instance().getClient().theWorld);
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return null;
    }
}