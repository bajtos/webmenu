<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*, java.net.URLEncoder" %>
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
   <title>Rozvoz obědů - <%= todayString %> - <%= location %></title>
   <link href="/styles/main.css" rel="stylesheet" type="text/css" />
   <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
   <meta name="title" content="Rozvoz obědů - Chci oběd!"/>
   <meta name="description" content="Přehled denních menu restaurací, které poskytují rozvoz jídla."/>
   <link rel="img_src" href="/images/favicon.ico" /> <!-- facebook thumbnail -->
   <%-- this does not work in all browsers :( 
   <script type="text/javascript">
      function bookmark(url, title) {
         if (window.sidebar)
            window.sidebar.addPanel(title, url, "");
         else
            window.external.AddFavorite(url, title);
      }
   </script>
   --%>
</head>
<body>
   <div id="logo">
      <h1><a href="/">Chci oběd!</a></h1>
   </div>

   <div id="page" class="rounded">
      <div id="content">
         <h1 class="title"><%= location %></h1>
         <h2 class="title">Dovoz obědů &ndash; <%= todayString %></h2>
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
         <div class="section rounded">
            <h4>Další dny</h4>
            <ul class="daylist">
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
         </div>

         <div class="section rounded">
            <h4>Sdílet</h4>
            <ul class="social">
               <li><a target="_blank" rel="nofollow"
                  href="http://www.facebook.com/sharer.php?u=http://www.chci-obed.eu/&t=<%=URLEncoder.encode("Přehled denních menu pro rozvoz obědů", "UTF-8")%>"
                  ><span class="icon facebook"></span>Facebook</a></li>
               <li><a target="_blank" rel="nofollow"
                  href="http://twitter.com/home?status=<%=URLEncoder.encode("Přehled denních menu pro rozvoz obědů http://www.chci-obed.eu", "UTF-8")%>"
                  ><span class="icon twitter"></span>Twitter</a></li>
               <li><a target="_blank" rel="nofollow"
                  href="http://www.google.com/reader/link?url=http://www.chci-obed.eu/&title=<%=URLEncoder.encode("Přehled denních menu pro rozvoz obědů", "UTF-8")%>"
                  ><span class="icon google"></span>Google Buzz</a></li>
               <li><a target="_blank" rel="nofollow"
                  href="http://linkuj.cz/?id=linkuj&url=http://www.chci-obed.eu/&title=<%=URLEncoder.encode("Přehled denních menu pro rozvoz obědů", "UTF-8")%>"
                  ><span class="icon linkuj"></span>Linkuj</a></li>
               <%-- this does not work in all browsers :( 
               <li><a rel="nofollow"
                  href="javascript:bookmark('http://www.chci-obed.eu/', 'Chci oběd!')"
                  ><span class="icon bookmark"></span>Oblíbené</a></li>
               --%>
            </ul>
         </div>
      </div> <!-- sidebar -->

   </div> <!-- page -->

   <div id="footer" class="rounded">
      <span>&copy; 2009-2010 by <a href="mailto:info@chci-obed.eu">Chci oběd! team</a></span>
      <span>Icons by <a href="http://www.fasticon.com">FastIcon.com</a></span>
   </div> <!-- footer -->

<jsp:include page="analytics.html"/>
<jsp:include page="uservoice.html" />
</body>
</html>
