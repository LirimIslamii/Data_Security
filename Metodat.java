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
    private static String Path = "C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\";

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
        File tempFile = new File("" + path +user+type+"");
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
		} catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

		public void CreateUser(String argumenti2) throws IOException {
			Boolean exists = FileExists(argumenti2, Path, ".xml");
			Boolean existsPub = FileExists(argumenti2, Path, ".pub.xml");
			if(!(exists && existsPub)){
		    	Person w1 = new Person(n1,e1,p1,q1,dp1,dq1,inverseQ1,d1);
		     
		        FileOutputStream priv = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\"+ argumenti2 +".xml"));
				XMLEncoder encoder1 = new XMLEncoder(priv);
				encoder1.writeObject(w1);
				encoder1.close();
				priv.close();
				
		        Person w2 = new Person(n1,e1);
				
				FileOutputStream pub = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\"+ argumenti2 +".pub.xml"));
				
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				
				System.out.println("Eshte krijuar celesi privat 'keys/" + argumenti2 + ".xml'");
				System.out.println("Eshte krijuar celesi publik 'keys/" + argumenti2 + ".pub.xml'");
			}
			else{
				System.out.print("Gabim: Celesi '" + argumenti2 + "' ekziston paraprakisht.");
			}
		}
		public void Delete(String argumenti2) {
				Boolean exists = FileExists(argumenti2, Path, ".xml");
				Boolean existsPub = FileExists(argumenti2, Path, ".pub.xml");
		    	if(exists && existsPub) {
		    		File file1 = new File("./" + argumenti2 + ".pub.xml");
		    		File file2 = new File("./" + argumenti2 + ".xml");
		    		file1.delete();
		    		file2.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + argumenti2 + ".xml'");
		    		System.out.println("Eshte larguar celesi publik 'keys/" + argumenti2 + ".pub.xml'");
		    	}
		    	else if(existsPub) {
		    		File file1 = new File("./" + argumenti2 + ".pub.xml");
		    		file1.delete();
		    		System.out.println("Eshte larguar celesi publik 'keys/" + argumenti2 + ".pub.xml'");
		    	}
		    	else if(exists) {
		    		File file2 = new File("./" + argumenti2 + ".xml");
		    		file2.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + argumenti2 + ".xml'");
		    	}
		    	else {
		    		System.out.println("Gabim: Celesi '" + argumenti2 + "' nuk ekziston.");
		    	}	
		    }
		
		public void Export(String argumenti2, String argumenti3) throws IOException {	
		    	Boolean exist = FileExists(argumenti3, Path, ".xml");
				Boolean existsPu = FileExists(argumenti3, Path, ".pub.xml");
				if(existsPu && argumenti2.equals("public") && !argumenti3.endsWith(".pub.xml")) {
		    		System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + pubKey.getModulus() + "</Modulus>");
		    		System.out.println("    <Exponent>" + pubKey.getPublicExponent() + "</Exponent>");
		    		System.out.println("</RSAKeyValue>");
		    	}
				else if(!existsPu && argumenti2.equals("public")) {
		    		System.out.println("Gabim: Celesi publik '" + argumenti3 + "' nuk ekziston.");
				}
				else if(exist && argumenti2.equals("privat") && !argumenti2.endsWith(".xml")) {
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
				else if(!exist && argumenti2.equals("privat")) {
		    		System.out.println("Gabim: Celesi privat '" + argumenti3 + "' nuk ekziston.");
				}
		    	else {
		    		System.out.println("Gabim 2");
		    	}
		    }
		public void Export2(String argumenti2, String argumenti3, String argumenti4) throws IOException {	
			Boolean exist = FileExists(argumenti3, Path, ".xml");
			Boolean existsPu = FileExists(argumenti3, Path, ".pub.xml");
			
			if(existsPu && argumenti2.equals("public") && argumenti4.endsWith(".pub.xml")) {
				Person w2 = new Person(n1,e1);
				
				FileOutputStream pub = new FileOutputStream(new File("./"+ argumenti4));
				
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				System.out.println("Celesi publik u ruajt ne fajllin '" + argumenti4 + "'.");
			}
			else if(!existsPu && argumenti2.equals("public")) {
	    		System.out.println("Gabim: Celesi publik '" + argumenti2 + "' nuk ekziston.");
			}
			else if(exist && argumenti2.equals("privat") && argumenti4.endsWith(".xml")) {
				Person w2 = new Person(n1,e1);
				
				FileOutputStream pub = new FileOutputStream(new File("./"+ argumenti4));
				
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				System.out.println("Celesi privat u ruajt ne fajllin '" + argumenti4 + "'.");
			}
			else if(!exist && argumenti2.equals("privat")) {
	    		System.out.println("Gabim: Celesi privat '" + argumenti2 + "' nuk ekziston.");
			}
	    	else {
	    		System.out.println("Gabim 2");
	    	}
	    }
		
		
		public void Import(String argumenti1, String argumenti2) throws IOException {
		    	Boolean existsPu = FileExists(argumenti2, "./", "");
				Boolean exist = FileExists(argumenti2, "./", "");
		    	if(!existsPu && argumenti2.endsWith(".pub.xml")) {
			        Person w2 = new Person(n1,e1);
					FileOutputStream pub = new FileOutputStream(new File("../"+ argumenti1));
					XMLEncoder encoder2 = new XMLEncoder(pub);
					encoder2.writeObject(w2);
					encoder2.close();
					pub.close();
					System.out.println("Celesi publik u ruajt ne fajllin " + argumenti1 + ".pub.xml");
		    	}
		    	else if(!exist && argumenti2.endsWith(".xml")) {
			        Person w2 = new Person(n1,e1);
					FileOutputStream pub = new FileOutputStream(new File("../"+ argumenti1));
					XMLEncoder encoder2 = new XMLEncoder(pub);
					encoder2.writeObject(w2);
					encoder2.close();
					pub.close();
					System.out.println("Celesi publik u ruajt ne fajllin " + argumenti1);
					
					System.out.println("Eshte krijuar celesi privat 'keys/" + argumenti1 + ".xml'");
		    	} 
				else {
					System.out.println("Gabim: Fajlli i dhene nuk eshte valid.");
				}
		    }
	    public void Write(String ar1,String ar2,String ar3) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	String n11 = Base64.getEncoder()
                    .encodeToString(ar1.getBytes(StandardCharsets.UTF_8.toString()));
    		
	    	SecureRandom sr = new SecureRandom(); //create new secure random
	    	byte [] iv = new byte[8]; //create an array of 8 bytes 
	    	sr.nextBytes(iv); //create random bytes to be used for the IV (?) Not too sure.
	    	String ivi = iv.toString();
			String n2 = Base64.getEncoder()
                    .encodeToString(ivi.getBytes(StandardCharsets.UTF_8.toString()));
	    	
	    	test rsaObj = new test();
	    	SecureRandom sr1 = new SecureRandom(); //create new secure random
    		byte [] k = new byte[8]; //create an array of 8 bytes 
    		sr.nextBytes(k); //create random bytes to be used for the IV (?) Not too sure.    
    		String ko = k.toString();
			byte[] encryptedData = rsaObj.encryptData(ko);
			String eno = encryptedData.toString();
			String n3 = Base64.getEncoder()
                    .encodeToString(eno.getBytes(StandardCharsets.UTF_8.toString()));
			
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] dataToEn = ar2.getBytes();
			byte[] en = null;
			String encrypted = cipher.doFinal(dataToEn).toString();
			String n4 = Base64.getEncoder()
                    .encodeToString(ar2.getBytes(StandardCharsets.UTF_8.toString()));
	
			String ciphertext = n11 + "." + n2 + "." + n3 + "." + n4;
			
			if(ar3.endsWith(".txt")) {
	    		FileOutputStream priv = new FileOutputStream(new File("./"+ ar3));
				XMLEncoder encoder1 = new XMLEncoder(priv);
				encoder1.writeObject(ciphertext);
				encoder1.close();
				priv.close();
				System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin '" + ar3 + "'");
			}
			else
				System.out.println(ciphertext);
    	}
	    public void Write2(String ar1,String ar2) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	Boolean existsPu = FileExists(ar1, "C:\\\\Users\\\\Uran\\\\Desktop\\\\Projekti Siguri\\\\", ".pub.xml");
			if(existsPu){
		    	String n11 = Base64.getEncoder()
	                    .encodeToString(ar1.getBytes(StandardCharsets.UTF_8.toString()));
	    		
		    	SecureRandom sr = new SecureRandom(); //create new secure random
		    	byte [] iv = new byte[8]; //create an array of 8 bytes 
		    	sr.nextBytes(iv); //create random bytes to be used for the IV (?) Not too sure.
		    	String ivi = iv.toString();
				String n2 = Base64.getEncoder()
	                    .encodeToString(ivi.getBytes(StandardCharsets.UTF_8.toString()));
		    	
		    	test rsaObj = new test();
		    	SecureRandom sr1 = new SecureRandom(); //create new secure random
	    		byte [] k = new byte[8]; //create an array of 8 bytes 
	    		sr.nextBytes(k); //create random bytes to be used for the IV (?) Not too sure.    
	    		String ko = k.toString();
				byte[] encryptedData = rsaObj.encryptData(ko);
				String eno = encryptedData.toString();
				String n3 = Base64.getEncoder()
	                    .encodeToString(eno.getBytes(StandardCharsets.UTF_8.toString()));
				
				SecretKey key = KeyGenerator.getInstance("DES").generateKey();
				Cipher cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				byte[] dataToEn = ar2.getBytes();
				byte[] en = null;
				String encrypted = cipher.doFinal(dataToEn).toString();
				String n4 = Base64.getEncoder()
	                    .encodeToString(ar2.getBytes(StandardCharsets.UTF_8.toString()));
		
				String ciphertext = n11 + "." + n2 + "." + n3 + "." + n4;
				
					System.out.println(ciphertext);
			}
			else{
				System.out.println("Gabim: Celesi publik '" + ar1 + "' nuk ekziston.");
			}
    	}
	    
	    
	    public void Read_Message(String argumenti1) throws UnsupportedEncodingException, IOException {
			if(!argumenti1.endsWith(".txt")){
					String marrsi = argumenti1.split("\\.", 0)[0];
		            String mesazhi = argumenti1.split("\\.", 0)[3];
		            
		            byte[] dataBytes1 = Base64.getDecoder().decode(marrsi);
			        String marrsi1 = new String(dataBytes1, StandardCharsets.UTF_8.name());	
			        byte[] dataBytes2 = Base64.getDecoder().decode(mesazhi);
			        String mesazhi1 = new String(dataBytes2, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(marrsi1, "./", ".xml");
			    if(exist) {
			        System.out.println("Marresi: " + marrsi1);
			        System.out.println("Mesazhi: " + mesazhi1);
			     	}
			    else{
			     	System.out.println("Gabim: Celesi privat 'keys/" + marrsi1 +".xml' nuk ekziston");
			    }
			}
			else{
					String contents = Files.lines(Paths.get("C:\\Users\\Uran\\Desktop\\Projekti Siguri\\" + argumenti1)).collect(Collectors.joining("\n"));
					contents = contents.split("<string>")[1].split("</string>")[0];
					
					String marrsi = contents.split("\\.", 0)[0];
		            String mesazhi = contents.split("\\.", 0)[3];

					byte[] dataBytes1 = Base64.getDecoder().decode(marrsi);
			        String marrsi1 = new String(dataBytes1, StandardCharsets.UTF_8.name());	
			        byte[] dataBytes2 = Base64.getDecoder().decode(mesazhi);
			        String mesazhi1 = new String(dataBytes2, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(marrsi1, "./", ".xml");
			    if(exist) {
			        System.out.println("Marresi: " + marrsi1);
			        System.out.println("Mesazhi: " + mesazhi1);
			     	}
			    else{
			     	System.out.println("Gabim: Celesi privat 'keys/" + marrsi1 +".xml' nuk ekziston");
			    }
			
			}
    	}
}
