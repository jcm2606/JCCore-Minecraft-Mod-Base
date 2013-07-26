package jcm2606.mods.jccore.keybind;

import net.minecraft.client.settings.KeyBinding;

public class KeyBind extends KeyBinding {
    public boolean isClientSided;
    
    public KeyBind(String par1Str, int par2) {
        super(par1Str, par2);
        this.isClientSided = false;
    }
}
