import javax.sound.sampled.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {
  public static void main(String[] args)throws IOException, LineUnavailableException, InterruptedException, 
                            ArrayIndexOutOfBoundsException, NoSuchAlgorithmException, InvalidKeyException, 
                            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Morse_Code M = new Morse_Code();
        Tap_Code T = new Tap_Code();
        Four_Code F = new Four_Code();
        Metodat K = new Metodat();
        K.writeToFile("RSA/publicKey", K.getPublicKey().getEncoded());
        K.writeToFile("RSA/privateKey", K.getPrivateKey().getEncoded());
        
        if(args.length == 0 || args.length > 5){

            System.out.println("\n\t\tProgrami pranon deri ne 5 argumente!\n");
            System.out.println("\t\t\tPerdorimi i Programit\n\n1.Per Kodin Morse shtyp -> morse-code encode | decode <text>\n" 
              + "~Per Beep shtyp -> morse-code --audio <text>\n"+ "2.Per Kodin Tap shtyp -> tap-code encode | decode <text>\n" 
                                      + "3.Per Kodin FourSquare shtyp -> four-square encrypt | decrypt <key1> <key2> <text>");
                System.exit(0);
            }
        if("morse-code".equals(args[0])){
            if("encode".equals(args[1])){
                if(args[2].matches("^[a-zA-Z0-9 ]+"))
                        System.out.println(M.encode(args[2]));
                else
                        System.out.println("\n\t\tTeksti duhet te përmbajë vetëm numra dhe shkronja!\n");
                }
            else if("decode".equals(args[1]))
                        M.decode(args[2]);
            else if("--audio".equals(args[1]))
                    if(args[2].matches("^[a-zA-Z0-9 ]+"))
                        M.Beep(args[2]);
                    else
                        System.out.println("\n\t\tTeksti duhet te përmbajë vetëm numra dhe shkronja!\n");
            }
        if("tap-code".equals(args[0])){
            if("encode".equals(args[1])){
                if(args[2].matches("^[a-zA-Z ]+"))
                        T.encode(args[2]);
                else 
                    System.out.println("\n\t\tTeksti duhet të përmbajë vetëm shkronja!\n");
            }
            else if("decode".equals(args[1]))
                if(args[2].matches("^[a-zA-Z ]+")){
                    System.out.println("\n\t\tTeksti duhet te permbaje vetem . dhe /\n");
                }
                else
                    T.decode(args[2]);
        }
        if("four-square".equals(args[0])){
                if("encrypt".equals(args[1])){
                    if(args[2].matches("^[a-zA-Z ]+"))
                        System.out.println(F.encrypt(args[2],args[3],args[4]).toLowerCase());
                }
                else if("decrypt".equals(args[1]))
                        System.out.println(F.decrypt(args[2],args[3],args[4]).toLowerCase());
        }           
        
        // Faza e dyte
        
        if(args[0].equals("create-user")) {
            if(args[1].matches("^[a-zA-Z0-9._]+")) {
                K.CreateUser(args[1]);
            }
            else {
                System.out.println("Keni shenuar gabim. Lejohen vetem shkronjat,numrat dhe . _");
            }
        }
        else if(args[0].equals("delete-user")) {
            K.Delete(args[1]);
        }
        else if(args[0].equals("export-key")) {
            if(args.length == 3) {
                K.Export(args[1], args[2]);
            }
            else {
                K.Export(args[1], args[2],args[3]);
            }
        }
        else if(args[0].equals("import-key")) {
            K.Import(args[1], args[2]);
        }
      
        else if(args[0].equals("write-message")) {
            if(args.length == 3) {
                K.Write(args[1], args[2]);
            }
            else {
                K.Write(args[1], args[2],args[3]);
            }
        }
        else if(args[0].equals("read-message")) {
            K.Read_Message(args[1]);
        }
        else {
            System.exit(0);
        }
    }
}
