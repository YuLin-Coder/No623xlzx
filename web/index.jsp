
<%
  String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>心理预约咨询管理平台</title>
  </head>
  <script >
    window.location.href="<%=path%>/IndexServlet?action=toIndex";
  </script>
  <body>

  </body>
</html>
