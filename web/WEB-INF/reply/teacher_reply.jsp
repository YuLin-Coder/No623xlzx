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
	<form action="ReplyServlet?action=addTeacherReply" method="post" class="form form-horizontal" >


		<input type="hidden" value="${sid}" name="sid">
	<%--	<input type="hidden" value="${type}" name="type">--%>
		<input type="hidden" value="${teacher.id}" name="teacher_id">

		<input type="hidden" value="1" name="flag">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>回复学生评价信息：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<textarea name="content" id="content" cols="" rows="" class="textarea"  placeholder="请输入回复学生评价内容" ></textarea>
			</div>
		</div>


		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认回复</button>


			</div>
		</div>
	</form>
</div>
<script>
	$(function () {
		// 给注册绑定单击事件
		$("#sub_btn").click(function () {

			//校验心理老师姓名
			var replyText = $("#content").val();
			if (replyText =="") {
				$("span.msg").text("回复内容不许为空！");
				return false;
			}



		});
		// 去掉错误信息
		setTimeout(function(){ $(".msg").html("")},10000);
	})






</script>


</body>
</html>