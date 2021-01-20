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
    
    private static final HashMap<Byte, String> CHARACTER_VALUES = new HashMap<Byte, String>() {{
        put((byte) 200, "🫂");  // this is technically -56, but we don't care.
        put((byte) 50, "💖");
        put((byte) 10, "✨");
        put((byte) 5, "🥺");
        put((byte) 1, ",");
        put((byte) 0, "❤️");
    }};
    
    private static final HashMap<Byte, String> BYTE_TO_EMOJI = new HashMap<Byte, String>() {{
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
            put(i, byteToEmoji(i));
        }
    }};
    
    private static final HashMap<String, Byte> EMOJI_TO_BYTE = new HashMap<String, Byte>() {{
        for (Map.Entry<Byte, String> entry: BYTE_TO_EMOJI.entrySet()) {
            put(trimEndMatches(entry.getValue(), "👉👈"), entry.getKey());
        }
    }};
    
    private static String byteToEmoji(byte value) {
        if (value == 0){
            return CHARACTER_VALUES.get((byte) 0);
        }
        
        StringBuilder buffer = new StringBuilder();
        short left = (short) (value & 0xFF);
        
        while (left > 0) {
            short subtractBy;
            if (left >= 200) {
                subtractBy = 200;
            } else if (left >= 50) {
                subtractBy = 50;
            } else if (left >= 10) {
                subtractBy = 10;
            } else if (left >= 5) {
                subtractBy = 5;
            } else {
                subtractBy = 1;
            }
            
            buffer.append(CHARACTER_VALUES.get((byte) subtractBy));
            left -= subtractBy;
        }
        
        buffer.append("👉👈");
        return buffer.toString();
    }
    
    private static String trimEndMatches(final String value, final String end) {
        String result = value;
        while (result.endsWith(end)) result = result.substring(0, result.lastIndexOf(end));
        return result;
    }
    
    private static String encodeByte(final byte value) {
        return BYTE_TO_EMOJI.get(value);
    }
    
    private static Byte decodeByte(final String input) throws TranslationError {
        Byte result = EMOJI_TO_BYTE.get(input);
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
            buf.append(encodeByte(b));
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
            buf[i] = decodeByte(arr[i]);
        }
        
        return new String(buf);
    }
}
