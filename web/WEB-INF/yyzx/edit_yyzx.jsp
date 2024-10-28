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
	<form action="YyzxServlet?action=update" method="post" class="form form-horizontal" >

		<input type="hidden" value="${yyzx.yid}" name="yid">
		<input type="hidden" value="${student.id}" name="s_id">
		<input type="hidden" value="${type}" name="type">


			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
					<span class="c-red">*</span>
					预约老师2：</label>
				<div class="formControls col-xs-3 col-sm-3">
					<input type="text" class="input-text" value="${yyzx.tname}" placeholder="请输入预约老师" id="tname" name="tname" readonly>
				</div>
				<span class="msg" style="color:red;">${requestScope.msg}</span>
			</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>备注信息：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<textarea name="bz" id="bz" cols="" rows="" class="textarea"  placeholder="请输入备注" >${yyzx.bz}</textarea>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				预约时间：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="date" class="input-text" value="${yyzx.yysj}" placeholder="请输入预约时间" id="yysj" name="yysj">
			</div>
		</div>

		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认修改</button>

				<c:if test="${type==3}">
					<button  class="btn btn-default radius" onclick="window.location.href='YyzxServlet?action=list'" type="button">&nbsp;&nbsp;返回&nbsp;&nbsp;</button>
				</c:if>
			</div>
		</div>
	</form>
</div>
<script>
	$(function () {
		// 给注册绑定单击事件
		$("#sub_btn").click(function () {

			//校验心理老师姓名
			var tnameText = $("#tname").val();
			if (tnameText =="") {
				$("span.msg").text("心理老师姓名不许为空！");
				return false;
			}

			//校验预约时间
			var yysjText = $("#yysj").val();
			if (yysjText =="") {
				$("span.msg").text("预约时间不许为空！");
				return false;
			}

		});
		// 去掉错误信息
		setTimeout(function(){ $(".msg").html("")},10000);
	})






</script>


</body>
</html>