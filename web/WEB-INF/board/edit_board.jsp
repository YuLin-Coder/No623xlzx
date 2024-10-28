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
	<form action="BoardServlet" method="post" class="form form-horizontal" >
		<input type="hidden" value="${empty param.id ?"add":"update"}" name="action"/>
		<input type="hidden" value="${board.id}" name="id">
		<input type="hidden" value="${type}" name="type">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				公告标题：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${board.title}" placeholder="请输入公告标题" id="title" name="title">
			</div>
			<span class="msg" style="color:red;">${requestScope.msg}</span>
		</div>

		<div class="row cl">

			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公告内容：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<textarea name="content" id="content" cols="" rows="" class="textarea"  placeholder="请输入公告内容" >${board.content}</textarea>

			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				编&nbsp;辑者：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${board.editor}" placeholder="请输入编辑者" id="editor" name="editor">
			</div>
		</div>





		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >${empty param.id ?"确认添加":"确认修改"}</button>

				<c:if test="${type==3}">
					<button  class="btn btn-default radius" onclick="window.location.href='BoardServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</c:if>
			</div>
		</div>
	</form>
</div>
<script>
	$(function () {
		// 给注册绑定单击事件
		$("#sub_btn").click(function () {

			//校验公告标题
			var titleText = $("#title").val();
			if (titleText =="") {
				$("span.msg").text("公告标题不许为空！");
				return false;
			}
			//公告内容
			var contentText = $("#content").val();
			if (contentText =="") {
				$("span.msg").text("公告聂荣不许为空！");
				return false;
			}

			//校验编辑者
			var editorText = $("#editor").val();
			if (editorText =="") {
				$("span.msg").text("编辑者不许为空！");
				return false;
			}




		});
		// 去掉错误信息
		setTimeout(function(){ $(".msg").html("")},10000);
	})






</script>


</body>
</html>