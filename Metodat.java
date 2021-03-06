import java.beans.XMLEncoder;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.nio.file.*;
import java.net.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Calendar;
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 

class Metodat {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private SecretKey mydes;
    private static String Path = "C:/Users/Uran/Desktop/Projekti Siguri/";
    private static String PathKeys = "C:/Users/Uran/Desktop/Projekti Siguri/keys/";
    private static String PathKoha = "C:/Users/Uran/Desktop/Projekti Siguri/koha/";
    private static String PathPassword = "C:/Users/Uran/Desktop/Projekti Siguri/password/";
    private static String PathTokenat = "C:/Users/Uran/Desktop/Projekti Siguri/tokenat/";
    private static String PathUsers = "C:/Users/Uran/Desktop/Projekti Siguri/users/";
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String BC_PROV_ALGORITHM_SHA1RSA = "SHA1withRSA";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    Scanner input = new Scanner(System.in);
	
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    KeyPair keyPair = keyPairGen.genKeyPair();
    RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
    RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();	
    
    Base64.Encoder encoder = Base64.getEncoder();
	    
    BigInteger n = ((RSAKey) privKey).getModulus();
    BigInteger e = ((RSAPrivateCrtKey) privKey).getPublicExponent();
    BigInteger p = ((RSAPrivateCrtKey) privKey).getPrimeP();
    BigInteger q = ((RSAPrivateCrtKey) privKey).getPrimeQ();
    BigInteger dp = ((RSAPrivateCrtKey) privKey).getPrimeExponentP();
    BigInteger dq = ((RSAPrivateCrtKey) privKey).getPrimeExponentQ();
    BigInteger inverseQ = ((RSAPrivateCrtKey) privKey).getCrtCoefficient();
    BigInteger d = ((RSAPrivateKey) privKey).getPrivateExponent();
	
    String N = n.toString();
    String E = e.toString();
    String P = p.toString();
    String Q = q.toString();
    String DP = dp.toString();
    String DQ = dq.toString();
    String INVERSEQ = inverseQ.toString();
    String D = d.toString();
	
    public SecretKey getSecretKey() {
	 return mydes;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
	
    public String encrypt(String unencryptedString) {
	   String encryptedString = null;
	   try {        	
	       cipher.init(Cipher.ENCRYPT_MODE, key);
	       byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
	       byte[] encryptedText = cipher.doFinal(plainText);
	       encryptedString = new String(Base64.getEncoder().encodeToString(encryptedText));
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	return encryptedString;
    }

    public String decrypt(String encryptedString) {
	   String decryptedText=null;
	 try {
	       cipher.init(Cipher.DECRYPT_MODE, key);
	       byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
	       byte[] plainText = cipher.doFinal(encryptedText);
	       decryptedText= new String(plainText);
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
	return decryptedText;
    }
	
    public Metodat() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, InvalidKeySpecException {
	   KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
           keyGen.initialize(2048);
           KeyPair pair = keyGen.generateKeyPair();
           this.privateKey = pair.getPrivate();
           this.publicKey = pair.getPublic();
        
           myEncryptionKey = "ThisIsSpartaThisIsSparta";
           myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
           arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
           ks = new DESedeKeySpec(arrayBytes);
           skf = SecretKeyFactory.getInstance(myEncryptionScheme);
           cipher = Cipher.getInstance(myEncryptionScheme);
           key = skf.generateSecret(ks);
    }
	
    public static byte[] signBySoft(PrivateKey privateKey, byte[] data) throws Exception {
           byte[] result = null;
	   Signature st = Signature.getInstance(BC_PROV_ALGORITHM_SHA1RSA);
	   st.initSign(privateKey);
	   st.update(data);
	   result = st.sign();
	return result;
    }

    public static boolean validateSignBySoft(PublicKey publicKey,
	   byte[] signData, byte[] srcData) throws Exception {
	   Signature st = Signature.getInstance(BC_PROV_ALGORITHM_SHA1RSA);
	   st.initVerify(publicKey);
	   st.update(srcData);
	return st.verify(signData);
    }
		
    public static PublicKey bigIntegerToPublicKey(BigInteger e, BigInteger m) throws InvalidKeySpecException, NoSuchAlgorithmException  {
	   RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
           KeyFactory fact = KeyFactory.getInstance("RSA");
           PublicKey pubKey = fact.generatePublic(keySpec);
       return pubKey;
    }

    public static PrivateKey bigIntegerToPrivateKey(BigInteger e, BigInteger m) throws NoSuchAlgorithmException, InvalidKeySpecException {
  	   RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
	   KeyFactory fact = KeyFactory.getInstance("RSA");
	   PrivateKey privKey = fact.generatePrivate(keySpec);
       return privKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
           File f = new File(path);
           f.getParentFile().mkdirs();
           FileOutputStream fos = new FileOutputStream(f);
           fos.write(key);
           fos.flush();
           fos.close();
    }
	
    private static void copyFile(File source, File dest) throws IOException {
            Files.copy(source.toPath(), dest.toPath());
    }

    public static Boolean FileExists(String user, String path, String type){
           File tempFile = new File("" + path + user + type + "");
           boolean exists = tempFile.exists();
        return exists;
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
	
    public static void ReturnMessage(String marrsi, String mesazhi) throws UnsupportedEncodingException, IOException{
	   byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
           String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
	   byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
	   String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
	   Boolean exist = FileExists(Marrsi, PathKeys, ".xml");
           if(exist) {
	        System.out.println("Marresi: " + Marrsi);
	        System.out.println("Mesazhi: " + Mesazhi);
	   }
	   else
	        System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");
    }
	
    private static boolean checkString(String input) {
	    String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	    char currentCharacter;
	    boolean numberPresent = false;
	    boolean specialCharacterPresent = false;
	    
	    for (int i = 0; i < input.length(); i++) {
	        currentCharacter = input.charAt(i);
	        if (Character.isDigit(currentCharacter)) {
			numberPresent = true;
	        } else if (specialChars.contains(String.valueOf(currentCharacter))) {
			specialCharacterPresent = true;
		}
	     }
	return numberPresent || specialCharacterPresent;
    }
	
    public void CreateUser(String name) throws IOException {		
	   Boolean existsPrivat = FileExists(name, PathKeys, ".xml");
	   Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
	   if(!(existsPrivat && existsPublik)){
	   		  char passwordi[] = null;
		      try {
		         passwordi = PasswordField.getPassword(System.in, "Jepni fjalekalimin: ");
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      }
		     

		String password = String.valueOf(passwordi);

		
	   if(password.length() >= 6 && checkString(password) == true) {
	   	char repaitPasswordi[] = null;
		      try {
		         repaitPasswordi = PasswordField.getPassword(System.in, "Perserit fjalekalimin: ");
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      }

		String repaitPassword = String.valueOf(repaitPasswordi);
	   if(!password.equals(repaitPassword)) {
		System.out.println("Gabim: Fjalekalimet nuk perputhen.");
	   }
	   else {	 
		Person celsiPrivat = new Person(N,E,P,Q,DP,DQ,INVERSEQ,D);
		FileOutputStream ruajCelsinPrivat = new FileOutputStream(new File(PathKeys + name +".xml"));
		XMLEncoder enkoderiCelsitPrivat = new XMLEncoder(ruajCelsinPrivat);
		enkoderiCelsitPrivat.writeObject(celsiPrivat);
		enkoderiCelsitPrivat.close();
		ruajCelsinPrivat.close();
					
		Person celsiPublik = new Person(N,E);
		FileOutputStream ruajCelsinPublik = new FileOutputStream(new File(PathKeys + name +".pub.xml"));
		XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
		enkoderiCelsitPublik.writeObject(celsiPublik);
		enkoderiCelsitPublik.close();
		ruajCelsinPublik.close();
					
		FileWriter fileWriter = new FileWriter(PathUsers + name + ".txt");
    		fileWriter.write(name);
   		fileWriter.close();

   		String generatedPassword = null;
	 try {
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		//Add password bytes to digest
		md.update(password.getBytes());
		//Get the hash's bytes 
		byte[] bytes = md.digest();
		//This bytes[] has bytes in decimal format;
		//Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
	   for(int i=0; i< bytes.length ;i++) {
		sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	   }
		//Get complete hashed password in hex format
		generatedPassword = sb.toString();
	 } 
	 catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	 }
				 
		FileWriter fileWriter2 = new FileWriter(PathPassword + name + "Pas.txt");
    		fileWriter2.write(generatedPassword);
   		fileWriter2.close();
				  
		System.out.println("Eshte krijuar shfrytezuesi '" + name + "'");
		System.out.println("Eshte krijuar celesi privat 'keys/" + name + ".xml'");
		System.out.println("Eshte krijuar celesi publik 'keys/" + name + ".pub.xml'");	
	       }
	}
	   else{
	       System.out.print("Gabim: Fjalekalimi duhet te permbaje se paku nje numer ose simbol dhe duhet te jete i gjate se paku 6 karaktere.");
	       }
	   }
	   else {
	       System.out.print("Gabim: Celesi '" + name + "' ekziston paraprakisht.");
	   }
    }
	
    public void Delete(String name) {
	   Boolean existsPrivat = FileExists(name, PathKeys, ".xml");
	   Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
           if(existsPrivat && existsPublik) {
	   	File celsiPublik = new File(PathKeys + name + ".pub.xml");
		File celsiPrivat = new File(PathKeys + name + ".xml");
		celsiPublik.delete();
		celsiPrivat.delete();

		File password = new File(PathPassword + name + "Pas.txt");
		password.delete();

		File user = new File(PathUsers + name + ".txt");
		user.delete();

		File tokeni = new File(PathTokenat + name + ".txt");
		tokeni.delete();

		File koha = new File(PathKoha + name + "Koha.txt");
		koha.delete();


		System.out.println("Eshte larguar shfrytezuesi '" + name + "'");
		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
           }
           else if(existsPublik) {
		File celsiPublik = new File(PathKeys + name + ".pub.xml");
		celsiPublik.delete();
		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
	   }
	   else if(existsPrivat) {
		File celsiPrivat = new File(PathKeys + name + ".xml");
		celsiPrivat.delete();
		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
	   }
  	   else 
		System.out.println("Gabim: Celesi '" + name + "' nuk ekziston.");
    }
		
    public void Login(String name) throws Exception{
	   Boolean exists = FileExists(name, PathKeys, ".xml");
	   if(exists){				
	   	String contentsPrivate = Files.lines(Paths.get(PathKeys + name + ".xml"
			)).collect(Collectors.joining("\n")); 
		String privateExponent = contentsPrivate.split("<string>")[3].split("</string>")[0];
		String modulus = contentsPrivate.split("<string>")[6].split("</string>")[0];
						
		BigInteger m = new BigInteger(modulus);
		BigInteger d = new BigInteger(privateExponent);
			    		
		byte[] a = signBySoft(bigIntegerToPrivateKey(d,m), "abc".getBytes());

		char passwordi[] = null;
		      try {
		         passwordi = PasswordField.getPassword(System.in, "Jepni fjalekalimin: ");
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      }
		     

		String pass = String.valueOf(passwordi);
			
		String contents = Files.lines(Paths.get(PathPassword + name + "Pas.txt")).collect(Collectors.joining("\n"));
		String generatedPassword = null;
	 try {
	        // Create MessageDigest instance for MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(pass.getBytes());
	        //Get the hash's bytes 
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	     for(int i=0; i< bytes.length;i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	     }
	        //Get complete hashed password in hex format
	        generatedPassword = sb.toString();
	 } 
	 catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	 }
		Boolean existsPas = FileExists(name, PathPassword, "Pas.txt");
		if(generatedPassword.equals(contents)  && existsPas) {
			Calendar calendar = Calendar.getInstance();
      			//Returns current time in millis
      			long timeMilli2 = calendar.getTimeInMillis() + 1200000;//20 minuta
      			String koha = String.valueOf(timeMilli2); 
      			
      		String tokeni = Base64.getEncoder().encodeToString(a);
      		
			FileWriter fileWriter = new FileWriter(PathTokenat + name + ".txt");
			fileWriter.write(tokeni);
			fileWriter.close();

			FileWriter fileWriterkoha = new FileWriter(PathKoha + name + "Koha.txt");
			fileWriterkoha.write(koha);
			fileWriterkoha.close();

			System.out.print("Token: " + tokeni);	
		}
		else {
			System.out.println("Gabim: Shfrytezuesi ose fjalekalimi i gabuar.");
		}
	 }
		else {
			System.out.println("Gabim: Shfrytezuesi ose fjalekalimi i gabuar.");
		}
    }

    public void Status(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
 	   File directoryPath = new File(PathTokenat);
 	   //List of all files and directories
	   String contents[] = directoryPath.list();
	for(int i=0; i<contents.length; i++) {
	   String contents1 = Files.lines(Paths.get(PathTokenat + contents[i])).collect(Collectors.joining("\n"));									

	   if(contents1.equals(token)){
	   	Calendar calendar = Calendar.getInstance();
      		//Returns current time in millis
      		long timeMilli2 = calendar.getTimeInMillis();
      		String kohaTashme = String.valueOf(timeMilli2); 

		String shfrytezuesi = "User: " + contents[i].substring(0, contents[i].length() - 4);
		String koha = Files.lines(Paths.get(PathKoha + contents[i].substring(0, contents[i].length() - 4) + "Koha.txt"))
																			.collect(Collectors.joining("\n"));
		long l = Long.parseLong(koha);  
		DateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				 	
		long o = Long.parseLong(kohaTashme);  
	
		String nami = contents[i].substring(0, contents[i].length() - 4);
		Boolean existsPrivat = FileExists(nami, PathKeys, ".xml");
	    Boolean existsPublik = FileExists(nami, PathKeys, ".pub.xml");
	    if(existsPrivat && existsPublik){
			String contentsPublic = Files.lines(Paths.get(PathKeys + nami + ".pub.xml"
    									)).collect(Collectors.joining("\n")); 
    		String modulus = contentsPublic.split("<string>")[2].split("</string>")[0];
    		String publicExponent = contentsPublic.split("<string>")[1].split("</string>")[0];
    										
    		BigInteger e = new BigInteger(publicExponent);
    		BigInteger m = new BigInteger(modulus);
    							    		
    		byte[] a = Base64.getDecoder().decode(token);
	    	boolean on = validateSignBySoft(bigIntegerToPublicKey(e,m), a, "abc".getBytes());

			if(o < l && on == true) {
				System.out.println("\n" + shfrytezuesi);
				System.out.println("Valid: PO");
				System.out.println("Skadimi: "  + simple.format(l));	
			}
			else {
				System.out.println("\n" + shfrytezuesi);
				System.out.println("Valid: JO");
				System.out.println("Skadimi: Koha ka skaduar");
			}
				break;
		}
		else{
			System.out.println("Gabim: Celesi privat '" + nami + "' nuk ekziston.");
			System.out.println("Gabim: Celesi publik '" + nami + "' nuk ekziston.");
			break;
		}
	}
		if(contents.length - 1 == i){
			System.out.println("\nTokeni nuk eshte valid ose nuk ekziston shfrytezuesi.");
		}
	}		
    }

    public void Export(String publicOrPrivat, String name) throws IOException {	
	   Boolean existsPrivat = FileExists(name, PathKeys, ".xml");
	   Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
	   if(existsPublik && publicOrPrivat.equals("public") && !name.endsWith(".pub.xml")) {
	   	String contents = Files.lines(Paths.get(PathKeys + name + ".pub.xml"
							)).collect(Collectors.joining("\n"));   
		System.out.println("<RSAKeyValue>");
		System.out.println("    <Modulus>" + contents.split("<string>")[1].split("</string>")[0] + "</Modulus>");
		System.out.println("    <Exponent>" + contents.split("<string>")[2].split("</string>")[0] + "</Exponent>");
    		System.out.println("</RSAKeyValue>");
 	   }
	   else if(!existsPublik && publicOrPrivat.equals("public"))
		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
	   else if(existsPrivat && publicOrPrivat.equals("private") && !publicOrPrivat.endsWith(".xml")) {
		String contents = Files.lines(Paths.get(PathKeys + name + ".xml"
							)).collect(Collectors.joining("\n")); 
		System.out.println("<RSAKeyValue>");
		System.out.println("    <Modulus>" + contents.split("<string>")[6].split("</string>")[0] + "</Modulus>");
		System.out.println("    <Exponent>" + contents.split("<string>")[4].split("</string>")[0] + "</Exponent>");
		System.out.println("    <P>" + contents.split("<string>")[7].split("</string>")[0] + "</P>");
		System.out.println("    <Q>" + contents.split("<string>")[8].split("</string>")[0] + "</Q>");
		System.out.println("    <DP>" + contents.split("<string>")[1].split("</string>")[0] + "</DP>");
		System.out.println("    <DQ>" + contents.split("<string>")[2].split("</string>")[0] + "</DQ>");
		System.out.println("    <InverseQ>" + contents.split("<string>")[5].split("</string>")[0] + "</InverseQ>");
		System.out.println("    <D>" + contents.split("<string>")[3].split("</string>")[0] + "</D>");
		System.out.println("</RSAKeyValue>");
	   }
	   else 
		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
    }
		
    public void Export(String publicOrPrivat, String name, String file) throws IOException {	
	   Boolean existsPrivat = FileExists(name, PathKeys, ".xml");
           Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
	   if(existsPublik && publicOrPrivat.equals("public") && file.endsWith(".pub.xml")) {
	   	File source = new File(PathKeys + name + ".pub.xml");
		File destination = new File(PathKeys + file);
		copyFile(source, destination);
		System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + file + "'");
	   }
	   else if(!existsPublik && publicOrPrivat.equals("public")) 
    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
	   else if(existsPrivat && publicOrPrivat.equals("private") && file.endsWith(".xml")) {
		File source = new File(PathKeys + name + ".xml");
		File destination = new File(PathKeys + file);
		copyFile(source, destination);
		System.out.println("Celesi privat u ruajt ne fajllin '" + file + "'.");
	   }
	   else 
	  	System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
    }
		
    public void Import(String name, String path) throws IOException {
           Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
       	   Boolean existsPrivat = FileExists(name, PathKeys, ".xml");
       	   if(!existsPublik && path.endsWith(".pub.xml")) {
     	   	String emriFajllit = path.split("////")[6];	
	   if(emriFajllit.endsWith(".pub.xml")) {
	   	Path temp = Files.move (Paths.get(path),  
		Paths.get("C:////Users////Uran////Desktop////Projekti Siguri////keys////" + emriFajllit)); 
		if(temp != null) 
			System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'."); 
		else
			System.out.println("Deshtoj bartja e fajllit"); 
		}
	   }
	   else if(!existsPrivat && path.endsWith(".xml")) {
		String emriFajllit = path.split("////")[6];	
	   if(emriFajllit.endsWith(".xml")) {
		Path temp = Files.move (Paths.get(path),  
		Paths.get("C:////Users////Uran////Desktop////Projekti Siguri////keys////" + name + ".xml")); 
		Person celsiPublik = new Person(N,E);
		FileOutputStream ruajCelsinPublik = new FileOutputStream(new File(PathKeys + name + ".pub.xml"));
		XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
		enkoderiCelsitPublik.writeObject(celsiPublik);
		enkoderiCelsitPublik.close();
		ruajCelsinPublik.close();					
	     if(temp != null) {
		System.out.println("Celesi privat u ruajt ne fajllin 'keys/" + name + ".xml'."); 
		System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'."); 
	     }
  	     else
		System.out.println("Deshtoj bartja e fajllit"); 
	     } 
	   }
	   else if(existsPublik) 
		System.out.println("Gabim: Celesi '" + name + "' ekziston paraprakisht.");
	   else 
		System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid");
    }
    
    public void Pastebin(String name,String url) throws IOException {
	   URL urlObj = new URL(url);
	   HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
	   connection.setRequestMethod("GET");

	   Integer responseCode = connection.getResponseCode();
	   StringBuffer response = new StringBuffer();
	   if (responseCode == HttpURLConnection.HTTP_OK) {
	   	BufferedReader inputreader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        while ((inputLine = inputreader.readLine()) != null) {
	        	response.append(inputLine);
	        }
	   }
	   FileOutputStream out = new FileOutputStream(new File(PathKeys + name + ".pub.xml"));
	   XMLEncoder encoder = new XMLEncoder(out);
	   encoder.writeObject(response.toString());
           encoder.close();
	   out.close();
    	   System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'");
    }
    
    public static String WriteMessage(String name,String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IOException {
       String enkodimiBaze64UTF8 = Base64.getEncoder().encodeToString(name.getBytes(StandardCharsets.UTF_8.toString()));
     	
  	   SecureRandom sr = new SecureRandom(); //Krijon nje random te sigurt
  	   byte [] bajtaRandomIV = new byte[8];  //Krijon nje array prej 8 bajt
  	   sr.nextBytes(bajtaRandomIV); 		  //Krijon bajta random qe perdoren prej iv
  	   String iv = bajtaRandomIV.toString();
  	   String enkodimiBase64 = Base64.getEncoder().encodeToString(iv.getBytes(StandardCharsets.UTF_8.toString()));
  	   
  	   Metodat rsaObj = new Metodat();
      	   byte [] bajtaRandomKEY = new byte[8]; //Krijon nje array prej 8 bajt
      	   sr.nextBytes(bajtaRandomKEY); 	      //Krijon bajta random qe perdoren prej KEY
      	   String KEY = bajtaRandomKEY.toString();
     	   byte[] encryptedData = rsaObj.encryptData(KEY);
  	   String rsa = encryptedData.toString();
  	   String enkodimiBase64RSA = Base64.getEncoder().encodeToString(rsa.getBytes(StandardCharsets.UTF_8.toString()));
  					
  	   Metodat td= new Metodat();
  	   String enkodimiBase64DES = td.encrypt(message);

  	   return enkodimiBaze64UTF8 + "." + enkodimiBase64 + "." + enkodimiBase64RSA + "." + enkodimiBase64DES;
    }

    public void Write(String name,String message,String file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	    				InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {	
	   
    	String ciphertext = WriteMessage(name, message);
	   
	   if(file.endsWith(".txt")) {
	   	FileOutputStream ruajFile = new FileOutputStream(new File(Path + file));
		XMLEncoder encoder = new XMLEncoder(ruajFile);
		encoder.writeObject(ciphertext);
		encoder.close();
		ruajFile.close();
		System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin '" + file + "'");
	   }
	   else
		System.out.println(ciphertext);
    }
	    
    public void Write(String name,String message) throws Exception {	
	   Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
 	   if(existsPublik){
 	    String ciphertext = WriteMessage(name, message);

		System.out.println(ciphertext);
 	   }
 	   else
		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
    }

    public void Write(String name,String message, String sender, String token) throws Exception {	
 	   Boolean existsPublik = FileExists(name, PathKeys, ".pub.xml");
  	   if(existsPublik){
  		 String ciphertext1 = WriteMessage(name, message);
 				
 	        // base64(utf8(<sender>))
 		String senderUTF8sender = Base64.getEncoder().encodeToString(sender.getBytes(StandardCharsets.UTF_8.toString()));

 	        // base64(signature(des(<message>)))
 		String contentsPrivate = Files.lines(Paths.get(PathKeys + name + ".xml"
 						)).collect(Collectors.joining("\n")); 
 		String privateExponent = contentsPrivate.split("<string>")[3].split("</string>")[0];
 		String modulus = contentsPrivate.split("<string>")[6].split("</string>")[0];
 		BigInteger m = new BigInteger(modulus);
 		BigInteger d = new BigInteger(privateExponent);
 		byte[] a = signBySoft(bigIntegerToPrivateKey(d,m), message.getBytes());
 		        
 		String SignatureEnkodimiBase64DES = Base64.getEncoder().encodeToString(a);

 		String ciphertext = ciphertext1 + "." + senderUTF8sender + "." + SignatureEnkodimiBase64DES;
 		System.out.println("\n" + ciphertext);
  	   }
  	   else
 		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
     }
    
    public void Read_Message(String encryptedMessage) throws Exception {
     char someChar = '.';
        int count = 0;
          
        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (encryptedMessage.charAt(i) == someChar) {
                count++;
            }
        }
    	if(count == 5){
    	 	   if(!encryptedMessage.endsWith(".txt")){
    		   	String marrsi = encryptedMessage.split("\\.", 0)[0];
    			String mesazhi = encryptedMessage.split("\\.", 0)[3];
    			String sender = encryptedMessage.split("\\.", 0)[4];

    			byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
    			String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	

    			byte[] dekodimiSender = Base64.getDecoder().decode(sender);
    			String Sender = new String(dekodimiSender, StandardCharsets.UTF_8.name());
    				       
    			Metodat td= new Metodat();
    			String decrypted = td.decrypt(mesazhi);

    			Boolean existsPublik = FileExists(Marrsi, PathKeys, ".pub.xml");
    			Boolean existsPrivat = FileExists(Marrsi, PathKeys, ".xml");
    			if(existsPrivat) {
    			    if(existsPublik) {
    					String contentsPublic = Files.lines(Paths.get(PathKeys + Marrsi + ".pub.xml"
    									)).collect(Collectors.joining("\n")); 
    					String modulus = contentsPublic.split("<string>")[2].split("</string>")[0];
    					String publicExponent = contentsPublic.split("<string>")[1].split("</string>")[0];
    										
    					BigInteger e = new BigInteger(publicExponent);
    					BigInteger m = new BigInteger(modulus);
    							    		
    					byte[] a = Base64.getDecoder().decode(encryptedMessage.split("\\.", 0)[5]);
    					if(a.length == 256){

	    					boolean on = validateSignBySoft(bigIntegerToPublicKey(e,m), a, decrypted.getBytes());
	    					
	    					System.out.println("Marresi: " + Marrsi);
	    					System.out.println("Mesazhi: " + decrypted);
	    					System.out.println("Sender: " + Sender);	
	    					if(on == true) {	
	    						System.out.println("Nenshkrimi: Valid");
	    					}
	    					else {
	    						System.out.println("Nenshkrimi: mungon celesi publik '" + Marrsi + "'");
	    					}
	    				}
	    				else{
	    					System.out.println("Keni shenuar gabim pjesen e fundit te celesit");
	    				}
    			    }
    			    else {
    					System.out.println("Nenshkrimi: mungon celesi publik '" + Marrsi + "'");
    			    }
    			}
    			else
    				System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");
    			}	    	
    		}
    		else if(count == 3){
    			if(!encryptedMessage.endsWith(".txt")){
    					String marrsi = encryptedMessage.split("\\.", 0)[0];
    		            String mesazhi = encryptedMessage.split("\\.", 0)[3];

    		            byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
    			        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
    			       
    			        Metodat td= new Metodat();
    			        String decrypted = td.decrypt(mesazhi);

    			        Boolean exist = FileExists(Marrsi, PathKeys, ".xml");
    			    if(exist) {
    			        System.out.println("\nMarresi: " + Marrsi);
    			        System.out.println("Mesazhi: " + decrypted);
    			     	}
    			    else
    			     	System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");

    			}
    			else{
    				String contents = Files.lines(Paths.get(Path +
    									encryptedMessage)).collect(Collectors.joining("\n"));
    				contents = contents.split("<string>")[1].split("</string>")[0];
    				String marrsi = contents.split("\\.", 0)[0];
    			    String mesazhi = contents.split("\\.", 0)[3];
    			    ReturnMessage(marrsi,mesazhi);
    		 	}
    		}
    		else {
		     	System.out.println("Keni shenuar gabim encrypted message.");
    		}
			
    }
}
