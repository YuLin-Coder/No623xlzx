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


    <li class="layui-nav-item layui-this">
      <a href="ReplyServlet?action=myReplyList">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>
    
      <li class="layui-nav-item">
      <a href="CommentServlet?action=myCommentList">
        <i class="layui-icon">&#xe609;</i>
        我的评论
      </a>
    </li>

    <li class="layui-nav-item ">
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
    <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
      <%--<button class="layui-btn layui-btn-danger" id="LAY_delallmsg">清空全部消息</button>--%>
      <div  id="LAY_minemsg" style="margin-top: 10px;">
        <!--<div class="fly-none">您暂时没有最新消息</div>-->
        <ul class="mine-msg">
<c:forEach items="${replyList}" var="vo">
          <li data-id="123">
            <blockquote class="layui-elem-quote">
              <a href="javascript:;" target="_blank"><cite>${vo.teacher.name}</cite></a>回复了您的问题<c:if test="${vo.flag==1}">教师评价</c:if> <c:if test="${vo.flag==2}">帖子</c:if><c:if test="${vo.flag==3}">留言</c:if><cite>${vo.content}</cite>
            </blockquote>
            <p><span>回复时间：<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span><a href="ReplyServlet?action=delete2&id=${vo.id}" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a></p>
          </li>
</c:forEach>
          <%--<li data-id="123">
            <blockquote class="layui-elem-quote">
              <a href="/jump?username=Absolutely" target="_blank"><cite>Absolutely</cite></a>回答了您的求解<a target="_blank" href="/jie/8153.html/page/0/#item-1489505778669"><cite>layui后台框架</cite></a>
            </blockquote>
            <p><span>回复时间：<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span><a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a></p>
          </li>
          <li data-id="123">
            <blockquote class="layui-elem-quote">
              系统消息：欢迎使用 layui
            </blockquote>
            <p><span>1小时前</span><a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a></p>
          </li>--%>
        </ul>
      </div>
    </div>
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
