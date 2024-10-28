<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<div style="margin: 0 auto">
<h1>注册成功</h1>
 <a href="LoginServlet?action=toLogin"  class="btn btn-secondary radius"><i class="Hui-iconfont">&#xe67d;</i> 返回登录界面</a>
</div>
</body>
</html>
