import java.util.HashMap;
import java.util.Map;

public class Tap_Code {

    public static Map<String, Character> DECODE = new HashMap<>();

    static {
        //ENCODE HashMap
        DECODE.put(". .", 'a');
        DECODE.put(". ..", 'b');
        DECODE.put(". ...", 'c');
        DECODE.put(". ....", 'd');
        DECODE.put(". .....", 'e');
        DECODE.put(".. .", 'f');
        DECODE.put(".. ..", 'g');
        DECODE.put(".. ...", 'h');
        DECODE.put(".. ....", 'i');
        DECODE.put(".. .....", 'j');
        DECODE.put("... .", 'l');
        DECODE.put("... ..", 'm');
        DECODE.put("... ...", 'n');
        DECODE.put("... ....", 'o');
        DECODE.put("... .....", 'p');
        DECODE.put(".... .", 'q');
        DECODE.put(".... ..", 'r');
        DECODE.put(".... ...", 's');
        DECODE.put(".... ....", 't');
        DECODE.put(".... .....", 'u');
        DECODE.put("..... .", 'v');
        DECODE.put("..... ..", 'w');
        DECODE.put("..... ...", 'x');
        DECODE.put("..... ....", 'y');
        DECODE.put("..... .....", 'z');
        DECODE.put(" ", ' ');
    }

 public static String TapCodeEncode(char x)  
		{ 

		switch (x)  
		{ 
		case 'a': 
		    return ". .  "; 
		case 'b': 
		    return ". ..  "; 
		case 'c': 
		    return ". ...  "; 
		case 'd': 
		    return ". ....  "; 
		case 'e': 
		    return ". .....  "; 
		case 'f': 
		    return ".. .  "; 
		case 'g': 
		    return ".. ..  "; 
		case 'h': 
		    return ".. ...  "; 
		case 'i': 
		    return ".. ....  "; 
		case 'j': 
		    return ".. .....  "; 
		case 'l': 
		    return "... .  "; 
		case 'm': 
		    return "... ..  "; 
		case 'n': 
		    return "... ...  "; 
		case 'o': 
		    return "... ....  "; 
		case 'p': 
		    return "... .....  "; 
		case 'q': 
		    return ".... .  "; 
		case 'r': 
		    return ".... ..  "; 
		case 's': 
		    return ".... ...  "; 
		case 't': 
		    return ".... ....  "; 
		case 'u': 
		    return ".... .....  "; 
		case 'v': 
		    return "..... .  "; 
		case 'w': 
		    return "..... ..  "; 
		case 'x': 
		    return "..... ...  "; 
		case 'y': 
		    return "..... ....  "; 
		// for space 
		case 'z': 
		    return "..... .....  "; 
		} 
		return ""; 
		} 
		
		public static void encode(String e)  
		{ 
			for (int i = 0;i<e.length(); i++) 
				System.out.print(TapCodeEncode(e.charAt(i))); 
				System.out.println(); 
		}
    public static String decode(String tap) {
        StringBuilder decoded = new StringBuilder();
        String[] words = tap.trim().split(" ",1);
        for (String word : words) {
            String[] letters = word.split("  ");
            for (String letter : letters) {
                decoded.append(DECODE.get(letter));
            }
        }
        System.out.println(decoded);
        return decoded.toString().toUpperCase();
    }
}
