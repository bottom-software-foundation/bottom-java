package com.github.sebbylaw.bottom;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * The Bottom class can not be subclassed or instantiated. It is merely a container for static methods.
 */
public final class Bottom {
    // Bottom class should not be instantiated
    private Bottom() {}
    
    /*
     * We use Short instead of Byte here because Java sucks and won't let me use unsigned bytes
     */
    private static final HashMap<Short, String> CHARACTER_VALUES = new HashMap<Short, String>() {{
        put((short) 200, "🫂");
        put((short) 50, "💖");
        put((short) 10, "✨");
        put((short) 5, "🥺");
        put((short) 1, ",");
        put((short) 0, "❤️");
    }};
    
    private static final HashMap<Short, String> BYTE_TO_EMOJI = new HashMap<Short, String>() {{
        for (short i = 0; i <= 255; i++) {
            put(i, byteToEmoji(i));
        }
    }};
    
    private static final HashMap<String, Short> EMOJI_TO_BYTE = new HashMap<String, Short>() {{
        for (Map.Entry<Short, String> entry: BYTE_TO_EMOJI.entrySet()) {
            put(trimEndMatches(entry.getValue(), "👉👈"), entry.getKey());
        }
    }};
    
    private static String byteToEmoji(short value) {
        StringBuilder buffer = new StringBuilder();
        
        if (value == 0){
            return CHARACTER_VALUES.get((short) 0);
        }
        
        while (value > 0) {
            short subtractBy;
            if (value >= 200) {
                subtractBy = 200;
            } else if (value >= 50) {
                subtractBy = 50;
            } else if (value >= 10) {
                subtractBy = 10;
            } else if (value >= 5) {
                subtractBy = 5;
            } else {
                subtractBy = 1;
            }
            
            buffer.append(CHARACTER_VALUES.get(subtractBy));
            value -= subtractBy;
        }
        
        buffer.append("👉👈");
        return buffer.toString();
    }
    
    private static String trimEndMatches(final String value, final String end) {
        String result = value;
        while (result.endsWith(end)) result = result.substring(0, result.lastIndexOf(end));
        return result;
    }
    
    private static String encodeByte(final short value) {
        return BYTE_TO_EMOJI.get(value);
    }
    
    private static Short decodeByte(final String input) throws TranslationError {
        Short result = EMOJI_TO_BYTE.get(input);
        if (result == null) throw new TranslationError("Could not decode character " + input, input);
        return result;
    }
    
    /**
     * Encodes a UTF-8 String into bottom.
     * @param input String to be encoded into bottom.
     * @return String encoded in bottom (UTF-8).
     */
    public static String encode(final String input) {
        StringBuilder buf = new StringBuilder();
        
        for (byte b: input.getBytes(StandardCharsets.UTF_8)) {
            buf.append(encodeByte((short) (b & 0xFF)));
        }
        
        return buf.toString();
    }
    
    /**
     * Decodes bottom into a UTF-8 String.
     * @param input bottom to be decoded.
     * @return The decoded String (UTF-8).
     * @throws TranslationError an invalid bottom string was provided.
     */
    public static String decode(final String input) throws TranslationError {
        if (input.isEmpty()) return input;
        
        String[] arr = input.contains("\u200B")
                // Older versions used a ZWSP as a character separator, instead of `👉👈`.
                ? trimEndMatches(input,"\u200B").split("\u200B")
                : trimEndMatches(input, "👉👈").split("👉👈");
        
        byte[] buf = new byte[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            buf[i] = decodeByte(arr[i]).byteValue();
        }
        
        return new String(buf);
    }
}
