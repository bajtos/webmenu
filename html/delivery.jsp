<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
   <title>Denní menu :: <%= request.getAttribute("location") %> :: rozvoz jídla</title>
   <link href="/resources/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
   Denní menu: <%= request.getAttribute("location") %>
   <p>

   <section>
   <h4><a href="http://www.mamhladvhk.cz/tydenni-menu.php">MámHladvHK.cz</a></h4><p>Telefon: 123-456-789</p>
   <table width="800px">
      <tr><td colspan="2">Polévka: Čočková s párkem</td></tr>
      <tr><td>Menu 2: 100g Kuřecí nudličky na kari se smetanou, špagety, sýr</td><td align="right">&ndash; / 77,- Kč</td></tr>
      <tr><td>Menu 3: 100g Kuřecí steak zapečený šunkou a brokolicí, americký brambor</td><td align="right">&ndash; / 85,- Kč</td></tr>
   </table>
   <footer>Uvedené ceny jsou pouze orientační, doporučujeme je zkontrolovat před objednáním.</footer>
   </section>

   <section>
   <h4><a href="http://www.sport-cafe.cz/menu.php">Sport Café</a></h4><p>Telefon: 123-456-789</p>
   <table width="800px">
      <tr><td colspan="2">Polévka: Uzená s kroupami</td></tr>
      <tr><td>Menu 1: Čínská kuřecí směs s bambusovými výhonky a sojovými klíčky, rýže</td><td align="right">&ndash; / 70,- Kč</td></tr>
      <tr><td>Menu 2: Makarony Neapol, sekaná rajčata, protlak, klobása, oregáno</td><td align="right">&ndash; / 70,- Kč</td></tr>
      <tr><td>Menu 3: Vídeňská roštěná s cibulkou dozlatova, hranolky</td><td align="right">&ndash; / 90,- Kč</td></tr>
      <tr><td>Menu 4: Vepřová kapsa, plněná šunkou a taveným sýrem, vařené brambory</td><td align="right">&ndash; / 90,- Kč</td></tr>
      <tr><td>Menu 5: Pizza Napolitana (sardelové řezy, sekaná rajčata, sýr, olivy, kapary, oregáno)</td><td align="right">&ndash; / 99,- Kč</td></tr>
   </table>
   <footer>Uvedené ceny jsou pouze orientační, doporučujeme je zkontrolovat před objednáním.</footer>
   </section>
      
</body>
</html>

