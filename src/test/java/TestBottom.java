import com.github.bottomSoftwareFoundation.bottom.Bottom;
import com.github.bottomSoftwareFoundation.bottom.TranslationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class TestBottom {
    public static class Encode {
        private static void testEncode(String input, String expected) {
            String result = Bottom.encode(input);
            assertEquals(expected, result);
        }
        
        @Test
        public void testEmpty() {
            testEncode("","");
        }
        
        @Test
        public void testTest() {
            testEncode("Test", "💖✨✨✨,,,,👉👈💖💖,👉👈💖💖✨🥺👉👈💖💖✨🥺,👉👈");
        }
        
        @Test
        public void testCharEncode() {
            testEncode("h", "💖💖,,,,👉👈");
        }
        
        @Test
        public void testUnicodeString() {
            testEncode("🥺", "🫂✨✨✨✨👉👈💖💖💖🥺,,,,👉👈💖💖💖✨🥺👉👈💖💖💖✨✨✨🥺,👉👈");
        }
        
        @Test
        public void testJP() {
            testEncode(
                    "がんばれ",
                    "🫂✨✨🥺,,👉👈💖💖✨✨🥺,,,,👉👈💖💖✨✨✨✨👉👈🫂✨✨🥺,,👉👈💖💖✨✨✨👉👈💖💖✨✨✨✨🥺,,👉👈🫂✨✨🥺,,👉👈💖💖✨✨🥺,,,,👉👈💖💖💖✨✨🥺,👉👈🫂✨✨🥺,,👉👈💖💖✨✨✨👉👈💖💖✨✨✨✨👉👈"
            );
        }
    }
    
    public static class Decode {
        private void testDecode(String input, String expected) {
            try {
                String result = Bottom.decode(input);
                assertEquals(expected, result);
            } catch (TranslationError translationError) {
                translationError.printStackTrace();
                fail(translationError.getMessage());
            }
        }
        
        @Test
        public void testEmpty() {
            testDecode("","");
        }
        
        @Test
        public void testTest() {
            testDecode("💖✨✨✨,,,,👉👈💖💖,👉👈💖💖✨🥺👉👈💖💖✨🥺,👉👈","Test");
        }
        
        @Test
        public void testBackwardsCompat() {
            testDecode("💖✨✨✨,,,,\u200B💖💖,\u200B💖💖✨🥺\u200B💖💖✨🥺,\u200B","Test");
            testDecode("💖✨✨✨,,,,👉👈💖💖,👉👈💖💖✨🥺👉👈💖💖✨🥺,👉👈", "Test");
        }
        
        @Test
        public void testCharDecode() {
            testDecode("💖💖,,,,", "h");
        }
        
        @Test
        public void testUnicodeString() {
            testDecode("🫂✨✨✨✨👉👈💖💖💖🥺,,,,👉👈💖💖💖✨🥺👉👈💖💖💖✨✨✨🥺,👉👈", "🥺");
        }
        
        @Test
        public void testJP() {
            testDecode(
                    "🫂✨✨🥺,,👉👈💖💖✨✨🥺,,,,👉👈💖💖✨✨✨✨👉👈🫂✨✨🥺,,👉👈💖💖✨✨✨👉👈💖💖✨✨✨✨🥺,,👉👈🫂✨✨🥺,,👉👈💖💖✨✨🥺,,,,👉👈💖💖💖✨✨🥺,👉👈🫂✨✨🥺,,👉👈💖💖✨✨✨👉👈💖💖✨✨✨✨👉👈",
                    "がんばれ"
            );
        }
    }
}
