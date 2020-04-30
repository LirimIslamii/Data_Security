package rsaexample;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class test {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    public test() throws NoSuchAlgorithmException {
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

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
    private static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path +user+type+"");
        boolean exists = tempFile.exists();
        return exists;
    }   
    private static String Path = "C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\";
    	private byte[] encryptData(String data) throws IOException {
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

private void decryptData(byte[] data) throws IOException {
	byte[] descryptedData = null;
	try {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		descryptedData = cipher.doFinal(data);
	} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
		e.printStackTrace();
	}
	
	}
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        test keyPairGenerator = new test();
        keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
        keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
        
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGen.genKeyPair();
        RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();//ktu vetem qelsin publik
        
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
		
		Boolean exists = FileExists(args[1], Path, ".xml");
		
	    if (args[0].equals("create-user")) {
	    	if(!exists) {
	    	Person w1 = new Person(n1,e1,p1,q1,dp1,dq1,inverseQ1,d1);
	        
	        FileOutputStream priv = new FileOutputStream(new File("./"+ args[1] +".xml"));
			XMLEncoder encoder1 = new XMLEncoder(priv);
			encoder1.writeObject(w1);
			encoder1.close();
			priv.close();
			
	        Person w2 = new Person(n1,e1);
			
			FileOutputStream pub = new FileOutputStream(new File("./"+ args[1] +".pub.xml"));
			
			XMLEncoder encoder2 = new XMLEncoder(pub);
			encoder2.writeObject(w2);
			encoder2.close();
			pub.close();
	        
			System.out.println("Eshte gjeneruar qelsi publik: keys/" + args[1] + ".pub.xml");
			System.out.println("Eshte gjeneruar qelsi privat: keys/" + args[1] + ".xml");
			}
			else {
				System.out.println("Celesi '" + args[1] + "' ekziston paraprakisht.");
			}
		}
	    	    }
		else {
			System.out.print("Keni shenuar gabim");
	}
    }
}

