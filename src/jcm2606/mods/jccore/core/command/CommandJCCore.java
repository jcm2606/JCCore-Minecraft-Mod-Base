package jcm2606.mods.jccore.core.command;

import java.util.List;

import jcm2606.mods.jccore.lib.Commands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandJCCore extends CommandBase {
    @Override
    public String getCommandName()
    {
        return "jccore";
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] args)
    {
        if(args.length > 0)
        {
            if(args[0].equalsIgnoreCase(Commands.COMMAND_CONTAINER))
            {
                CommandCompatContainer.processCommand(icommandsender, args);
            } else {
                throw new WrongUsageException("Command entered is not valid.");
            }
        } else {
            throw new WrongUsageException("This command has been used incorrectly. Commands have to be entered like: /jccore <command> <command args>");
        }
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {
        switch (args.length) {
            case 1 : {
                return getListOfStringsMatchingLastWord(args, new String[] { Commands.COMMAND_CONTAINER });
            }
            
            case 2 : {
                if(args[0].equals(Commands.COMMAND_CONTAINER))
                {
                    return getListOfStringsMatchingLastWord(args, new String[] { Commands.COMMAND_CONTAINER_LIST, Commands.COMMAND_CONTAINER_POST_UPDATE });
                }
            }
            
            case 3 : {
                return null;
            }
            
            default : {
                return null;
            }
        }
    }
}
