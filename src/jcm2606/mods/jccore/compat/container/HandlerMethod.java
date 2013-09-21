package jcm2606.mods.jccore.compat.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a particular method as a {@link CompatibilityContainer} update handler.
 * 
 * @param value
 *            The unique handler ID for these handler methods. Is
 *            {@link CompatibilityContainer} unique. Defaults to -1 (wildcard)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HandlerMethod
{
    public int value() default -1;
}
