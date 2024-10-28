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
        <form action="YyzxServlet?action=findByMap" method="post">

			<input type="date" name="yysj" id="yysj" placeholder="根据预约时间" style="width:250px" class="input-text">
			<button  class="btn btn-success" id="search" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	   </form>
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
			<c:forEach items="${yyzxList}" var="u" >
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
		<div style="text-align: center">
			 <div class="inline pull-right page">
						<a class="btn btn-secondary-outline  size-MINI radius" href='YyzxServlet?action=list&currentPage=1' >首页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='YyzxServlet?action=list&currentPage=${pageTool.lastPage}'>上一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius"href='YyzxServlet?action=list&currentPage=${pageTool.nextPage}'>下一页</a>

						<a class="btn btn-secondary-outline  size-MINI radius" href='YyzxServlet?action=list&currentPage=${pageTool.pageCount}'>尾页</a>

						&nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
						<span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页

					</div>

                </div>
</div>
</div>
		
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
function del_yyzx(obj,id){
	 //用户安全提示
    if(confirm("您确定要删除该条记录吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/YyzxServlet?action=delete&yid="+id;
    }
}

</script>


</body>
</html>