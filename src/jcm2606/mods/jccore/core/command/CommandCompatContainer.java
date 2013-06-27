package jcm2606.mods.jccore.core.command;

import java.util.Set;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.lib.Commands;
import jcm2606.mods.jccore.network.PacketHandlerCore;
import jcm2606.mods.jccore.util.GeneralUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandCompatContainer {
    public static void processCommand(ICommandSender commandSender, String args[])
    {
        if(args[1].equalsIgnoreCase(Commands.COMMAND_CONTAINER_LIST))
        {
            processList(commandSender);
        } else
            if(args[1].equalsIgnoreCase(Commands.COMMAND_CONTAINER_POST_UPDATE))
            {
                processUpdatePost(commandSender, args);
            } else {
                throw new WrongUsageException("Command entered is not valid.");
            }
    }
    
    private static void processList(ICommandSender commandSender)
    {
        Set<String> containerNameList = CompatibilityContainer.getContainerList().keySet();
        String message;
        
        message = containerNameList.toString().replace("[", "").replace("]", "");
        
        commandSender.sendChatToPlayer("Current registered containers:");
        commandSender.sendChatToPlayer(" " + message);
    }
    
    private static void processUpdatePost(ICommandSender commandSender, String[] args)
    {
        String compatContainerName = args[2];
        int updateId = Integer.valueOf(args[3]);
        
        if(GeneralUtil.isClient())
        {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(PacketHandlerCore.constructCompatContainerUpdatePostPacket(compatContainerName, updateId));
        } else {
            PacketDispatcher.sendPacketToAllPlayers(PacketHandlerCore.constructCompatContainerUpdatePostPacket(compatContainerName, updateId));
        }
    }
}
