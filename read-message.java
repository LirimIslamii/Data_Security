if(args[0].equals("read-message")) {
	    	Boolean exist = FileExists(args[1], Path, ".xml");
	    	if(exist) {
	    		String str = args[2];
	            String marrsi = str.split("\\.", 0)[0];
	            String mesazhi = str.split("\\.", 0)[3];
	            
		        byte[] dataBytes1 = Base64.getDecoder().decode(marrsi);
		        String marrsi1 = new String(dataBytes1, StandardCharsets.UTF_8.name());	
		        byte[] dataBytes2 = Base64.getDecoder().decode(mesazhi);
		        String mesazhi1 = new String(dataBytes2, StandardCharsets.UTF_8.name());
		        
		        System.out.println("Marresi: " + marrsi1);
		        System.out.println("Mesazhi: " + mesazhi1);

	    	}
	    	else {
	    		System.out.println("Gabim 33");
	    	}