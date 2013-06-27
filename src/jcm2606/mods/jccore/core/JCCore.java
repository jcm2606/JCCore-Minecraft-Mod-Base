package jcm2606.mods.jccore.core;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.command.CommandJCCore;
import jcm2606.mods.jccore.core.proxy.CommonProxy;
import jcm2606.mods.jccore.lib.Reference;
import jcm2606.mods.jccore.network.PacketHandlerCore;
import jcm2606.mods.jccore.util.LoggerBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "JCCore", name = "JC Core", version = JCCore.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = JCCore.VERSION, channels = { PacketHandlerCore.CHANNEL_COMPAT_CONTAINER }, packetHandler = PacketHandlerCore.class)
public class JCCore {
    public static final String VERSION = "0.1.0.0";

    @Instance("JCCore")
    public static JCCore instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_PATH, serverSide = Reference.COMMON_PROXY_PATH)
    public static CommonProxy proxy;

    public static LoggerBase logger = new LoggerBase("JC Core");

    @PreInit
    public static void preLoad(FMLPreInitializationEvent event)
    {
        event.getModMetadata().version = JCCore.VERSION;
        
        CompatibilityContainer.registerContainer(new CompatContainerJCCore());
    }

    @Init
    public static void load(FMLInitializationEvent event)
    {
    }

    @PostInit
    public static void postLoad(FMLPostInitializationEvent event)
    {
        proxy.loadRendering();
    }
    
    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandJCCore());
    }
}
