<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<div class="page-container">
	<form action="AttendanceServlet?action=add" method="post" class="form form-horizontal" >


		<div class="row cl">

			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>考勤人：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<select name="t_id">
					<c:forEach items="${requestScope.teachers}" var="t">
					<option value="${t.id}">${t.name}</option>
					</c:forEach>
				</select>

			</div>
		</div>





		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确定考勤</button>


					<button  class="btn btn-default radius" onclick="window.location.href='AttendanceServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>

			</div>
		</div>
	</form>
</div>
<script>


</script>


</body>
</html>