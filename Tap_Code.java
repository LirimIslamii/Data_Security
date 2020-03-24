import java.util.HashMap;
import java.util.Map;

public class Tap_Code {

    public static Map<String, Character> ENCODE = new HashMap<>();

    static {
        //ENCODE HashMap
        ENCODE.put(". .", 'a');
        ENCODE.put(". ..", 'b');
        ENCODE.put(". ...", 'c');
        ENCODE.put(". ....", 'd');
        ENCODE.put(". .....", 'e');
        ENCODE.put(".. .", 'f');
        ENCODE.put(".. ..", 'g');
        ENCODE.put(".. ...", 'h');
        ENCODE.put(".. ....", 'i');
        ENCODE.put(".. .....", 'j');
        ENCODE.put("... .", 'l');
        ENCODE.put("... ..", 'm');
        ENCODE.put("... ...", 'n');
        ENCODE.put("... ....", 'o');
        ENCODE.put("... .....", 'p');
        ENCODE.put(".... .", 'q');
        ENCODE.put(".... ..", 'r');
        ENCODE.put(".... ...", 's');
        ENCODE.put(".... ....", 't');
        ENCODE.put(".... .....", 'u');
        ENCODE.put("..... .", 'v');
        ENCODE.put("..... ..", 'w');
        ENCODE.put("..... ...", 'x');
        ENCODE.put("..... ....", 'y');
        ENCODE.put("..... .....", 'z');
        ENCODE.put(" ", ' ');
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
    public static String decode(String eMorse) {
        StringBuilder encoded = new StringBuilder();
        String[] words = eMorse.trim().split(" ",1);
        for (String word : words) {
            String[] letters = word.split("  ");
            for (String letter : letters) {
                encoded.append(ENCODE.get(letter));
            }
        }
        System.out.println(encoded);
        return encoded.toString().toUpperCase();
    }
}
