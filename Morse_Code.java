import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class Morse_Code {
    
        private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;

static String[] LETTER = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"," "};
static String[] MORSE = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----","/"}; 

 public static HashMap<String, String> LETTER_TO_MORSE = new HashMap<>();
    public static HashMap<String, String> MORSE_TO_LETTER= new HashMap<>();

    static {
        for (int i = 0; i < LETTER.length  &&  i < MORSE.length; i++) {
            LETTER_TO_MORSE.put(LETTER[i], MORSE[i]);
            MORSE_TO_LETTER.put(MORSE[i], LETTER[i]);
        }
    }
public static String decode(String dCode) {
        StringBuilder builder = new StringBuilder();
        String[] words = dCode.trim().split("   ");

        for (String word : words) {
            for (String letter : word.split(" ")) {
                String alpha = MORSE_TO_LETTER.get(letter);
                builder.append(alpha);
            }

            builder.append(" ");
        }
        System.out.println(builder);
        return builder.toString().toUpperCase();
    }

public static String encode(String eCode) {
        StringBuilder builder = new StringBuilder();
        String[] words = eCode.trim().split("");

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String morse = LETTER_TO_MORSE.get(word.substring(i, i + 1).toLowerCase());
                builder.append(morse).append("");
            }

            builder.append(" ");
        }
        return builder.toString();
    }
public static void Beep(String text) throws IOException, LineUnavailableException, InterruptedException {
        String audio = encode(text);
        boolean sound = true;
        for (char note : audio.toCharArray()) {
            System.out.print(note == ' ' ? " " : note);
            if (sound) {
                try (SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(7000F, 8, 1, true, false))) {
                    sdl.open(sdl.getFormat());
                    sdl.start();
                    for (int i = 0; i < (note == '.' ? DOT : DASH) * 8; i++) {
                        sdl.write(new byte[]{(byte) (Math.sin(i / (8000F / FREQ) * 2.0 * Math.PI) * 127.0)}, 0, 1);
                    }
                    sdl.drain();
                }
            }
            Thread.sleep(DOT / 5);
        }
    }

}
