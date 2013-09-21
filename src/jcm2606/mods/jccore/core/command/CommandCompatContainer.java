package jcm2606.mods.jccore.core.command;

import java.util.Set;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.lib.Commands;
import jcm2606.mods.jccore.core.util.ChatUtil;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.network.PacketCompatContainerPostUpdate;
import jcm2606.mods.jccore.network.PacketHandlerCore;
import jcm2606.mods.jccore.network.PacketType;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandCompatContainer
{
    public static void processCommand(ICommandSender commandSender, String args[])
    {
        if (args[1].equalsIgnoreCase(Commands.COMMAND_CONTAINER_LIST))
        {
            processList(commandSender);
        } else
            if (args[1].equalsIgnoreCase(Commands.COMMAND_CONTAINER_POST_UPDATE))
            {
                processUpdatePost(commandSender, args);
            } else
            {
                throw new WrongUsageException("Command entered is not valid.");
            }
    }
    
    private static void processList(ICommandSender commandSender)
    {
        Set<String> containerNameList = CompatibilityContainer.getContainerList().keySet();
        String message;
        
        message = containerNameList.toString().replace("[", "").replace("]", "");
        
        ChatUtil.sendTextToCommandSender(commandSender, "Current registered containers:");
        ChatUtil.sendTextToCommandSender(commandSender, " " + message);
    }
    
    private static void processUpdatePost(ICommandSender commandSender, String[] args)
    {
        String compatContainerName = args[2];
        int updateId = Integer.valueOf(args[3]);
        
        if (GeneralUtil.isClient())
        {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(PacketType.populatePacket(new PacketCompatContainerPostUpdate(
                    compatContainerName, updateId), PacketHandlerCore.CHANNEL_JCCORE));
        } else
        {
            PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketCompatContainerPostUpdate(compatContainerName, updateId),
                    PacketHandlerCore.CHANNEL_JCCORE));
        }
    }
}
