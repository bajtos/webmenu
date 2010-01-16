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

      <hr/>

      <% menu = model.getMenu(Restaurants.SPORT_CAFE_HK); %>
      <section>
         <header>
            <h4>Sport Café</h4> 
            (<span class="telefon">Telefon: 773 400 600</span>&nbsp;-&nbsp;
            <a href="http://www.sport-cafe.cz/menu.php">menu</a>&nbsp;-&nbsp;
            <a href="http://www.sport-cafe.cz/rozvoz.php">kontakt</a>)
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
        <%-- Pro dovezení jídla je nutné objednat alespoň dvě porce (menu).  --%>
      </footer>
      </section>

   </div> <!-- content -->
<!-- Google Analytics -->
<jsp:include page="analytics.html"/>  
</body>
</html>

