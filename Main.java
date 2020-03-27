import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import java.util.List;

public class Main {
            // MAIN
  public static void main(String[] args)throws  ArrayIndexOutOfBoundsException, IOException, LineUnavailableException, InterruptedException {
        Morse_Code M = new Morse_Code();
        Tap_Code T = new Tap_Code();
        Four_Code F = new Four_Code();
   
   	    if(args.length == 0 || args.length > 5 || args.length < 3){

			System.out.println("\n\t\tProgrami pranon deri ne 5 argumente!\n");
            System.out.println("\t\t\tPerdorimi i Programit\n\n1.Per Kodin Morse shtyp -> morse-code encode <text> ose decode <text>\n" + "~Per Beep shtyp -> morse-code --audio <text>\n"+ "2.Per Kodin Tap shtyp -> tap-code encode <text> ose decode <text>" + "3.Per Kodin FourSquare shtyp -> four-square encrypt <key1> <key2> <text> ose \ndecrypt <key1> <key2> <text>");
				System.exit(0);
			}
			String text = args[2];
		if("morse-code".equals(args[0])){
			if("encode".equals(args[1])){
				if(text.matches("^[a-zA-Z0-9 ]+"))
						System.out.println(M.encode(text));
				else
						System.out.println("\n\t\tTeksti duhet te përmbajë vetëm numra dhe shkronja!\n");
				}
			else if("decode".equals(args[1]))
						M.decode(text);
			else if("--audio".equals(args[1]))
					if(text.matches("^[a-zA-Z0-9 ]+"))
						M.Beep(text);
					else
						System.out.println("\n\t\tTeksti duhet te përmbajë vetëm numra dhe shkronja!\n");
			}
		if("tap-code".equals(args[0])){
			if("encode".equals(args[1])){
				if(text.matches("^[a-zA-Z ]+"))
						T.encode(text);
				else 
					System.out.println("\n\t\tTeksti duhet të përmbajë vetëm shkronja!\n");
			}
			else if("decode".equals(args[1]))
				if(text.matches("^[a-zA-Z ]+")){
					System.out.println("\n\t\tTeksti duhet te permbaje vetem . dhe /\n");
				}
				else
					T.decode(text);
		}
		if("four-square".equals(args[0])){
				if("encrypt".equals(args[1])){
					if(text.matches("^[a-zA-Z ]+"))
						System.out.println(F.encrypt(text,args[3],args[4]).toLowerCase());
				}
				else if("decrypt".equals(args[1]))
						System.out.println(F.decrypt(text,args[3],args[4]).toLowerCase());
		}			
    }
}




