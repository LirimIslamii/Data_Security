if(args[0].equals("write-message")) {
	    	Boolean exist = FileExists(args[1], Path, ".xml");
	    	if(exist) {
	    		String n11 = Base64.getEncoder()
                        .encodeToString(args[1].getBytes(StandardCharsets.UTF_8.toString()));
	    		
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
				byte[] dataToEn = args[2].getBytes();
				byte[] en = null;
				String encrypted = cipher.doFinal(dataToEn).toString();
				String n4 = Base64.getEncoder()
                        .encodeToString(args[2].getBytes(StandardCharsets.UTF_8.toString()));
				
				String ciphertext = n11 + "." + n2 + "." + n3 + "." + n4;
				System.out.println(ciphertext);
	    	}
	    	else{
	    		System.out.println("Gabim: Celesi publik '" + args[1] + "' nuk ekziston.");
	    	}