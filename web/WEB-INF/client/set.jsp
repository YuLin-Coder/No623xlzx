<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>



<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
  

    <li class="layui-nav-item layui-this">
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

    <li class="layui-nav-item ">
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
      <a href="CommentServlet?action=myMessagesList">
        <i class="layui-icon">&#xe609;</i>
        我的留言
      </a>
    </li>

    <li class="layui-nav-item">
      <a href="<%=path%>/LoginServlet?action=loginOut">
        <i class="layui-icon">&#xe609;</i>
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
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">我的资料</li>
  <%--      <li lay-id="avatar">头像</li>
        <li lay-id="pass">密码</li>--%>
     
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
          <form method="post" enctype="multipart/form-data" action="StudentServlet?action=update"  >
            <input type="hidden" name="id" value="${student.id}"  />
            <div class="layui-form-item">
              <label for="stuno" class="layui-form-label">学号</label>
              <div class="layui-input-inline">
                <input type="text" id="stuno" name="stuno" required autocomplete="off" value="${student.stuno }" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
              <label for="realname" class="layui-form-label">姓名</label>
              <div class="layui-input-inline">
                <input type="text" id="realname" name="realname" required lay-verify="required" autocomplete="off" value="${student.realname }" class="layui-input">
              </div>
              <div class="layui-inline">
                <div class="layui-input-inline">
                  <input type="radio" name="sex" value="男" <c:if test="${student.sex=='男' }">checked</c:if> title="男">
                  <input type="radio" name="sex" value="女" <c:if test="${student.sex=='女' }">checked</c:if>  title="女">
                </div>
              </div>
            </div>
             <div class="layui-form-item">
              <label for="pwd" class="layui-form-label">密码</label>
              <div class="layui-input-inline">
                <input type="text" id="pwd" name="pwd" required autocomplete="off" value="${student.pwd }" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-form-item">
              <label for="major" class="layui-form-label">专业</label>
              <div class="layui-input-inline">
                <input type="text" id="major" name="major" autocomplete="off" value="${student.major }" class="layui-input">
              </div>
            </div>

            <div class="layui-form-item">
              <label for="bj" class="layui-form-label">班级</label>
              <div class="layui-input-inline">
                <input type="text" id="bj" name="bj" autocomplete="off" value="${student.bj }" class="layui-input">
              </div>
            </div>

           <%-- <div class="layui-form-item">
              <label class="layui-form-label">照片</label>
              <div class="layui-input-inline ">

              &lt;%&ndash;    <img class="layui-upload"  src="<%=path%>/${student.photo}" >&ndash;%&gt;

                <img src="<%=path%>/upload/${student.photo }" width="70px" height="100px" id="preview_img">
              </div>
              <input type="file" name="photo" id="photo" />
          &lt;%&ndash;    <input type="hidden" name="photo" value="${student.photo}"  />&ndash;%&gt;
            </div>
--%>
            <div class="layui-form-item">
              <label class="layui-form-label">照片</label>
              <div class="layui-input-inline uploadHeadImage">
                <div class="layui-upload-drag" id="headImg">
                  <img class="layui-upload-img headImage" src="<%=path%>/upload/${student.photo }" id="preview_img" style="width:100px;height:100%">
               <%--   <p id="demoText"></p>
                  <p>点击上传图片</p>--%>
                </div>
              </div>

              <input type="file" name="photo" id="photo"  />
            </div>
            <%--<div class="layui-form-item layui-form-text">
              <label for="userText" class="layui-form-label">个性签名</label>
              <div class="layui-input-block">
                <textarea placeholder="随便写些什么刷下存在感" id="userText"  name="userText" autocomplete="off" class="layui-textarea" style="height: 80px;">${user.userText }</textarea>
              </div>
            </div>--%>
            <div class="layui-form-item">
              <button class="layui-btn" type="submit" >确认修改</button>
            </div>
          </div>
          
        <%--  <div class="layui-form layui-form-pane layui-tab-item">
            <div class="layui-form-item">
              <div class="avatar-add">
                <p>建议尺寸168*168，支持jpg、png、gif，最大不能超过50KB</p>
            <form action="AuthServlet?action=updateMyTx" enctype="multipart/form-data"  method="post">             
                <input type="file" id="tx" name="tx"   class="layui-input">               
                <button type="button" class="layui-btn upload-img" type="submit">
                  <i class="layui-icon">&#xe67c;</i>上传头像
                </button>
                <img src="upload/${user.tx }" id="preview_img">
                </form>
                <span class="loading"></span>
              </div>
            </div>
          </div>
          
          <div class="layui-form layui-form-pane layui-tab-item">
            <form action="AuthServlet?action=updateMyPwd" method="post" onsubmit="return checkPwd()">
              <div class="layui-form-item">
                <label for="oldpwd" class="layui-form-label">当前密码</label>
                <div class="layui-input-inline">
                  <input type="text" id="oldpwd" name="oldpwd" value="${user.password }" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="newpass" class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="newpass" name="newpass" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="repass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn" key="set-mine" type="submit">确认修改</button>
              </div>
            </form>
          </div>--%>
          
       
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


	 
$("#photo").change(function () {
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
