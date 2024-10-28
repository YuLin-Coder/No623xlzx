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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 评论管理 <span class="c-gray en">&gt;</span>评论信息列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">		
        <form action="CommentServlet?action=findByMap" method="post">

			<input type="text" name="content" id="content" placeholder="根据评论内容查询" style="width:250px" class="input-text">
			<button  class="btn btn-success" id="search" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	   </form>
	</div>
	<span class="msg" style="color:red;">${requestScope.msg}</span>


	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">

					<th width="80">评论对象</th>
					<th width="80">评论人</th>
					<th width="80">评论内容</th>
					<th width="50">评论时间</th>

					<th width="50">基础操作</th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${commentList}" var="vo" >
				<tr class="text-c">
					<td><c:if test="${vo.flag==1}">教师</c:if> <c:if test="${vo.flag==2}">帖子</c:if> </td>
				<td>${vo.student.realname }</td>

				<td>${vo.content}</td>
				<td><fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

				<td class="f-14 td-manage">

					<c:if test="${type==2}">
				<c:if test="${flag==1}">
						<a style="text-decoration:none" class="ml-5" href="ReplyServlet?action=toAddTeacherReply&sid=${vo.student.id}" onclick="Hui_admin_tab(this)"   title="回复" >回复学生评价</a>
					</c:if>
						<%--<c:if test="${flag==2}">
							<a style="text-decoration:none" class="ml-5" href="ReplyServlet?action=toAddTieziReply&sid=${vo.student.id}" onclick="Hui_admin_tab(this)"   title="回复" >回复帖子</a>
						</c:if>--%>
					</c:if>
					<c:if test="${type==3}">
					<a style="text-decoration:none" class="ml-5" onClick="del_row(this,'${vo.id }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</c:if>
				</td>

				</tr>

			</c:forEach>

			</tbody>
		</table>
		<br>
		<div style="text-align: center">
			 <div class="inline pull-right page">
						<a class="btn btn-secondary-outline  size-MINI radius" href='CommentServlet?action=list&currentPage=1' >首页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='CommentServlet?action=list&currentPage=${pageTool.lastPage}'>上一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='CommentServlet?action=list&currentPage=${pageTool.nextPage}'>下一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius" href='CommentServlet?action=list&currentPage=${pageTool.pageCount}'>尾页</a>

						&nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
						<span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页

					</div>

                </div>
</div>
</div>
		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_row(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/CommentServlet?action=delete&id="+id;
    }
}

</script>


</body>
</html>