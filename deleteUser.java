 if (args[0].equals("delete-user"))
	    {
	    	if(exists) {
	    		File file1 = new File("C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\" + args[1] + ".pub.xml");
	    		File file2 = new File("C:\\\\Users\\\\Uran\\\\eclipse-workspace\\\\rsaexample\\\\" + args[1] + ".xml");
	    		file1.delete();
	    		file2.delete();
	    		System.out.println("The key " + args[1] + ".xml is deleted");
	    		System.out.println("The key " + args[1] + ".pub.xml is deleted");
	    	}
	    	else {
	    		System.out.println("Gabim: Celesi ' " + args[1] + "' nuk ekziston.");
	    	}	
	    }