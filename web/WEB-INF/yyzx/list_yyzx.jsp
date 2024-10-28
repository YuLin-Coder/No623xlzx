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
	<div class="text-c">
		<a class="btn btn-success radius" title="返回主界面" href="YyzxServlet?action=list" onclick="Hui_admin_tab(this)" ><i class="Hui-iconfont">&#xe67d;</i> 返回主界面</a>

	</div>
	<span class="msg" style="color:red;">${requestScope.msg}</span>

	
	</span> 
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="80">学生编号</th>
					<th width="80">学生姓名</th>
                    <th width="80">性别</th>
                    <th width="80">专业</th>
                    <th width="80">班级</th>
					<th width="50">预约老师</th>
					<th width="120">备注</th>
					<th width="80">预约时间</th>
					<th width="50">操作</th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="u" >
				<tr class="text-c">				
				<td>${u.stuno }</td>
				<td>${u.realname }</td>
                    <td>${u.sex }</td>
                    <td>${u.major }</td>
                    <td>${u.bj }</td>
				<td>${u.tname }</td>
				<td>${u.bz }</td>
				<td>${u.yysj }</td>
				<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" onClick="del_yyzx(this,'${u.yid }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
function del_yyzx(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/YyzxServlet?action=delete&yid="+yid;
    }
}

</script>


</body>
</html>