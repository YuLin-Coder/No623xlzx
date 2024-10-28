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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 公告栏管理 <span class="c-gray en">&gt;</span>公告信息列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	<span class="msg" style="color:red;">${requestScope.msg}</span>
<c:if test="${type==3}">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <a class="btn btn-success radius" title="返回主界面" href="BoardServlet?action=list" onclick="Hui_admin_tab(this)" ><i class="Hui-iconfont">&#xe67d;</i> 返回主界面</a></div></span>  </div>
	</span>
</c:if>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					
					<th width="80">公告标题</th>
					<th width="120">公告内容</th>
					<th width="50">编辑者</th>
					<th width="80">发布时间</th>
<c:if test="${type==3}">
					<th width="50">操作</th>
</c:if>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="u" >
				<tr class="text-c">				
				<td>${u.title }</td>
				<td>${u.content }</td>
				<td>${u.editor }</td>
				<td>${u.fbsj }</td>
					<c:if test="${type==3}">
				<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" href="BoardServlet?action=query&id=${u.id}" onclick="Hui_admin_tab(this)"   title="编辑" ><i class="Hui-iconfont">&#xe6df;</i></a>
					<a style="text-decoration:none" class="ml-5" onClick="del_board(this,'${u.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>
					</c:if>
				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>

</div>
</div>
		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_board(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/BoardServlet?action=delete&id="+id;
    }
}

</script>


</body>
</html>