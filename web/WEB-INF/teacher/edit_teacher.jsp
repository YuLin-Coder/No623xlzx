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
	<form action="TeacherServlet?action=update" method="post" enctype="multipart/form-data"  class="form form-horizontal" >

		<input type="hidden" value="${teacher.id}" name="id">
	<%--	<input type="hidden" value="${sessionScope.type}" name="type">--%>
	<%--	Teacher(Integer id, String tno, String pwd, String name, String sex, String major, String detail, String phone)--%>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				工&emsp;&emsp;号：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${teacher.tno}" placeholder="请输入工号" id="tno" name="tno">
			</div>
			<span class="msg" style="color:red;">${requestScope.msg}</span>
		</div>


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				上传照片：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<span class="btn-upload form-group">
  <input class="input-text upload-url radius" type="text" style="width:260px;" name="uploadfile-1" id="uploadfile-1" readonly>&emsp;<a href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe63e;</i> 上传照片</a>
  <input type="file" multiple name="file" id="file"  class="input-file">
</span>


			</div>


		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				登录密码：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${teacher.pwd}" placeholder="请输入密码" id="pwd" name="pwd">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				教师姓名：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${teacher.name}" placeholder="请输入教师姓名" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				性&emsp;&emsp;别：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<select name="sex" class="select">
					<option value="男" <c:if test="${teacher.sex=='男'}">selected</c:if>>男</option>
					<option value="女" <c:if test="${teacher.sex=='女'}">selected</c:if>>女</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				专业技能：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${teacher.major}" placeholder="请输入专业技能" id="major" name="major">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				手机号：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${teacher.phone}" placeholder="请输入手机号" id="phone" name="phone">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				详&emsp;&emsp;细：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<textarea name="detail" id="detail" cols="" rows="" class="textarea"  placeholder="请输入详细信息" >${teacher.detail}</textarea>
			</div>
		</div>






		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认修改</button>

				<c:if test="${type==3}">
					<button  class="btn btn-default radius" onclick="window.location.href='TeacherServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</c:if>
			</div>
		</div>
	</form>
</div>
<script>
	$(function () {

		$("#sub_btn").click(function () {
			//校验工号
			var tnoText = $("#tno").val();
			if (tnoText =="") {
				$("span.msg").text("工号不许为空！");
				return false;
			}

			var nameText = $("#file").val();
			if (nameText =="") {
				$("span.msg").text("心理老师招聘不许为空！");
				return false;
			}

			// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
			//1 获取用户名输入框里的内容
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
			var nameText = $("#name").val();
			if (nameText =="") {
				$("span.msg").text("心理老师姓名不许为空！");
				return false;
			}


			//校验专业
			var majorText = $("#major").val();
			if (majorText =="") {
				$("span.msg").text("专业技能不许为空！");
				return false;
			}

			//校验班级
			var bjText = $("#bj").val();
			if (bjText =="") {
				$("span.msg").text("专业不许为空！");
				return false;
			}
			//校验手机号
			var phone =  $("#phone").val();
			if(!(/^1[34578]\d{9}$/.test(phone))){
				$("span.msg").text("请输入正确的电话号码格式");
				return false;
			}



		});
		// 去掉错误信息
		setTimeout(function(){ $(".msg").html("")},10000);
	})






</script>


</body>
</html>