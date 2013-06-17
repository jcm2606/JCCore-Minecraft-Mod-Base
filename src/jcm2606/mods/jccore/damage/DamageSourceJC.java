package jcm2606.mods.jccore.damage;

import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class DamageSourceJC extends DamageSource {
    public final String PLAYER_NAME_CHARACTER = "%1$s";
    
    public DamageSourceJC(String name) {
        super(name);
    }
    
    public void setDeathMessage(String message)
    {
        LanguageRegistry.instance().addStringLocalization("death.attack." + this.damageType, message);
    }
}
