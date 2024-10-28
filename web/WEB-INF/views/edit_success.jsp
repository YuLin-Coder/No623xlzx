<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<h1>修改成功</h1>
 <a href="AdminServlet?action=toUpdateAdmin"  class="btn btn-secondary radius"><i class="Hui-iconfont">&#xe67d;</i> 返回主界面</a>

</body>
</html>
