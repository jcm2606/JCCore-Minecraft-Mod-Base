package jcm2606.mods.jccore.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LoggerBase
{
    private final Logger logger;
    
    public LoggerBase(String name)
    {
        this.logger = Logger.getLogger(name);
        logger.setParent(FMLLog.getLogger());
    }
    
    public void log(String message, Level level)
    {
        logger.log(level, message);
    }
    
    public void info(String message)
    {
        this.log(message, Level.INFO);
    }
    
    public void warning(String message)
    {
        this.log(message, Level.WARNING);
    }
    
    public void severe(String message)
    {
        this.log(message, Level.SEVERE);
    }
}
