<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="webmenu.data.Restaurants" %>
<!DOCTYPE html>
<html>
<head>
   <title>Administrace - Chci Oběd!</title>
   <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
   <h3>Editace</h3>
   <ul>
      <li><a href="global-data">Global data</a></li>
   </ul>
   </ul>

   <h3>Spustit aktualizaci</h3>
   <ul>
      <% for (String rk : Restaurants.getKeys()) { %>
      <li><a href="update/<%= rk %>"><%= Restaurants.getRestaurant(rk).getName() %></a></li>
      <% } %>
   </ul>
</body>
</html>
