<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="webmenu.data.Restaurants" %>
<!DOCTYPE html>
<html>
<head>
   <title>Administrace - Chci Oběd!</title>
</head>
<body>
   <h3>Spustit aktualizaci</h3>
   <ul>
      <% for (String rk : Restaurants.getKeys()) { %>
      <li><a href="update/<%= rk %>"><%= Restaurants.getRestaurant(rk).getName() %></a></li>
      <% } %>
   </ul>
</body>
</html>
