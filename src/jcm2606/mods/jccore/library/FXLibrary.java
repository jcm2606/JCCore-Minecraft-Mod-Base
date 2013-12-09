package jcm2606.mods.jccore.library;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import codechicken.lib.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXLibrary
{
    public static final FXEntry explosionHuge = new FXEntry("hugeexplosion");
    public static final FXEntry explosionLarge = new FXEntry("largeexplode");
    public static final FXEntry explosion = new FXEntry("explode");
    public static final FXEntry bubble = new FXEntry("bubble");
    public static final FXEntry suspend = new FXEntry("suspended");
    public static final FXEntry suspendDepth = new FXEntry("depthsuspend");
    public static final FXEntry redstone = new FXEntry("reddust");
    public static final FXEntry portal = new FXEntry("portal");
    public static final FXEntry flame = new FXEntry("flame");
    
    public static final class FXEntry {
        protected String name;
        protected EntityFX entityClass;
        
        public FXEntry(String name)
        {
            this.name = name;
            this.entityClass = null;
        }
        
        public FXEntry(String name, EntityFX entity)
        {
            this.name = name;
            this.entityClass = entity;
        }
        
        @SuppressWarnings("unused")
        public String getName()
        {
            return name;
        }
        
        public EntityFX getEntityClass()
        {
            return this.entityClass;
        }
    }
    
    public static void spawn(FXEntry particle, Vector3 location, Vector3 motion)
    {
        spawn(particle.getName(), location, motion);
    }
    
    public static void spawn(String name, Vector3 location, Vector3 motion)
    {
        Minecraft.getMinecraft().theWorld.spawnParticle(name, location.x, location.y, location.z, motion.x, motion.y, motion.z);
    }
    
    public static void spawn(EntityFX particle, Vector3 location, Vector3 motion)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }
}
