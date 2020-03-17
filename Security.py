package lira;
import java.util.Scanner;

public class liraa {
	static String morseEncode(char x)  
    { 
       
        switch (x)  
        { 
            case 'a': 
                return ".-"; 
            case 'b': 
                return "-..."; 
            case 'c': 
                return "-.-."; 
            case 'd': 
                return "-.."; 
            case 'e': 
                return "."; 
            case 'f': 
                return "..-."; 
            case 'g': 
                return "--."; 
            case 'h': 
                return "...."; 
            case 'i': 
                return ".."; 
            case 'j': 
                return ".---"; 
            case 'k': 
                return "-.-"; 
            case 'l': 
                return ".-.."; 
            case 'm': 
                return "--"; 
            case 'n': 
                return "-."; 
            case 'o': 
                return "---"; 
            case 'p': 
                return ".--."; 
            case 'q': 
                return "--.-"; 
            case 'r': 
                return ".-."; 
            case 's': 
                return "..."; 
            case 't': 
                return "-"; 
            case 'u': 
                return "..-"; 
            case 'v': 
                return "...-"; 
            case 'w': 
                return ".--"; 
            case 'x': 
                return "-..-"; 
            case 'y': 
                return "-.--"; 
            // for space 
            case 'z': 
                return "--.."; 
        } 
        return ""; 
    } 
	static void morseCode(String s)  
    { 
        // character by character print  
        // Morse code 
        for (int i = 0;i<s.length(); i++) 
            System.out.print(morseEncode(s.charAt(i))); 
            System.out.println(); 
    } 
	
	
	public static void main(String[] args) {
		System.out.print("$ds morse-code encode ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine().toLowerCase();
		morseCode(input);
	}

}
