
# Data_Security
Ky është Projekti në lëndën Siguria e të dhënave!

                                                Faza e parë

Në këtë program janë implementuar tre funksione. Realizimin e këtyre funksioneve e kemi bërë me gjuhën programuese Java!
Kuptohet që të tre kemi punuar ne këtë projekt!

❂ Funksioni Morse Code:

  1. Encode(Konverton Alfabetin Latin ne kodin Morse)
  2. Decode(Kodin Morse e konverton ne Alfabetin Latin)
  3. Beep(Shpreh në tinguj Tekstin e Konvertuar)

❂ Funksioni Tap Code:

  1. Encode(Konverton Alfabetin Latin ne kodin Tap që organizohet në një tabelë 5x5 duke trokitur në shkronjën ,aq herë sa e ka rreshtin dhe aq herë sa e ka kolonën)
  2. Decode(Konverton Kodin Tap ne Alfabetin Latin duke përdorur këtë tabelë)

❂ Funksioni FourSquare Code:

  1. Encrypt(Enkripton Alfabetin Latin në Kodin FourSquare në 4 tabela 5x5 duke përdorur disa rregulla)
  2. Decrypt(Dekripton Kodin FourSquare në Alfabet Latin)


Ekzekutimi i Programit : 

✺ HAPI 1:

➢ Kompailimi në cmd(Command Prompt) apo Terminal(Sisteme Operative → Linux apo MacOS) duke përdorur komandën:  javac Main.java
  (Sigurohuni të jeni në pathin e duhur e.x -> Desktop)!

✺ HAPI 2:

➢ Për të ekzekutuar njërin nga Funksionet e programit duhet t'i shkruani komandat në formatin përshembull: 

➤ Për funksionin Morse Code:

      ✸ java Main morse-code encode <text>
      ✸ java Main morse-code decode <text>
      ✸ java Main morse-code --audio <text>

![image](https://user-images.githubusercontent.com/61246827/77172481-4f995200-6abe-11ea-8621-d4b1360fd11e.png)


➤ Për funksionin Tap Code:

      ✸ java Main tap-code encode <text>
      ✸ java Main tap-code decode <text>

![image](https://user-images.githubusercontent.com/61246827/77023251-0ba63000-698c-11ea-8a27-4b29d57fd471.png)

➤ Për funksionin FourSquare Code:

      ✸ java Main four-square encrypt <key1> <key2> <text>
      ✸ java Main four-square decrypt <key1> <key2> <text> 

![image](https://user-images.githubusercontent.com/61246827/77173024-2d540400-6abf-11ea-8676-91d826613755.png)


REFERENCAT:

https://gist.github.com/Xyene/6478305?fbclid=IwAR3nkyPECY5aaOrOUDaYuoaWTlB5cCj8P6q2ZjaKqWYDydgeba2AQGK4_qc (Këtu jemi orientuar për Funksionin Beep() ).

https://www.vogella.com/tutorials/JavaRegularExpressions/article.html (Këtu jemi orientuar për karaktere)

https://forum.moparisthebest.com/t/foursquare-cipher/409572.html?fbclid=IwAR3Ng8rZOZzIHxVAX1nHR0lsTqWtJdhL4z3GfHVd09lp7R0PWvRQ-Pn8Jw0 (Këtu jemi mbështetur per Krijimin e Kodit FourSquare)

                                                  Faza e dytë



❂ Komanda create-user:

1. Krijon një përdorues dhe gjeneron një dyshe të çelsave (çelësin publik dhe çelësin privat të cilët i ruan në një vend të caktuar si XML fajlla!)

❂ Komanda delete-user:

1. Largon(fshinë) çelësat publik dhe privat në bazë të emrit të përdoruesit!

❂ Komanda export-key:

1. Eksporton çelësin privat apo publik nga fajllat ku janë të ruajtur këta çelësa!

❂ Komanda import-key:

1. Importon çelësin privat | publik nga një shteg i dhënë dhe e vendos aty ku janë të ruajtur çelësat!

❂ Komanda write-message:

1. E shkruan një mesazh të enkriptuar për një shfrytëzues!

❂ Komanda read-message:

1. E dekripton dhe shfaqë në consolë mesazhin e enkriptuar!



Ekzekutimi i Programit : 

✺ HAPI 1:

➢ Kompailimi në cmd(Command Prompt) apo Terminal(Sisteme Operative → Linux apo MacOS) duke përdorur komandën:  javac Main.java
  (Sigurohuni të jeni në pathin e duhur e.x -> Desktop)!

✺ HAPI 2:

➢ Për të ekzekutuar njërin nga Funksionet e programit duhet t'i shkruani komandat në formatin përshembull:

➤ Për komandën create-user:

  ✸ java Main create-user <name>

<img width="985" alt="create-user" src="https://user-images.githubusercontent.com/61246827/81131517-b0d09400-8f4b-11ea-9f13-4050a1500241.png">


➤ Për komandën delete-user:

  ✸ java Main delete-user <name>

<img width="989" alt="delete-user" src="https://user-images.githubusercontent.com/61246827/81131522-b4fcb180-8f4b-11ea-83c0-07bfbf87bcb0.png">



➤ Për komandën export-key:
  
  ✸ java Main export-key <public> | <private> <name> [file]

<img width="1191" alt="export-key" src="https://user-images.githubusercontent.com/61246827/81131532-be861980-8f4b-11ea-8572-0243215c97cf.png">


➤ Për komandën import-key:

  ✸ java Main import-key <name> <path>

<img width="1192" alt="import-key" src="https://user-images.githubusercontent.com/61246827/81131677-35bbad80-8f4c-11ea-986c-e011457032b9.png">
   
   
➤ Për komandën write-message:

  ✸ java Main write-message <name> <message> [file]

<img width="1194" alt="write-message" src="https://user-images.githubusercontent.com/61246827/81131678-37857100-8f4c-11ea-8844-3f122a2e30e2.png">


➤ Për komandën read-message:

  ✸ java Main read-message <encrypted-message>

<img width="1193" alt="read-message" src="https://user-images.githubusercontent.com/61246827/81131689-3d7b5200-8f4c-11ea-8cd3-6fa6681ef202.png">


REFERENCAT:

  https://www.novixys.com/blog/how-to-generate-rsa-keys-java/

  https://stackoverflow.com/questions/22411958/how-to-save-a-file-in-java

  https://www.tutorialspoint.com/generate-random-bytes-in-java

  http://www.java2s.com/Code/Java/Security/EncryptingaStringwithDES.htm

  https://www.codota.com/code/java/methods/javax.servlet.http.HttpServletRequest/getRequestURL

  https://github.com/jaysridhar/java-stuff/tree/master/source/java-keygen

  



