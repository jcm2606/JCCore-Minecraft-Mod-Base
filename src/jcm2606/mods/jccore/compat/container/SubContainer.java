package jcm2606.mods.jccore.compat.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a particular class as a {@link CompatibilityContainer} SubContainer.
 * 
 * @param value
 *            The unique SubContainer name.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SubContainer
{
    public String value() default "";
}
