if(args[0].equals("export-key")) {
	    	Boolean exist = FileExists(args[2], Path, ".xml");
			Boolean existsPu = FileExists(args[2], Path, ".pub.xml");
			if(existsPu && args[1].equals("public") && !args[3].endsWith(".pub.xml")) {
	    		System.out.println("<RSAKeyValue>");
	    		System.out.println("    <Modulus>" + pubKey.getModulus() + "</Modulus>");
	    		System.out.println("    <Exponent>" + pubKey.getPublicExponent() + "</Exponent>");
	    		System.out.println("</RSAKeyValue>");
	    	}
			else if(existsPu && args[1].equals("public") && args[3].endsWith(".pub.xml")) {
				Person w2 = new Person(n1,e1);
				
				FileOutputStream pub = new FileOutputStream(new File("./"+ args[3]));
				
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				System.out.println("Celesi publik u ruajt ne fajllin '" + args[3] + "'.");
			}
			else if(!existsPu && args[1].equals("public")) {
	    		System.out.println("Gabim: Celesi publik '" + args[1] + "' nuk ekziston.");
			}
			else if(exist && args[1].equals("privat") && !args[3].endsWith(".xml")) {
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
			else if(exist && args[1].equals("privat") && args[3].endsWith(".xml")) {
				Person w2 = new Person(n1,e1);
				
				FileOutputStream pub = new FileOutputStream(new File("./"+ args[3]));
				
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				System.out.println("Celesi privat u ruajt ne fajllin '" + args[3] + "'.");
			}
			else if(!exist && args[1].equals("privat")) {
	    		System.out.println("Gabim: Celesi privat '" + args[1] + "' nuk ekziston.");
			}
	    	else {
	    		System.out.println("Gabim 2");
	    	}
