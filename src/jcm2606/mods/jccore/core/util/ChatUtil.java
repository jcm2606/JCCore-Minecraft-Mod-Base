package jcm2606.mods.jccore.core.util;

import net.minecraft.command.ICommandSender;

public class ChatUtil
{
    public static void sendTextToCommandSender(ICommandSender sender, String message)
    {
        sender.sendChatToPlayer(ConvertUtil.getChatMessageComponent(message));
    }
}
