public class Main {
            // MAIN
    public static void main(String[] args) {
        Morse_Code M = new Morse_Code();
        Tap_Code T = new Tap_Code();

        if(args.length == 0){
				System.out.println("\n\t\t\tArgumentet Mungojne!");
		}
       if(args.length != 3){
			System.out.println("\n\t\tArgumentet jane termet qe vijne pas programit!\n");
            System.out.println("\t\t\tPerdorimi i Programit\n\n1.Per Kodin Morse shtyp -> morse-code encode <text> ose decode <text>");
            System.out.println("2.Per Kodin Tap shtyp -> tap-code encode <text> ose decode <text>");
            System.out.println();
				System.exit(1);
}
            String metoda = args[0];
			String EncodeOrDecode = args[1];
			String text = args[2];
		

		if(metoda.equals("morse-code") && (EncodeOrDecode.equals("encode"))) {
				if(text.matches("^[a-zA-Z0-9]+"))
					M.encode(text);
				else 
					System.out.println("\n\t\tTeksti duhet te permbaj vetem numra dhe shkronja\n\n");
			}
		else if(metoda.equals("morse-code") && (EncodeOrDecode.equals("decode"))){
			if(text.contains(".") || text.contains("-"))
							M.decode(text);
			else
				System.out.println("\n\t\tTeksti duhet te permbaj vetem . dhe -\n\n");
		}
		else if(metoda.equals("tap-code") && (EncodeOrDecode.equals("encode"))){
				if(text.matches("^[a-zA-Z]+"))
							T.encode(text);
				else 
					System.out.println("\n\t\tTeksti duhet te permbaj vetem shkronja\n\n");
		}
		else if(metoda.equals("tap-code") && (EncodeOrDecode.equals("decode"))){
			if(text.contains("."))
							T.decode(text);
			else 
				System.out.println("\n\t\tTeksti duhet te permabj vetem .\n\n");
		}

    }
}
