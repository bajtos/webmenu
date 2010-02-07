<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="webmenu.model.*" %>
<%
   Restaurant restaurant = menuModel.getRestaurant();
   OneDayMenu menu = menuModel.getMenu();
%>
<section>
   <header>
      <h4><%= restaurant.getName() %></h4>
      (<span class="phone">Telefon: <%= restaurant.getPhone() %></span>&nbsp;-&nbsp;
      <a href="<%= restaurant.getMenuLink() %>">menu</a>&nbsp;-&nbsp;
      <a href="<%= restaurant.getContactLink() %>">kontakt</a>)
   </header>

   <table width="100%" class="menu">
      <% if (menu == null) { %>
      <tr><td>Menu pro dnešní den není k dispozici.</td></tr>
      <% } else if (menu.isEmpty()) { %>
      <tr><td>Dnes nevaříme.</td></tr>
      <% } else { %>
      <% for (SoupItem soup : menu.getSoupItems()) { %>
      <tr><td colspan="2" class="text"><%= soup.getName() %>: <%= soup.getMeal() %></td></tr>
      <% } %>
      <% for (MenuItem item : menu.getMenuItems()) { %>
      <tr><td class="text"><%= item.getName() %>: <%= item.getMeal() %></td><td class="price"><%= item.getPrice() %>,- Kč</td></tr>
      <% } %>
      <% } %>
   </table>
   
   <footer>
      <%= StringUtils.join(restaurant.getDisclaimers(), "<br/>") %>
   </footer>
</section>
