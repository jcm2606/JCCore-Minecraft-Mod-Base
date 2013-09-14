package jcm2606.mods.jccore.core.proxy;

import jcm2606.mods.jccore.core.IProxyClient;
import jcm2606.mods.jccore.core.JCCoreEventHandler;
import jcm2606.mods.jccore.util.RenderUtil;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy implements IProxyClient {
    @Override
    public void loadRendering() {
        RenderUtil.loadRenderingUtils();
    }

    @Override
    public void loadTileEntities() {}

    @Override
    public void loadCustomRarities() {}

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        
        TickRegistry.registerTickHandler(new RenderUtil(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new JCCoreEventHandler());
    }

    @Override
    public void loadKeyBindings() {}
}
