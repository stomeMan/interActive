<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>hello word</title>
	<meta http-equiv="cache-control" content="no-cache">
  </head>
  
  <body>
    <a href="/interAction_v2/helloword/hello.json">Say Hello</a>
    <br>
    <a>${message}</a>
  </body>
</html>
