if(args[0].equals("export-key")) {
	    	Boolean exist = FileExists(args[2], Path, ".xml");
	    	if(exist) {
		    	if(args[1].equals("public")) {
		    		System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + pubKey.getModulus() + "</Modulus>");
		    		System.out.println("    <Exponent>" + pubKey.getPublicExponent() + "</Exponent>");
		    		System.out.println("</RSAKeyValue>");
		    	}
		    	else if(args[1].equals("privat")) {
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
		    	else {
		    		System.out.println("gabim 1");
		    	}
	    	}
	    
	    	else {
	    		System.out.println("Gabim 2");
	    	}