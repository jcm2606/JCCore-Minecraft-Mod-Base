package jcm2606.mods.jccore.tick;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public abstract class TickHandlerServerBase implements ITickHandler
{
    public abstract void onServerWorldLoad(World world);
    
    public abstract void onServerWorldTick(World world);
    
    public abstract void onServerPlayerTick(EntityPlayer player, World world);
    
    public abstract void onServerTick();
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (type.equals(EnumSet.of(TickType.WORLDLOAD)))
        {
            onServerWorldLoad((World) tickData[0]);
        } else
            if(type.equals(EnumSet.of(TickType.WORLD)))
            {
                onServerWorldTick((World) tickData[0]);
            } else
                if(type.equals(EnumSet.of(TickType.PLAYER)))
                {
                    onServerPlayerTick((EntityPlayer) tickData[0], (World) tickData[1]);
                } else
                {
                    onServerTick();
                }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.WORLDLOAD, TickType.SERVER, TickType.WORLD, TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return null;
    }
}