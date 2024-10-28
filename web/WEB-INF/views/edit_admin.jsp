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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span>个人信息维护· <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form action="AdminServlet?action=update" method="post"  class="form form-horizontal" id="form-user-add">


		<input type="hidden" value="${admin.id}" name="id">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				用户名：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${record.username}"  name="username" readonly>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				密&emsp;码：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${record.pwd}" placeholder="请输入密码" id="pwd" name="pwd">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				昵&emsp;称：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${record.nickname}" placeholder="请输入昵称" id="nickname" name="nickname">
			</div>
		</div>
			<span class="msg" style="color:red;">${requestScope.msg}</span>
		</div>
		

		
		
		
		
		
  

		
		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认修改</button>

			<button  class="btn btn-default radius" onclick="window.location.href='LoginServlet?action=toConsole'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<script>
$(function () {
    // 给添加绑定单击事件
    $("#sub_btn").click(function () {       
      
        //文件
         var nicknameText = $("#nickname").val();
        if (nicknameText =="") {
            $("span.msg").text("昵称不许为空！");
            return false;
        }
   
        var pwdText = $("#pwd").val();
        if (pwdText =="") {
            $("span.msg").text("密码不许为空！");
            return false;
        }
     // 去掉错误信息
    });
    setTimeout(function(){ $(".msg").html("")},10000);
})


</script>

</body>
</html>