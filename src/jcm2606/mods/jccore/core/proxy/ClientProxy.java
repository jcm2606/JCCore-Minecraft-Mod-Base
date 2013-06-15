package jcm2606.mods.jccore.core.proxy;

import jcm2606.mods.jccore.IClientProxy;
import jcm2606.mods.jccore.util.RenderUtil;

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
    public void registerHandlers() {}
}
