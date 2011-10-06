<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="webmenu.model.GlobalData" %>
<%
  GlobalData model = (GlobalData)request.getAttribute(GlobalData.class.getName());
%>  
<!DOCTYPE html>
<html>
<head>
   <title>Global data</title>
   <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
   <form method="POST">
      <p>Warning text:</p>
      <textarea name="WarningText" rows=10 cols=80><%= model.getWarningText() %></textarea>
      <p>
      <input type="submit" value="Save" />
   </form>
</body>
