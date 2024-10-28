<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>




<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item ">
      <a href="StudentServlet?action=findMyInfo">
        <i class="layui-icon">&#xe620;</i>
      个人信息
      </a>
    </li>


    <li class="layui-nav-item">
      <a href="ArchiveServlet?action=myArchiveList">
        <i class="layui-icon">&#xe609;</i>
        档案管理
      </a>
    </li>

    <li class="layui-nav-item">
      <a href="YyzxServlet?action=findMyYyzx&s_id=${student.id}">
        <i class="layui-icon">&#xe609;</i>
        我的预约
      </a>
    </li>


    <li class="layui-nav-item">
      <a href="ReplyServlet?action=myReplyList">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>


    <li class="layui-nav-item ">
      <a href="CommentServlet?action=myCommentList">
        <i class="layui-icon">&#xe609;</i>
        我的评论
      </a>
    </li>

    <li class="layui-nav-item layui-this">
      <a href="MessagesServlet?action=myMessagesList">
        <i class="layui-icon">&#xe609;</i>
       我的留言
      </a>
    </li>

    <li class="layui-nav-item">
      <a href="<%=path%>/LoginServlet?action=loginOut">
        <i class="layui-icon ">&#xe609;</i>
        退出
      </a>
    </li>


  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  
  <div class="fly-panel fly-panel-user" pad20>
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" lay-id="index" class="layui-this">我的留言</li>
     
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">

          <table class="layui-table">
            <thead>
            <tr>

              <th>留言内容</th>
              <th>留言时间</th>

              <th style="text-align: center;">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messagesList}" var="vo">
              <tr>

                <td>${vo.content}</td>

                <td>  <fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                <th style="text-align: center;">
                  <a href="MessagesServlet?action=deleteMyMessages&id=${vo.id}" class="layui-btn layui-btn-sm layui-btn-danger">删除</a>

                </th>
              </tr>
            </c:forEach>
            </tbody>
          </table>
         <%-- <ul class="mine-view jie-row">
           <c:forEach items="${commentList}" var="vo">
            <li>
              <a href="TieziServlet?action=detail&id=${vo.tiezi.id}" target="_blank">标题：${vo.tiezi.title }</a>
             <i>评论时间:<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></i>
              <p>&emsp;评论内容：${vo.content }</p>
               <em><a class="mine-edit" href="CommentServlet?action=deleteMyComment&id=${vo.id }">删除</a>
               </em>

            </li>
           </c:forEach>
          </ul>
          <div id="LAY_page"></div>--%>
        </div>
   
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
<script src="<%=path %>/res/layui/layui.js"></script>


<script>
function checkPwd() {
    //根据ID获取值
    var newpass = document.getElementById("newpass").value;
    var repass = document.getElementById("repass").value;
    if (password == "") {
        alert("新密码不能为空!");
      
        return false;
    }
    if (repass == "") {
        alert("重复密码不允许为空");
       
        return false;
    } 
    if (newpass!=repass) {
        alert("两次密码不一致"+newpass+"-"+repass);
     
        return false;
    }
    return true;
}
layui.use([ 'form','jquery','layer' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 $= layui.jquery;
	 form.render();//这句一定要加，占坑
	 
$("#tx").change(function () {
    //创建blob对象，浏览器将文件放入内存中，并生成标识
    var img_src = URL.createObjectURL($(this)[0].files[0]);
    //给img标检的src赋值
    document.getElementById("preview_img").src=img_src;
    //URL.revokeObjectURL(img_src);// 手动 回收，
});
});
layui.config({
  version: "2.0.0"
  ,base: 'res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>
