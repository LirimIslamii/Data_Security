import javax.crypto.Cipher;  

public class Main {
        static Cipher cipher;  
    public static void main(String[] args)throws Exception {
        Morse_Code M = new Morse_Code();
        Tap_Code T = new Tap_Code();
        Four_Code F = new Four_Code();
        Metodat K = new Metodat();

        K.writeToFile("RSA/publicKey", K.getPublicKey().getEncoded());
        K.writeToFile("RSA/privateKey", K.getPrivateKey().getEncoded());

        // Faza e pare

        if("morse-code".equals(args[0]) && args.length == 3){
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
        else if("tap-code".equals(args[0]) && args.length == 3){
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
        else if("four-square".equals(args[0]) && args.length == 5){
                if("encrypt".equals(args[1])){
                    if(args[2].matches("^[a-zA-Z ]+"))
                        System.out.println(F.encrypt(args[2],args[3],args[4]).toLowerCase());
                }
                else if("decrypt".equals(args[1]))
                        System.out.println(F.decrypt(args[2],args[3],args[4]).toLowerCase());
        }           
        
        // Faza e dyte
        
        else if(args[0].equals("create-user") && args.length == 2) {
            if(args[1].matches("^[a-zA-Z0-9._]+"))
                K.CreateUser(args[1]);
            else 
                System.out.println("Keni shenuar gabim. Lejohen vetem shkronjat, numrat dhe . _");
        }
        else if(args[0].equals("delete-user") && args.length == 2) 
                K.Delete(args[1]);
        else if(args[0].equals("export-key") && args.length == 3) 
                K.Export(args[1], args[2]);
        else if(args[0].equals("export-key") && args.length == 4) 
                K.Export(args[1], args[2],args[3]);
        else if(args[0].equals("import-key") && args.length == 3){
            if(args[2].startsWith("https") || args[2].startsWith("http"))
                K.Pastebin(args[1], args[2]);
            else
                K.Import(args[1],args[2]);
        }
        else if(args[0].equals("write-message") && args.length == 5) 
                K.Write(args[1], args[2],args[3],args[4]);
        else if(args[0].equals("write-message") && args.length == 3) 
                K.Write(args[1], args[2]);
        else if(args[0].equals("write-message") && args.length == 4) 
                K.Write(args[1], args[2],args[3]);
        else if(args[0].equals("read-message") && args.length == 2)
            K.Read_Message(args[1]);
        else if(args[0].equals("login") && args.length == 2)
            K.Login(args[1]);
        else if(args[0].equals("status") && args.length == 2)
            K.Status(args[1]);
        else {
            System.out.println("\n\t\tProgrami pranon deri ne 5 argumente!\n");
            System.out.println("\t\t\tPerdorimi i Programit\n\n\t1.Per Kodin Morse shtyp -> morse-code encode | decode <text>\n" 
              + "\t~Per Beep shtyp -> morse-code --audio <text>\n"+ "\t2.Per Kodin Tap shtyp -> tap-code encode | decode <text>\n" 
                                      + "\t3.Per Kodin FourSquare shtyp -> four-square encrypt | decrypt <key1> <key2> <text>\n"
                                      + "\t4.Komanda create-user <name>\n"
                                      + "\t5.Komanda delete-user <name>\n"
                                      + "\t6.Komanda export-key <public | private> <name> [file]\n"
                                      + "\t7.Komanda import-key <name> <path>\n"
                                      + "\t8.Komanda write-message <name> <message> [file]\n"
                                      + "\t9.Komanda read-message <encrypted-message>\n");
                System.exit(0);
        }
    }
}



