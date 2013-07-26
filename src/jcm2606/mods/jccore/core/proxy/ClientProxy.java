package jcm2606.mods.jccore.core.proxy;

import jcm2606.mods.jccore.core.IClientProxy;
import jcm2606.mods.jccore.util.RenderUtil;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy implements IClientProxy {
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
    }

    @Override
    public void loadKeyBindings() {}
}
