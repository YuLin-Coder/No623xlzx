<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
　　<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 考勤管理 <span class="c-gray en">&gt;</span>考勤信息列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <a class="btn btn-success radius" title="返回主界面" href="AttendanceServlet?action=list" onclick="Hui_admin_tab(this)" ><i class="Hui-iconfont">&#xe67d;</i> 返回主界面</a></div></span>  </div>
<span class="msg" style="color:red;">${requestScope.msg}</span>

	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<a class="btn btn-primary radius" title="员工打卡"  onclick="Hui_admin_tab(this)" href="AttendanceServlet?action=toAddAttendance"><i class="Hui-iconfont">&#xe600;</i> 员工打卡</a>
	</span> 
	</div>

	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					
					<th width="80">打卡人</th>
					<th width="120">考勤状态</th>

					<th width="80">考勤时间</th>

					<th width="50">操作</th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="a" >
				<tr class="text-c">				
				<td>${a.teacher.name}</td>
				<td><c:if test="${a.status==1 }">已打卡</c:if><c:if test="${a.status==0 }">未打卡</c:if></td>

				<td><fmt:formatDate value="${a.create_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" href="AttendanceServlet?action=query&id=${a.id}" onclick="Hui_admin_tab(this)"   title="编辑" ><i class="Hui-iconfont">&#xe6df;</i></a>
					<a style="text-decoration:none" class="ml-5" onClick="del_attendance(this,'${a.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>

				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>

		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_attendance(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/AttendanceServlet?action=delete&id="+id;
    }
}

</script>


</body>
</html>