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
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.net.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Metodat {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static String Path = "////Users////lirimislami////Desktop////ds////keys////";

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
        
	String N = encoder.encodeToString(n.toByteArray());
	String E = encoder.encodeToString(e.toByteArray());
	String P = encoder.encodeToString(p.toByteArray());
	String Q = encoder.encodeToString(q.toByteArray());
	String DP = encoder.encodeToString(dp.toByteArray());
	String DQ = encoder.encodeToString(dq.toByteArray());
	String INVERSEQ = encoder.encodeToString(inverseQ.toByteArray());
	String D = encoder.encodeToString(d.toByteArray());

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

    private static void copyFile(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
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
	public static void ReturnMessage(String marrsi, String mesazhi) throws UnsupportedEncodingException, IOException{
				    byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
			        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
			        byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
			        String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(Marrsi, Path, ".xml");
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
			    return
			      numberPresent || specialCharacterPresent;
			}
			
	    Scanner input = new Scanner(System.in);
	    
		public void CreateUser(String name) throws IOException {		
			Boolean existsPrivat = FileExists(name, Path, ".xml");
			Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			 if(!(existsPrivat && existsPublik)){
			System.out.print("Jepni fjalekalimin: ");
			String password = input.nextLine();
			
		if(password.length() >= 6 && checkString(password) == true) {
			System.out.print("Perserit fjalekalimin: ");
			String repaitPassword = input.nextLine();
			if(!password.equals(repaitPassword)) {
				System.out.println("Gabim: Fjalekalimet nuk perputhen.");
			}
			else {
				 
			    	Person celsiPrivat = new Person(N,E,P,Q,DP,DQ,INVERSEQ,D);
			        FileOutputStream ruajCelsinPrivat = new FileOutputStream(new File("C:////Users////Uran////Desktop////Projekti Siguri////keys////"
			        																	+ name +".xml"));
					XMLEncoder enkoderiCelsitPrivat = new XMLEncoder(ruajCelsinPrivat);
					enkoderiCelsitPrivat.writeObject(celsiPrivat);
					enkoderiCelsitPrivat.close();
					ruajCelsinPrivat.close();
					
			        Person celsiPublik = new Person(N,E);
					FileOutputStream ruajCelsinPublik = new FileOutputStream(new File("C:////Users////Uran////Desktop////Projekti Siguri////keys////"
																			+ name +".pub.xml"));
					XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
					enkoderiCelsitPublik.writeObject(celsiPublik);
					enkoderiCelsitPublik.close();
					ruajCelsinPublik.close();
					
					FileWriter fileWriter = new FileWriter("C:/Users/Uran/Desktop/Projekti Siguri/users/" + name + ".txt");
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
			            for(int i=0; i< bytes.length ;i++)
			            {
			                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			            }
			            //Get complete hashed password in hex format
			            generatedPassword = sb.toString();
			        } 
			        catch (NoSuchAlgorithmException e) 
			        {
			            e.printStackTrace();
			        }
				 
			        FileWriter fileWriter2 = new FileWriter("C:/Users/Uran/Desktop/Projekti Siguri/password/" + name + "Pas.txt");
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
				Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
		    	if(existsPrivat && existsPublik) {
		    		File celsiPublik = new File(Path + name + ".pub.xml");
		    		File celsiPrivat = new File(Path + name + ".xml");
		    		celsiPublik.delete();
		    		celsiPrivat.delete();

		    		File password = new File("C:/Users/Uran/Desktop/Projekti Siguri/password/" + name + "Pas.txt");
		    		password.delete();

		    		File user = new File("C:/Users/Uran/Desktop/Projekti Siguri/users/" + name + ".txt");
		    		user.delete();

		    		System.out.println("Eshte larguar shfrytezuesi '" + name + "'");
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPublik) {
		    		File celsiPublik = new File(Path + name + ".pub.xml");
		    		celsiPublik.delete();
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPrivat) {
		    		File celsiPrivat = new File(Path + name + ".xml");
		    		celsiPrivat.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    	}
		    	else 
		    		System.out.println("Gabim: Celesi '" + name + "' nuk ekziston.");
		    }
		
		public void Export(String publicOrPrivat, String name) throws IOException {	
		    	Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
				if(existsPublik && publicOrPrivat.equals("public") && !name.endsWith(".pub.xml")) {
					String contents = Files.lines(Paths.get("////Users////lirimislami////Desktop////ds////keys////" + name + ".pub.xml"
							)).collect(Collectors.joining("\n"));   
					
		    		System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + contents.split("<string>")[1].split("</string>")[0] + "</Modulus>");
		    		System.out.println("    <Exponent>" + contents.split("<string>")[2].split("</string>")[0] + "</Exponent>");
		    		System.out.println("</RSAKeyValue>");
		    	}
				else if(!existsPublik && publicOrPrivat.equals("public"))
		    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
				else if(existsPrivat && publicOrPrivat.equals("private") && !publicOrPrivat.endsWith(".xml")) {
					String contents = Files.lines(Paths.get("////Users////lirimislami////Desktop////ds////keys////" + name + ".xml"
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
			Boolean existsPrivat = FileExists(name, Path, ".xml");
			Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(existsPublik && publicOrPrivat.equals("public") && file.endsWith(".pub.xml")) {
				File source = new File("////Users////lirimislami////Desktop////ds////keys////" + name + ".pub.xml");
				File destination = new File("////Users////lirimislami////Desktop////ds////keys////" + file);
				copyFile(source, destination);
				System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + file + "'");
			}
			else if(!existsPublik && publicOrPrivat.equals("public")) 
	    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");

			else if(existsPrivat && publicOrPrivat.equals("private") && file.endsWith(".xml")) {
				File source = new File("////Users////lirimislami////Desktop////ds////keys////" + name + ".xml");
				File destination = new File("////Users////lirimislami////Desktop////ds////keys////" + file);
				copyFile(source, destination);
				System.out.println("Celesi privat u ruajt ne fajllin '" + file + "'.");
			}
			else 
	    		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
	    }
		
		public void Import(String name, String path) throws IOException {
		    	Boolean existsPublik = FileExists(name, Path, ".pub.xml");
				Boolean existsPrivat = FileExists(name, Path, ".xml");
				if(!existsPublik && path.endsWith(".pub.xml")) {
					String emriFajllit = path.split("//")[6];	
					if(emriFajllit.endsWith(".pub.xml")) {
					Path temp = Files.move 
					        (Paths.get(path),  
					        Paths.get("////Users////lirimislami////Desktop////ds////keys////" + emriFajllit)); 
					  
					        if(temp != null) 
					            System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'."); 
					        else
					            System.out.println("Deshtoj bartja e fajllit"); 

					}
				}
				else if(!existsPrivat && path.endsWith(".xml")) {
					String emriFajllit = path.split("//")[6];	
					if(emriFajllit.endsWith(".xml")) {
					Path temp = Files.move 
					        (Paths.get(path),  
					        Paths.get("////Users////lirimislami////Desktop////ds////keys////" + name + ".xml")); 
							Person celsiPublik = new Person(N,E);
							FileOutputStream ruajCelsinPublik = new FileOutputStream(new File(Path + name + ".pub.xml"));
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
	            BufferedReader inputreader = new BufferedReader(
	                    new InputStreamReader(connection.getInputStream()));
	            String inputLine;

	            while ((inputLine = inputreader.readLine()) != null) {
	                response.append(inputLine);
	            }
	        }
	        

		    FileOutputStream out = new FileOutputStream(new File("////Users////lirimislami////Desktop////ds////keys////" + name + ".pub.xml"));
			XMLEncoder encoder = new XMLEncoder(out);
			encoder.writeObject(response.toString());
			encoder.close();
			out.close();
			System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'");
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
	    		FileOutputStream ruajFile = new FileOutputStream(new File("////Users////lirimislami////Desktop////ds////" + file));
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
			else
				System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
    	}

	    public void Read_Message(String encryptedMessage) throws UnsupportedEncodingException, IOException {
			if(!encryptedMessage.endsWith(".txt")){
					String marrsi = encryptedMessage.split("\\.", 0)[0];
		            String mesazhi = encryptedMessage.split("\\.", 0)[3];
		            ReturnMessage(marrsi,mesazhi);
			}
			else{
					String contents = Files.lines(Paths.get("//Users//lirimislami//Desktop//ds//" +
								encryptedMessage)).collect(Collectors.joining("\n"));
					contents = contents.split("<string>")[1].split("</string>")[0];
					String marrsi = contents.split("\\.", 0)[0];
		            String mesazhi = contents.split("\\.", 0)[3];
		            ReturnMessage(marrsi,mesazhi);
			}
    	}
}
