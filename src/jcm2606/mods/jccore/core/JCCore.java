package jcm2606.mods.jccore.core;

import jcm2606.mods.jccore.client.render.BlockRenderHandler;
import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.command.CommandJCCore;
import jcm2606.mods.jccore.core.lib.Reference;
import jcm2606.mods.jccore.core.util.LoggerBase;
import jcm2606.mods.jccore.network.PacketHandlerCore;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "JCCore", name = "JC Core", version = JCCore.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = JCCore.VERSION, channels =
{ PacketHandlerCore.CHANNEL_JCCORE }, packetHandler = PacketHandlerCore.class)
public class JCCore
{
    public static final String VERSION = "0.1.0.0";
    
    @Instance("JCCore")
    public static JCCore instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_PATH, serverSide = Reference.COMMON_PROXY_PATH)
    public static CommonProxy proxy;
    
    public static LoggerBase logger = new LoggerBase("JC Core");
    
    public static int renderIDUniversal;
    
    @EventHandler
    public static void preLoad(FMLPreInitializationEvent event)
    {
        event.getModMetadata().version = JCCore.VERSION;
        
        CompatibilityContainer.registerContainer(new CompatContainerJCCore());
        
        renderIDUniversal = RenderingRegistry.getNextAvailableRenderId();
        
        RenderingRegistry.registerBlockHandler(new BlockRenderHandler());
        
        proxy.registerHandlers();
    }
    
    @EventHandler
    public static void load(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public static void postLoad(FMLPostInitializationEvent event)
    {
        proxy.loadRendering();
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandJCCore());
    }
}
