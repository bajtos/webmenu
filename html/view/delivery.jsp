<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="webmenu.data.Restaurants, webmenu.model.*, webmenu.viewmodel.*" %>
<%
   DeliveryViewModel model = DeliveryViewModel.get(request);
   String location = model.getLocationName();
   Date today = model.getDate().getTime();
   String todayString = MessageFormat.format("{0} {1,date,d.M.yyyy}", model.getDayName(model.getDate().get(Calendar.DAY_OF_WEEK)), today);
%>
<!DOCTYPE html>
<html>
<head>
   <title>Rozvoz jídla - <%= location %> - Chci Oběd!</title>
   <link href="/styles/main.css" rel="stylesheet" type="text/css" />
   <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
   <div id="logo">
      <h1><a href="/">Chci Oběd!</a></h1>
   </div>

   <div id="page" class="rounded">
      <div id="content">
         <h1 class="title"><%= location %></h1>
         <h2 class="title">Rozvoz jídla - <%= todayString %></h2>
         <div class="spacer"></div>

         <% if (StringUtils.isNotEmpty(model.getWarningText())) { %>
         <div class="warning rounded"><%= model.getWarningText() %></div>
         <% } %>

         <%
         for (String rk : Restaurants.getKeys()) {
            DayMenuViewModel menuModel = model.getMenu(rk);
            %><%@ include file="daymenu.jspc" %>
            <hr/>
         <% } %>

      </div> <!-- content -->
      <div id="sidebar">
         <ul class="daylist rounded">
            <% int[] days = { Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY };
            for (int day : days) {
               %><li>&#xBB;&nbsp;<%
               if (day == model.getDate().get(Calendar.DAY_OF_WEEK)) {
               %><span class="today"><%= model.getDayName(day) %></span> <%
               } else { 
               %><a href="<%= model.getDayUrl(day) %>"><%= model.getDayName(day) %></a><%
               } 
               %></li>
            <% } %>
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
