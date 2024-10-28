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
	<form action="AttendanceServlet?action=update" method="post" class="form form-horizontal" >
		<input type="hidden" value="${attendance.id}" name="id">
		<input type="hidden" value="${type}" name="type">

		<div class="row cl">


		<div class="row cl">

			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>考勤状态：</label>
			<div class="formControls col-xs-3 col-sm-3">
			<select name="status">
                <option value="1" <c:if test="${attendance.status==1}">selected</c:if>>已打卡</option>
                <option value="0" <c:if test="${attendance.status==0}">selected</c:if>>未打卡</option>
            </select>

			</div>
		</div>






		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认修改</button>


					<button  class="btn btn-default radius" onclick="window.location.href='AttendanceServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>

			</div>
		</div>
	</form>
</div>
<script>


</script>


</body>
</html>