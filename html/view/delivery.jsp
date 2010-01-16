<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="webmenu.data.Restaurants, webmenu.model.*, webmenu.viewmodel.DeliveryViewModel" %>
<%
   DeliveryViewModel model = DeliveryViewModel.get(request);
   String location = model.getLocation();
   Date today = model.getDate();
   OneDayMenu menu;
%>
<!DOCTYPE html>
<html>
<head>
   <title>Rozvoz jídla - <%= location %> - Chci Oběd!</title>
   <link href="/styles/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <div id="page">
      <div id="logo">
         <div class="rounded">
            <div>Chci Oběd!</div>
         </div>
      </div><div id="header">
         <div class="rounded">
            <h3><%= location %></h3>
            <h4>Rozvoz jídla <%= today.getDate() %>.<%= today.getMonth()+1 %>.<%= today.getYear() + 1900 %></h4>
         </div>
      </div><div id="content">

      <% menu = model.getMenu(Restaurants.MAM_HLAD_HK); %>
      <section>
         <header>
            <h4>MámHladvHK</h4> 
            (<span class="telefon">Telefon: 773 452 345</span>&nbsp;-&nbsp;
            <a href="http://www.mamhladvhk.cz/tydenni-menu.php">menu</a>&nbsp;-&nbsp;
            <a href="http://www.mamhladvhk.cz/info-o-rozvozu.php">kontakt</a>)
         </header>
         <table width="100%">
         <% if (menu == null) { %>
            <tr><td>Menu pro dnešní den není k dispozici.</td></tr>
         <% } else if (menu.isEmpty()) { %>
            <tr><td>Dnes nevaříme.</td></tr>
         <% } else { %>
            <% for (SoupItem soup : menu.getSoupItems()) { %>
               <tr><td colspan="2"><%= soup.getName() %>: <%= soup.getMeal() %></td></tr>
            <% } %>
            <% for (MenuItem item : menu.getMenuItems()) { %>
            <tr><td><%= item.getName() %>: <%= item.getMeal() %></td><td align="right"><%= item.getPrice() %>,- Kč</td></tr>
            <% } %>
         <% } %>
      </table>
      <footer>
        Uvedené ceny jsou pouze orientační, doporučujeme je zkontrolovat před objednáním.<br/>
        Pro dovezení jídla je nutné objednat alespoň dvě porce (menu).
      </footer>
      </section>

      <% /* TODO
      <hr/>

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
      */ %>
   </div> <!-- content -->
<!-- Google Analytics -->
<jsp:include page="analytics.html"/>  
</body>
</html>

