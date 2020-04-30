if(args[0].equals("import-key")) {
	    	Boolean existsPu = FileExists(args[2], "C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\keys\\\\", "");
			Boolean exist = FileExists(args[2], "C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\keys\\\\", "");
	    	if(!existsPu && args[2].endsWith(".pub.xml")) {
		        Person w2 = new Person(n1,e1);
				FileOutputStream pub = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\keys\\\\"+ args[2]));
				XMLEncoder encoder2 = new XMLEncoder(pub);
				encoder2.writeObject(w2);
				encoder2.close();
				pub.close();
				System.out.println("Celesi publik u ruajt ne fajllin " + args[2]);
	    	}
	    	else if(!exist && args[2].endsWith(".xml")) {
		    	Person w1 = new Person(n1,e1,p1,q1,dp1,dq1,inverseQ1,d1);
		        FileOutputStream priv = new FileOutputStream(new File("C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\keys\\\\"+ args[2]));
				XMLEncoder encoder1 = new XMLEncoder(priv);
				encoder1.writeObject(w1);
				encoder1.close();
				priv.close();
				System.out.println("Celesi privat u ruajt ne fajllin " + args[2]);
	    	} 
			else {
				System.out.println("Gabim: Celesi '"+ args[1] + "' ekziston paraprakisht.");
			}