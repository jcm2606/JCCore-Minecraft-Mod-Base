package jcm2606.mods.jccore.core.util;

import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ResourceLocation;

public class ConvertUtil {
    public static ResourceLocation getResourceLocation(String modid, String location)
    {
        return new ResourceLocation(modid, location);
    }
    
    public static ChatMessageComponent getChatMessageComponent(String message)
    {
        ChatMessageComponent chatComponent = new ChatMessageComponent();
        
        chatComponent.func_111079_a(message);
        
        return chatComponent;
    }
}
