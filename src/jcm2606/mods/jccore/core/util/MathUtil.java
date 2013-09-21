package jcm2606.mods.jccore.core.util;

/**
 * Mathematics utilities and shortcuts.
 * 
 * Credit to some of the work in here goes to Asyncronous.
 * 
 * @author Jcm2606
 */
public class MathUtil
{
    public static boolean isDivisibleBy(int diviser, int value)
    {
        return value % diviser == 0;
    }
    
    public static boolean isEvenNumber(int value)
    {
        return value % 2 == 0;
    }
}
