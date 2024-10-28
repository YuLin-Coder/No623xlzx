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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 心理预约咨询管理 <span class="c-gray en">&gt;</span>心理预约咨询列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<span class="msg" style="color:red;">${requestScope.msg}</span>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="50">预约老师</th>
					<th width="50">预约状态</th>
					<th width="120">备注</th>
					<th width="80">预约时间</th>
					<th width="50">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="u" >
				<tr class="text-c">
				<td>${u.tname }</td>
					<td>
						<c:if test="${u.status==0}">
							<a class="btn btn-warning-outline  size-MINI radius" href='javascript:void(0)' >预约咨询失败</a>
						</c:if>
						<c:if test="${u.status==1}">
							<a class="btn btn-secondary-outline  size-MINI radius" href='javascript:void(0)' >预约审核中</a>
						</c:if>
						<c:if test="${u.status==2}">
							<a class="btn btn-success-outline  size-MINI radius" href='javascript:void(0)' >预约成功</a>
						</c:if>
					</td>
				<td>${u.bz }</td>
				<td>${u.yysj }</td>
				<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" href="YyzxServlet?action=query&yid=${u.yid}" onclick="Hui_admin_tab(this)"   title="编辑" ><i class="Hui-iconfont">&#xe6df;</i></a>
					<a style="text-decoration:none" class="btn btn-danger-outline size-MINI radius" onClick="del_yyzx(this,'${u.yid }',${student.id})" href="javascript:;" title="撤销预约">撤销预约</a>
				</td>

				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>

</div>
</div>
		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_yyzx(obj,yid,sid){
	 //用户安全提示
    if(confirm("您确定要撤销预约吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/YyzxServlet?action=delete&yid="+yid+"&s_id="+sid;
    }
}

</script>


</body>
</html>