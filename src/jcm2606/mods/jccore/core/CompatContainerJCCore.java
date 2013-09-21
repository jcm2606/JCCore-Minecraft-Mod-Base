package jcm2606.mods.jccore.core;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;

public class CompatContainerJCCore extends CompatibilityContainer
{
    @Override
    public Object getModClass()
    {
        return JCCore.instance;
    }
    
    @Override
    public String getContainerName()
    {
        return "API_JCCore";
    }
}
