import java.util.ArrayList;
import java.util.List;

public class Four_Code {
		private static final char[] ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
												
		
	    private static final char[][] ALPHABET_SQUARE = new char[5][5];

	    static {
	        int x = 0, y = 0;
	        for (char c : ALPHABET) {
	            ALPHABET_SQUARE[x][y] = c;
	            x++;
	            if (x == 5) {
	                x = 0;
	                y++;
	            }
	        }
	    }

	    private static String clean(String input) {
	        input = input.trim().replace("J", "j").replace("I", "i").toUpperCase();
	        StringBuilder clean = new StringBuilder();
	        for (char c : input.toCharArray()) {
	            if (Character.getType(c) == Character.UPPERCASE_LETTER) {
	                clean.append(c);
	            }
	        }
	        return clean.toString();
	    }

	    private static char[][] generateKeyTable(String keyword) {
	        keyword = clean(keyword);
	        char[][] keyTable = new char[5][5];
	        List<Character> used = new ArrayList<Character>();
	        int x = 0, y = 0;
	        for (char c : keyword.toCharArray()) {
	            if (!used.contains(c)) {
	                keyTable[x][y] = c;
	                used.add(c);
	                x++;
	                if (x == 5) {
	                    x = 0;
	                    y++;
	                    if (y == 5) {
	                        return keyTable;
	                    }
	                }
	            }
	        }
	        for (char c : ALPHABET) {
	            if (!used.contains(c)) {
	                keyTable[x][y] = c;
	                x++;
	                if (x == 5) {
	                    x = 0;
	                    y++;
	                    if (y == 5) {
	                        return keyTable;
	                    }
	                }
	            }
	        }
	        return keyTable;
	    }

	    private static String[] split(String plaintext) {
	        if (plaintext.length() % 2 != 0) {
	            plaintext = plaintext + "X";      // "X" Padding Character!
	        }
	        String[] pairs = new String[plaintext.length() / 2];
	        int count = 0;
	        for (int i = 0; i < (plaintext.length() / 2); i++) {
	            pairs[i] = plaintext.substring(count, count + 2);
	            count = count + 2;
	        }
	        return pairs;
	    }

	    public static String encrypt(String keyword1, String keyword2,String plaintext) {
	        plaintext = clean(plaintext);
	        String[] pairs = split(plaintext);
	        char[][] keytable1 = generateKeyTable(keyword1);
	        char[][] keytable2 = generateKeyTable(keyword2);
	        char first, second;
	        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
	        StringBuilder ciphertext = new StringBuilder();
	        for (String s : pairs) {
	            first = s.charAt(0);
	            second = s.charAt(1);
	            for (int y = 0; y < 5; y++) {
	                for (int x = 0; x < 5; x++) {
	                    if (ALPHABET_SQUARE[x][y] == first) {
	                        xFirst = x;
	                        yFirst = y;
	                    } else if (ALPHABET_SQUARE[x][y] == second) {
	                        xSecond = x;
	                        ySecond = y;
	                    }
	                }
	            }
	            ciphertext.append(keytable1[xSecond][yFirst]).append(keytable2[xFirst][ySecond]);
	        }
	        return ciphertext.toString();
	    }

	    public static String decrypt(String keyword1, String keyword2,String ciphertext) {
	    	ciphertext = clean(ciphertext);
	        String[] pairs = split(ciphertext);
	        char[][] keytable1 = generateKeyTable(keyword1);
	        char[][] keytable2 = generateKeyTable(keyword2);
	        char first, second;
	        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
	        StringBuilder plaintext = new StringBuilder();
	        for (String s : pairs) {
	            first = s.charAt(0);
	            second = s.charAt(1);
	            for (int y = 0; y < 5; y++) {
	                for (int x = 0; x < 5; x++) {
	                    if (keytable1[x][y] == first) {
	                        xFirst = x;
	                        yFirst = y;
	                    } else if (keytable2[x][y] == second) {
	                        xSecond = x;
	                        ySecond = y;
	                    }
	                }
	            }
	            plaintext.append(ALPHABET_SQUARE[xSecond][yFirst]).append(ALPHABET_SQUARE[xFirst][ySecond]);
	        }
	        return plaintext.toString();
	    }

}
