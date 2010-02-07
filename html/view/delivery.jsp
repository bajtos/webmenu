<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="webmenu.data.Restaurants, webmenu.model.*, webmenu.viewmodel.DeliveryViewModel" %>
<%
   DeliveryViewModel model = DeliveryViewModel.get(request);
   String location = model.getLocationName();
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
   <div id="logo">
      <h1><a href="/">Chci Oběd!</a></h1>
   </div>

   <div id="page" class="rounded">
      <div id="content">
         <h1 class="title"><%= location %></h1>
         <h2 class="title">Rozvoz jídla - <!-- pondělí --><%= today.getDate() %>.<%= today.getMonth()+1 %>.<%= today.getYear() + 1900 %></h2>
         <div class="spacer"></div>

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
      <div id="sidebar">
         <ul class="daylist rounded">
            <li>&#xBB; <a href="<%= model.getDayUrl(Calendar.MONDAY) %>">pondělí</a></li>
            <li>&#xBB; <a href="<%= model.getDayUrl(Calendar.TUESDAY) %>">úterý</a></li>
            <li>&#xBB; <a href="<%= model.getDayUrl(Calendar.WEDNESDAY) %>">středa</a></li>
            <li>&#xBB; <a href="<%= model.getDayUrl(Calendar.THURSDAY) %>">čtvrtek</a></li>
            <li>&#xBB; <a href="<%= model.getDayUrl(Calendar.FRIDAY) %>">pátek</a></li>
         </ul>
      </div> <!-- sidebar -->

   </div> <!-- page -->

   <div id="footer" class="rounded">
      <span>&copy; 2009-2010 by <a href="mailto:info@chci-obed.eu">ChciOběd! team</a></span>
   </div> <!-- footer -->

<!-- Google Analytics -->
<jsp:include page="analytics.html"/>
</body>
</html>
