package jcm2606.mods.jccore.core.util;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.ChatAllowedCharacters;

public class StringUtil {
    private static byte[] charWidth = new byte[] { 4, 2, 5, 6, 6, 6, 6, 3, 5,
            5, 5, 6, 2, 6, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 5, 6, 5,
            6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 4, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 5, 6, 6, 2, 6,
            5, 3, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 5, 2, 5, 7, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6,
            2, 6, 6                };

    public static int getCharWidth(char c)
    {
        if (c == 167) {
            return -1;
        } else {
            int charIndex = ChatAllowedCharacters.allowedCharacters.indexOf(c);
            if (charIndex + 32 > charWidth.length || charIndex < 0) {
                return 0;
            }
            return charWidth[charIndex + 32];
        }
    }

    public static int getStringWidth(String s)
    {
        if (s == null) {
            return 0;
        } else {
            int width = 0;
            boolean var3 = false;

            for (int charIndex = 0; charIndex < s.length(); ++charIndex) {
                char c = s.charAt(charIndex);
                int charWidth = getCharWidth(c);

                if (charWidth < 0 && charIndex < s.length() - 1) {
                    ++charIndex;
                    c = s.charAt(charIndex);

                    if (c != 108 && c != 76) {
                        if (c == 114 || c == 82) {
                            var3 = false;
                        }
                    } else {
                        var3 = true;
                    }

                    charWidth = getCharWidth(c);
                }

                width += charWidth;

                if (var3) {
                    ++width;
                }
            }

            return width;
        }
    }

    public static List<String> formatMessage(String message)
    {
        LinkedList<String> splitNotice = new LinkedList<String>();
        String[] splits = message.split(" ");
        String partial = "";
        int colour = 7;
        for (int i = 0; i < splits.length; i++) {
            String next = partial.length() == 0 ? splits[i] : partial + " "
                    + splits[i];
            if (getStringWidth(next) > 377) {
                splitNotice.add(colourPrefix(colour) + partial);
                for (int charPos = 0; charPos < partial.length(); charPos++) {
                    for (; partial.length() > charPos + 1
                            && partial.charAt(charPos) == '\247'; charPos++) {
                        char c = partial.toLowerCase().charAt(charPos + 1);
                        if (c == 'k') {
                            continue;
                        }
                        colour = "0123456789abcdef".indexOf(c);
                        if (colour < 0 || colour > 15) {
                            colour = 15;
                        }
                    }
                }

                partial = splits[i];

                continue;
            }
            partial = next;
        }
        splitNotice.add(colourPrefix(colour) + partial);

        return splitNotice;
    }

    public static String colourPrefix(int colour)
    {
        if (colour == -1) {
            return "\247f";
        }
        return "\247" + "0123456789abcdef".charAt(colour);
    }
}
