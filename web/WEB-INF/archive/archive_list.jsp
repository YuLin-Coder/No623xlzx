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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 学生档案管理 <span class="c-gray en">&gt;</span>学生档案信息列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">		
        <form action="ArchiveServlet?action=findByMap" method="post">

			<input type="text" name="jg" id="jg" placeholder="根据籍贯查询" style="width:250px" class="input-text">
			<button  class="btn btn-success" id="search" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	   </form>
	</div>
	<span class="msg" style="color:red;">${requestScope.msg}</span>
	<c:if test="${type==3}">
	<%--<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">

	&lt;%&ndash;<a class="btn btn-primary radius" title="添加教师信息"  onclick="Hui_admin_tab(this)" href="ArchiveServlet?action=toAddTeacher"><i class="Hui-iconfont">&#xe600;</i> 添加心理老师</a>&ndash;%&gt;

	</span> --%>
	</div>
	</c:if>

	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					
					<th width="80">学生姓名</th>
					<th width="80">性别</th>
					<th width="80">照片</th>
					<th width="80">民族</th>
					<th width="80">籍贯</th>
					<th width="50">爸爸姓名</th>
					<th width="80">妈妈姓名</th>
					<th width="120">家庭联系方式</th>
					<th width="75">备注</th>
					<th width="120">毕业高中</th>

					<th width="50">基础操作</th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${archiveList}" var="u" >
				<tr class="text-c">				
				<td>${u.student.realname}</td>
				<td>${u.student.sex }</td>
					<td><img src="/upload/${u.student.photo }" style="width:60px;height: 80px;"></td>
				<td>${u.nation }</td>
				<td>${u.jg }</td>
				<td>${u.fname }</td>
				<td>${u.mname }</td>
				<td>${u.phone }</td>
					<td>${u.note }</td>
					<td>${u.graduate }</td>

				<td class="f-14 td-manage">


					<c:if test="${type==3}">
					<a style="text-decoration:none" class="ml-5" onClick="del_row(this,'${u.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</c:if>
					<a href="IndexServlet?action=downloadFile&filename=${archive.filename}" class="layui-btn layui-btn-sm layui-btn-normal">下载档案</a>
				</td>

				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>
		<div style="text-align: center">
			 <div class="inline pull-right page">
						<a class="btn btn-secondary-outline  size-MINI radius" href='ArchiveServlet?action=list&currentPage=1' >首页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='ArchiveServlet?action=list&currentPage=${pageTool.lastPage}'>上一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='ArchiveServlet?action=list&currentPage=${pageTool.nextPage}'>下一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius" href='ArchiveServlet?action=list&currentPage=${pageTool.pageCount}'>尾页</a>

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
        location.href="${pageContext.request.contextPath}/ArchiveServlet?action=delete&id="+id;
    }
}

</script>


</body>
</html>