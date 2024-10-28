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
	<form action="StudentServlet" method="post" class="form form-horizontal" >
		<input type="hidden" value="${empty param.id ?"add":"update"}" name="action"/>
		<input type="hidden" value="${student.id}" name="id">
		<input type="hidden" value="${type}" name="type">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				学&emsp;&emsp;号：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${student.stuno}" placeholder="请输入学号" id="stuno" name="stuno">
			</div>
			<span class="msg" style="color:red;">${requestScope.msg}</span>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				登录密码：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${student.pwd}" placeholder="请输入密码" id="pwd" name="pwd">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				学生姓名：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${student.realname}" placeholder="请输入学生姓名" id="realname" name="realname">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				性&emsp;&emsp;别：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<select name="sex" class="select">
					<option value="男" <c:if test="${student.sex=='男'}">selected</c:if>> 男</option>
					<option value="女" <c:if test="${student.sex=='女'}">selected</c:if>>女</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				专&emsp;&emsp;业：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${student.major}" placeholder="请输入专业" id="major" name="major">
			</div>
		</div>


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				班&emsp;&emsp;级：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${student.bj}" placeholder="请输入班级" id="bj" name="bj">
			</div>
		</div>



		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >${empty param.id ?"确认添加":"确认修改"}</button>

				<c:if test="${type==3}">
					<button  class="btn btn-default radius" onclick="window.location.href='StudentServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</c:if>
			</div>
		</div>
	</form>
</div>
<script>
	$(function () {
		// 给注册绑定单击事件
		$("#sub_btn").click(function () {
			//校验学号
			var stunoText = $("#stuno").val();
			if (stunoText =="") {
				$("span.msg").text("学号不许为空！");
				return false;
			}

			//校验密码
			var pwdText = $("#pwd").val();
			//2 创建正则表达式对象
			var pwdPatt = /^\w{5,12}$/;
			//3 使用test方法验证
			if (!pwdPatt.test(pwdText)) {
				//4 提示用户结果
				$("span.msg").text("密码至少6位！");

				return false;
			}
			//姓名
			var realnameText = $("#realname").val();
			if (realnameText =="") {
				$("span.msg").text("学生姓名不许为空！");
				return false;
			}

			//校验专业
			var majorText = $("#major").val();
			if (majorText =="") {
				$("span.msg").text("专业不许为空！");
				return false;
			}

			//校验班级
			var bjText = $("#bj").val();
			if (bjText =="") {
				$("span.msg").text("班级不许为空！");
				return false;
			}



		});
		// 去掉错误信息
		setTimeout(function(){ $(".msg").html("")},10000);
	})






</script>


</body>
</html>