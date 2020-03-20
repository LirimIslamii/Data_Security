
# Data_Security
Ky është Projekti në lëndën Siguria e të dhënave!

Në këtë program janë implementuar tre funksione. Realizimin e këtyre funksioneve e kemi bërë me gjuhën programuese Java!

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

![image](https://user-images.githubusercontent.com/61246827/77023092-a94d2f80-698b-11ea-89f3-c1ae7598c849.png)

➤ Për funksionin Tap Code:

      ✸ java Main tap-code encode <text>
      ✸ java Main tap-code decode <text>

![image](https://user-images.githubusercontent.com/61246827/77023251-0ba63000-698c-11ea-8a27-4b29d57fd471.png)

➤ Për funksionin FourSquare Code:

      ✸ java Main four-square encrypt <key1> <key2> <text>
      ✸ java Main four-square decrypt <key1> <key2> <text> 


REFERENCAT:

https://gist.github.com/Xyene/6478305?fbclid=IwAR3nkyPECY5aaOrOUDaYuoaWTlB5cCj8P6q2ZjaKqWYDydgeba2AQGK4_qc (Këtu jemi orientuar për Funksionin Beep() ).

https://www.vogella.com/tutorials/JavaRegularExpressions/article.html (Këtu jemi orientuar për karaktere)

https://forum.moparisthebest.com/t/foursquare-cipher/409572.html?fbclid=IwAR3Ng8rZOZzIHxVAX1nHR0lsTqWtJdhL4z3GfHVd09lp7R0PWvRQ-Pn8Jw0 (Këtu jemi mbështetur per Krijimin e Kodit FourSquare)




