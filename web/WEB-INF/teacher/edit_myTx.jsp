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
	<form action="TeacherServlet?action=updateTx" method="post" enctype="multipart/form-data"  class="form form-horizontal" >

		<input type="hidden" value="${teacher.id}" name="id">

		<img src="upload/${teacher.imgUrl}" id="preview_img" width="70px" height="70px" alt="">

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

				<span class="msg" style="color:red;">${requestScope.msg}</span>
			</div>








		<div class="row cl">
			<div class="col-9 col-offset-2">
				<button class="btn btn-primary radius"  id="sub_btn" type="submit" >确认修改</button>

					<button  class="btn btn-default radius" onclick="window.location.href='TeacherServlet?action=toEditTeacherInfo&id=${teacher.id}'" type="button">&nbsp;&nbsp;取消修改&nbsp;&nbsp;</button>

			</div>
		</div>
	</form>
</div>
<script>
	$(function () {
		$("#file").change(function () {
			//创建blob对象，浏览器将文件放入内存中，并生成标识
			var img_src = URL.createObjectURL($(this)[0].files[0]);
			//给img标检的src赋值
			document.getElementById("preview_img").src = img_src;
			//URL.revokeObjectURL(img_src);// 手动 回收，
		});
	});

</script>


</body>
</html>