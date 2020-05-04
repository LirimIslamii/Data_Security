import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Metodat {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static String Path = "C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\keys\\\\";

    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    KeyPair keyPair = keyPairGen.genKeyPair();
    RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
    RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();

    BigInteger n = privKey.getModulus();
    BigInteger e = privKey.getPublicExponent();
    BigInteger p = privKey.getPrimeP();
    BigInteger q = privKey.getPrimeQ();
    BigInteger dp = privKey.getPrimeExponentP();
    BigInteger dq = privKey.getPrimeExponentQ();
    BigInteger inverseQ = privKey.getCrtCoefficient();
    BigInteger d = privKey.getPrivateExponent();
        
	String n1 = n.toString();
	String e1 = e.toString();
	String p1 = p.toString();
	String q1 = q.toString();
	String dp1 = dp.toString();
	String dq1 = dq.toString();
	String inverseQ1 = inverseQ.toString();
	String d1 = d.toString();

    public Metodat() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
    }   

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
   
    byte[] encryptData(String data) throws IOException {
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedData = cipher.doFinal(dataToEncrypt);	
		} catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
											IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

		public void CreateUser(String name) throws IOException {
			Boolean existsPrivat = FileExists(name, Path, ".xml");
			Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(!(existsPrivat && existsPublik)){
		    	Person celsiPrivat = new Person(n1,e1,p1,q1,dp1,dq1,inverseQ1,d1);
		        FileOutputStream ruajCelsinPrivat = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\keys\\\\"
		        																	+ name +".xml"));
				XMLEncoder enkoderiCelsitPrivat = new XMLEncoder(ruajCelsinPrivat);
				enkoderiCelsitPrivat.writeObject(celsiPrivat);
				enkoderiCelsitPrivat.close();
				ruajCelsinPrivat.close();
				
		        Person celsiPublik = new Person(n1,e1);
				FileOutputStream ruajCelsinPublik = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\keys\\\\"
																		+ name +".pub.xml"));
				XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
				enkoderiCelsitPublik.writeObject(celsiPublik);
				enkoderiCelsitPublik.close();
				ruajCelsinPublik.close();
				
				System.out.println("Eshte krijuar celesi privat 'keys/" + name + ".xml'");
				System.out.println("Eshte krijuar celesi publik 'keys/" + name + ".pub.xml'");
			}
			else{
				System.out.print("Gabim: Celesi '" + name + "' ekziston paraprakisht.");
			}
		}
		
		public void Delete(String name) {
				Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
		    	if(existsPrivat && existsPublik) {
		    		File celsiPublik = new File(Path + name + ".pub.xml");
		    		File celsiPrivat = new File(Path + name + ".xml");
		    		celsiPublik.delete();
		    		celsiPrivat.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPublik) {
		    		File celsiPublik = new File("./" + name + ".pub.xml");
		    		celsiPublik.delete();
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPrivat) {
		    		File celsiPrivat = new File("./" + name + ".xml");
		    		celsiPrivat.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    	}
		    	else {
		    		System.out.println("Gabim: Celesi '" + name + "' nuk ekziston.");
		    	}	
		    }
		
		public void Export(String publicOrPrivat, String name) throws IOException {	
		    	Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
				if(existsPublik && publicOrPrivat.equals("public") && !name.endsWith(".pub.xml")) {
		    		System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + pubKey.getModulus() + "</Modulus>");
		    		System.out.println("    <Exponent>" + pubKey.getPublicExponent() + "</Exponent>");
		    		System.out.println("</RSAKeyValue>");
		    	}
				else if(!existsPublik && publicOrPrivat.equals("public")) {
		    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
				}
				else if(existsPrivat && publicOrPrivat.equals("private") && !publicOrPrivat.endsWith(".xml")) {
					System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + privKey.getModulus() + "</Modulus>");
		    		System.out.println("    <Exponent>" + privKey.getPublicExponent() + "</Exponent>");
		    		System.out.println("    <P>" + privKey.getPrimeP() + "</P>");
		    		System.out.println("    <Q>" + privKey.getPrimeQ() + "</Q>");
		    		System.out.println("    <DP>" + privKey.getPrimeExponentP() + "</DP>");
		    		System.out.println("    <DQ>" + privKey.getPrimeExponentQ() + "</DQ>");
		    		System.out.println("    <InverseQ>" + privKey.getCrtCoefficient() + "</InverseQ>");
		    		System.out.println("    <D>" + privKey.getPrivateExponent() + "</D>");
		    		System.out.println("</RSAKeyValue>");
				}
				else if(!existsPrivat && publicOrPrivat.equals("private")) {
		    		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
				}
		    	else {
		    		System.out.println("Keni shenuar gabim.\nSintaksa: ds export-key <public|private> <name>\n"
	    				+ "Argumenti <public|private> e përcakton llojin e çelësit që eksportohet. \n"
	    				+ "Argumenti <name> e përcakton çelësin e cilit shfrytëzues të eksportohet. \n");
		    	}
		    }
		
		public void Export(String publicOrPrivat, String name, String file) throws IOException {	
			Boolean existsPrivat = FileExists(name, Path, ".xml");
			Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(existsPublik && publicOrPrivat.equals("public") && file.endsWith(".xml")) {
				Person celsiPublik = new Person(n1,e1);
				FileOutputStream ruajCelsinPublik = new FileOutputStream(new File(Path + file));
				XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
				enkoderiCelsitPublik.writeObject(celsiPublik);
				enkoderiCelsitPublik.close();
				ruajCelsinPublik.close();
				System.out.println("Celesi publik u ruajt ne fajllin '" + file + "'.");
			}
			else if(!existsPublik && publicOrPrivat.equals("public")) {
	    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
			}
			else if(existsPrivat && publicOrPrivat.equals("private") && file.endsWith(".xml")) {
				Person celsiPrivat = new Person(n1,e1);
				FileOutputStream ruajCelsinPrivat = new FileOutputStream(new File(Path + file));
				XMLEncoder enkoderiCelsitPrivat = new XMLEncoder(ruajCelsinPrivat);
				enkoderiCelsitPrivat.writeObject(celsiPrivat);
				enkoderiCelsitPrivat.close();
				ruajCelsinPrivat.close();
				System.out.println("Celesi privat u ruajt ne fajllin '" + file + "'.");
			}
			else if(!existsPrivat && publicOrPrivat.equals("private")) {
	    		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
			}
	    	else {
	    		System.out.println("Keni shenuar gabim.\nSintaksa: ds export-key <public|private> <name> [file]\n"
	    				+ "Argumenti <public|private> e përcakton llojin e çelësit që eksportohet. \n"
	    				+ "Argumenti <name> e përcakton çelësin e cilit shfrytëzues të eksportohet. \n"
	    				+ "Argumenti opsional [file] e përcakton shtegun e fajllit se ku do të ruhet çelësi i eksportuar.");
	    	}
	    }
		
		public void Import(String argumenti1, String argumenti2) throws IOException {
		    	Boolean existsPu = FileExists(argumenti2, "./", "");
				Boolean exist = FileExists(argumenti2, "./", "");
		    	if(!existsPu && argumenti2.endsWith(".pub.xml")) {
			        Person w2 = new Person(n1,e1);
					FileOutputStream pub = new FileOutputStream(new File("./"+ argumenti1 + ".pub.xml"));
					XMLEncoder encoder2 = new XMLEncoder(pub);
					encoder2.writeObject(w2);
					encoder2.close();
					pub.close();
					System.out.println("Celesi publik u ruajt ne fajllin " + argumenti1 + ".pub.xml");
		    	}
		    	else if(!exist && argumenti2.endsWith(".xml")) {
			        Person w2 = new Person(n1,e1);
					FileOutputStream pub = new FileOutputStream(new File("./"+ argumenti1 + ".xml"));
					XMLEncoder encoder2 = new XMLEncoder(pub);
					encoder2.writeObject(w2);
					encoder2.close();
					pub.close();					
					System.out.println("Celsi privat u ruajt ne fajllin 'keys/" + argumenti1 + ".xml'");
		    	} 
				else {
					System.out.println("Gabim: Fajlli i dhene nuk eshte valid.");
				}
		    }

	    public void Write(String name,String message,String file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	    															InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	String enkodimiBaze64UTF8 = Base64.getEncoder()
                    .encodeToString(name.getBytes(StandardCharsets.UTF_8.toString()));
    	
	    	SecureRandom sr = new SecureRandom(); //Krijon nje random te sigurt
	    	byte [] bajtaRandomIV = new byte[8];  //Krijon nje array prej 8 bajt
	    	sr.nextBytes(bajtaRandomIV); 		  //Krijon bajta random qe perdoren prej iv
	    	String iv = bajtaRandomIV.toString();
			String enkodimiBase64 = Base64.getEncoder()
                    .encodeToString(iv.getBytes(StandardCharsets.UTF_8.toString()));
	    	
	    	Metodat rsaObj = new Metodat();
    		byte [] bajtaRandomKEY = new byte[8]; //Krijon nje array prej 8 bajt
    		sr.nextBytes(bajtaRandomKEY); 	      //Krijon bajta random qe perdoren prej KEY
    		String KEY = bajtaRandomKEY.toString();
			byte[] encryptedData = rsaObj.encryptData(KEY);
			String rsa = encryptedData.toString();
			String enkodimiBase64RSA = Base64.getEncoder()
                    .encodeToString(rsa.getBytes(StandardCharsets.UTF_8.toString()));
			
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);			
			String enkodimiBase64DES = Base64.getEncoder()
                    .encodeToString(message.getBytes(StandardCharsets.UTF_8.toString()));
	
			String ciphertext = enkodimiBaze64UTF8 + "." + enkodimiBase64 + "." + enkodimiBase64RSA + "." + enkodimiBase64DES;
			
			if(file.endsWith(".txt")) {
	    		FileOutputStream ruajFile = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\" + file));
				XMLEncoder encoder = new XMLEncoder(ruajFile);
				encoder.writeObject(ciphertext);
				encoder.close();
				ruajFile.close();
				System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin '" + file + "'");
			}
			else
				System.out.println(ciphertext);
    	}
	    
	    public void Write(String name,String message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	    													InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(existsPublik){
				String enkodimiBaze64UTF8 = Base64.getEncoder()
	                    .encodeToString(name.getBytes(StandardCharsets.UTF_8.toString()));
	    	
		    	SecureRandom sr = new SecureRandom(); //Krijon nje random te sigurt
		    	byte [] bajtaRandomIV = new byte[8];  //Krijon nje array prej 8 bajt
		    	sr.nextBytes(bajtaRandomIV); 		  //Krijon bajta random qe perdoren prej iv
		    	String iv = bajtaRandomIV.toString();
				String enkodimiBase64 = Base64.getEncoder()
	                    .encodeToString(iv.getBytes(StandardCharsets.UTF_8.toString()));
		    	
		    	Metodat rsaObj = new Metodat();
	    		byte [] bajtaRandomKEY = new byte[8]; //Krijon nje array prej 8 bajt
	    		sr.nextBytes(bajtaRandomKEY); 	      //Krijon bajta random qe perdoren prej KEY
	    		String KEY = bajtaRandomKEY.toString();
				byte[] encryptedData = rsaObj.encryptData(KEY);
				String rsa = encryptedData.toString();
				String enkodimiBase64RSA = Base64.getEncoder()
	                    .encodeToString(rsa.getBytes(StandardCharsets.UTF_8.toString()));
				
				SecretKey key = KeyGenerator.getInstance("DES").generateKey();
				Cipher cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.ENCRYPT_MODE, key);			
				String enkodimiBase64DES = Base64.getEncoder()
	                    .encodeToString(message.getBytes(StandardCharsets.UTF_8.toString()));
		
				String ciphertext = enkodimiBaze64UTF8 + "." + enkodimiBase64 + "." + enkodimiBase64RSA + "." + enkodimiBase64DES;
				System.out.println(ciphertext);
			}
			else{
				System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
			}
    	}
	    
	    
	    public void Read_Message(String encryptedMessage) throws UnsupportedEncodingException, IOException {
			if(!encryptedMessage.endsWith(".txt")){
					String marrsi = encryptedMessage.split("\\.", 0)[0];
		            String mesazhi = encryptedMessage.split("\\.", 0)[3];
		            
		            byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
			        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
			        byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
			        String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(Mesazhi, Path, ".xml");
			    if(exist) {
			        System.out.println("Marresi: " + Marrsi);
			        System.out.println("Mesazhi: " + Mesazhi);
			     	}
			    else{
			     	System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");
			    }
			}
			else{
					String contents = Files.lines(Paths.get("C:\\Users\\Uran\\Desktop\\Projekti Siguri\\" +
								encryptedMessage)).collect(Collectors.joining("\n"));
					contents = contents.split("<string>")[1].split("</string>")[0];
					
					String marrsi = encryptedMessage.split("\\.", 0)[0];
		            String mesazhi = encryptedMessage.split("\\.", 0)[3];
		            
		            byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
			        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
			        byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
			        String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(Mesazhi, Path, ".xml");
			    if(exist) {
			        System.out.println("Marresi: " + Marrsi);
			        System.out.println("Mesazhi: " + Mesazhi);
			     	}
			    else{
			     	System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");
			    }
			}
    	}
}
