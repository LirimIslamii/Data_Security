public class Kryesori {

    public static void main(String[] args) {
        String plaintextMethod = args[0];
        String EncodeOrDecode = args[1];
        String plaintextEncodeOrDecode = args[2];
        
        String morseCode = "morse-code encode";
        String morseCode1 = "morse-code decode";
        if(plaintextMethod.equals(morseCode.substring(0,10)) && EncodeOrDecode.equals(morseCode.substring(11,17))) {
            encode(plaintextEncodeOrDecode);
        }
        else if(plaintextMethod.equals(morseCode1.substring(0,10)) && EncodeOrDecode.equals(morseCode1.substring(11,17))) {
            decode(plaintextEncodeOrDecode);
        }
        else {
            System.out.print("GABIM");
        }
        
    }

// Kodi i Morse-Code Decode
public static char morseDecode(String x)  
{ 
   
    switch (x.substring(0,5))  
    { 
        case ".-   ": 
            return 'a'; 
        case "-... ": 
            return 'b'; 
        case "-.-. ": 
            return 'c'; 
        case "-..  ": 
            return 'd'; 
        case ".    ": 
            return 'e'; 
        case "..-. ": 
            return 'f'; 
        case "--.  ": 
            return 'g'; 
        case ".... ": 
            return 'h'; 
        case "..   ": 
            return 'i'; 
        case ".--- ": 
            return 'j'; 
        case "-.-  ": 
            return 'k'; 
        case ".-.. ": 
            return 'l'; 
        case "--   ": 
            return 'm'; 
        case "-.   ": 
            return 'n'; 
        case "---  ": 
            return 'o'; 
        case ".--. ": 
            return 'p'; 
        case "--.- ": 
            return 'q'; 
        case ".-.  ": 
            return 'r'; 
        case "...  ": 
            return 's'; 
        case "-    ": 
            return 't'; 
        case "..-  ": 
            return 'u'; 
        case "...- ": 
            return 'v'; 
        case ".--  ": 
            return 'w'; 
        case "-..- ": 
            return 'x'; 
        case "-.-- ": 
            return 'y'; 
        // for space 
        case "--.. ": 
            return 'z'; 
        case " ":
            return ' ';
    } 
    return ' '; 
} 
public static void decode(String e)  
{ 
    // character by character print  
    // Morse code 
    for (int i = 0;i < e.length(); i = i++) 
        System.out.print(morseDecode(e.substring(i,i + 5))); 
        System.out.println(); 
}

//Kodi i Morse-Code Encode
public static String morseEncode(char x)  
{ 

switch (x)  
{ 
case 'a': 
    return ".- "; 
case 'b': 
    return "-... "; 
case 'c': 
    return "-.-. "; 
case 'd': 
    return "-.. "; 
case 'e': 
    return ". "; 
case 'f': 
    return "..-. "; 
case 'g': 
    return "--. "; 
case 'h': 
    return ".... "; 
case 'i': 
    return ".. "; 
case 'j': 
    return ".--- "; 
case 'k': 
    return "-.- "; 
case 'l': 
    return ".-.. "; 
case 'm': 
    return "-- "; 
case 'n': 
    return "-. "; 
case 'o': 
    return "--- "; 
case 'p': 
    return ".--. "; 
case 'q': 
    return "--.- "; 
case 'r': 
    return ".-. "; 
case 's': 
    return "... "; 
case 't': 
    return "- "; 
case 'u': 
    return "..- "; 
case 'v': 
    return "...- "; 
case 'w': 
    return ".-- "; 
case 'x': 
    return "-..- "; 
case 'y': 
    return "-.-- "; 
// for space 
case 'z': 
    return "--.. "; 
} 
return " "; 
} 
public static void encode(String e)  
{ 
// character by character print  
// Morse code 
for (int i = 0;i<e.length(); i++) 
System.out.print(morseEncode(e.charAt(i))); 
System.out.println(); 
}

}
