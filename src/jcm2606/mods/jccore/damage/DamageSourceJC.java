package jcm2606.mods.jccore.damage;

import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Base class for any custom {@link DamageSource}.
 * 
 * @author Jcm2606
 */
public abstract class DamageSourceJC extends DamageSource
{
    public final String PLAYER_NAME_CHARACTER = "%1$s";
    
    public DamageSourceJC(String name)
    {
        super(name);
    }
    
    /**
     * Allows any custom {@link DamageSource} to have a custom death message
     * with ease.
     * 
     * @param message
     */
    public void setDeathMessage(String message)
    {
        LanguageRegistry.instance().addStringLocalization("death.attack." + this.damageType, message);
    }
}
