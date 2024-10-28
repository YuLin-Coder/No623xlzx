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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 心理老师管理 <span class="c-gray en">&gt;</span>心理老师信息列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">		
        <form action="TeacherServlet?action=findByMap" method="post">
			<input type="text" name="tno" id="tno" placeholder="根据工号查询" style="width:250px" class="input-text">
			<input type="text" name="name" id="name" placeholder="根据教师姓名查询" style="width:250px" class="input-text">
			<button  class="btn btn-success" id="search" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	   </form>
	</div>
	<span class="msg" style="color:red;">${requestScope.msg}</span>
	<c:if test="${type==3}">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">

	<a class="btn btn-primary radius" title="添加教师信息"  onclick="Hui_admin_tab(this)" href="TeacherServlet?action=toAddTeacher"><i class="Hui-iconfont">&#xe600;</i> 添加心理老师</a>

	</span> 
	</div>
	</c:if>

	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					
					<th width="80">工号</th>
					<th width="80">心理老师</th>
					<th width="80">照片</th>
					<th width="50">性别</th>		
					<th width="80">登录密码</th>
					<th width="120">专业技能</th>
					<th width="75">详细</th>
					<th width="120">手机号</th>
					<th width="50">基础操作</th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${teacherList}" var="u" >
				<tr class="text-c">				
				<td>${u.tno }</td>
				<td>${u.name }</td>
					<td><img src="<%=path%>/upload/${u.imgUrl }" style="width:60px;height: 80px;"></td>
				<td>${u.sex }</td>	
				<td>${u.pwd }</td>
				<td>${u.major }</td>
				<td>${u.detail }</td>
				<td>${u.phone }</td>
				<td class="f-14 td-manage">
					<c:if test="${type==1}">
						<a class="btn btn-success-outline  size-MINI radius" href='YyzxServlet?action=toAddYyzx&tname=${u.name}' >预约咨询</a>
					</c:if>
					<c:if test="${type==3}">
					<a style="text-decoration:none" class="ml-5" href="TeacherServlet?action=query&id=${u.id}" onclick="Hui_admin_tab(this)"   title="编辑" ><i class="Hui-iconfont">&#xe6df;</i></a>
					<a style="text-decoration:none" class="ml-5" onClick="del_teacher(this,'${u.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</c:if>
				</td>

				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>
		<div style="text-align: center">
			 <div class="inline pull-right page">
						<a class="btn btn-secondary-outline  size-MINI radius" href='TeacherServlet?action=list&currentPage=1' >首页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='TeacherServlet?action=list&currentPage=${pageTool.lastPage}'>上一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='TeacherServlet?action=list&currentPage=${pageTool.nextPage}'>下一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius" href='TeacherServlet?action=list&currentPage=${pageTool.pageCount}'>尾页</a>

						&nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
						<span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页

					</div>

                </div>
</div>
</div>
		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_teacher(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/TeacherServlet?action=delete&id="+id;
    }
}

</script>


</body>
</html>