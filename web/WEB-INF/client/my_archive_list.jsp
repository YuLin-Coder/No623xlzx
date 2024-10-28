<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>



<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
  

    <li class="layui-nav-item">
        <a href="StudentServlet?action=findMyInfo">
            <i class="layui-icon">&#xe620;</i>
            个人信息
        </a>
    </li>

      <li class="layui-nav-item layui-this">
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


      <li class="layui-nav-item ">
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
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">上传档案</li>
        <li lay-id="avatar">我的档案</li>


      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
            <form action="${pageContext.request.contextPath}/ArchiveServlet?action=add" enctype="multipart/form-data"  method="post">
                <input type="hidden" name="sid" value="${student.id}">
                <div class="layui-form-item">
                            <label for="nation" class="layui-form-label">民族</label>
                            <div class="layui-input-inline">
                              <input type="text" id="nation" name="nation" required  autocomplete="off" class="layui-input">
                            </div>
                           <%-- <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>--%>
                </div>



                <div class="layui-form-item">
                    <label for="jg" class="layui-form-label">籍贯</label>
                    <div class="layui-input-inline">
                        <input type="text" id="jg" name="jg" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="jg" class="layui-form-label">父亲姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="fname" name="fname" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="jg" class="layui-form-label">妈妈姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="mname" name="mname" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="jg" class="layui-form-label">家庭联系方式</label>
                    <div class="layui-input-inline">
                        <input type="text" id="phone" name="phone" required lay-verify="phone" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="jg" class="layui-form-label">毕业院校</label>
                    <div class="layui-input-inline">
                        <input type="text" id="graduate" name="graduate" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>


                <!--   <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                      <input type="password" id="reoass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                  </div> -->

             <%--   <div class="layui-form-item">
                    <label for="sex" class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <select name="sex" id="sex">
                            <option value="男" selected>男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                </div>--%>

               <%-- <div class="layui-form-item">
                    <label for="characters" class="layui-form-label">宠物特征</label>
                    <div class="layui-input-inline">
                        <textarea type="text" name="characters" id="characters" placeholder="宠物特征" class="layui-textarea"></textarea>

                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="characters" class="layui-form-label">宠物详情：</label>
                    <div class="layui-input-inline">
                        <textarea type="text" name="detail" id="detail" placeholder="宠物详情" class="layui-textarea"></textarea>

                    </div>
                </div>--%>

                <div class="layui-form-item">
                    <label for="note" class="layui-form-label">备注：</label>
                    <div class="layui-input-inline">
                        <textarea type="text" name="note" id="note" placeholder="备注" class="layui-textarea"></textarea>

                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">档案</label>
                    <div class="layui-input-inline">

                    <input type="file" name="fj" id="fj"  />
                    </div>
                </div>




                <div class="layui-form-item">
                    <button class="layui-btn" type="submit">立即添加</button>
                </div>

            </form>
          </div>


        <div class="layui-form layui-form-pane layui-tab-item">
<c:if test="${archive==null}">
    //暂时没有档案信息，请及时上传
</c:if>
<c:if test="${archive!=null}">
              <table class="layui-table">
                  <thead>
                  档案信息
                 <%-- <tr>
                      <th>宠物名称</th>
                      <th>宠物类别</th>
                      <th>宠物图片</th>
                      <th>发布者</th>
                      <th>发布时间</th>
                      <th style="text-align: center;">操作</th>
                  </tr>--%>
                  </thead>
                  <tbody>
                  <tr>
                      <td>姓名</td>
                      <td>${student.realname}</td>
                  </tr>
                  <tr>
                      <td>民族</td>
                      <td>${archive.nation}</td>
                  </tr>
                  <tr>
                      <td>父亲名字</td>
                      <td>${archive.fname}</td>
                  </tr>
                  <tr>
                      <td>母亲名字</td>
                      <td>${archive.mname}</td>
                  </tr>
                  <tr>
                      <td>家庭联系方式</td>
                      <td>${archive.phone}</td>
                  </tr>
                  <tr>
                      <td>备注</td>
                      <td>${archive.phone}</td>
                  </tr>
                      <tr>
                          <td>操作</td>
                          <td>
                              <a href="ArchiveServlet?action=delete2&id=${archive.id}" class="layui-btn layui-btn-sm layui-btn-danger">删除</a>
                              <a href="IndexServlet?action=downloadFile&filename=${archive.filename}" class="layui-btn layui-btn-sm layui-btn-normal">下载档案</a>
                          </td>
                      </tr>

                  </tbody>
              </table>
</c:if>
          </div>



        </div>

      </div>
    </div>
  </div>
</div>




<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
<script src="<%=path %>/res/layui/layui.js"></script>

<script>

layui.use([ 'form','jquery','layer','upload' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 $= layui.jquery,
            upload=layui.upload;
	 form.render();//这句一定要加，占坑





/*
$("#tx").change(function () {
    //创建blob对象，浏览器将文件放入内存中，并生成标识
    var img_src = URL.createObjectURL($(this)[0].files[0]);
    //给img标检的src赋值
    document.getElementById("preview_img").src=img_src;
    //URL.revokeObjectURL(img_src);// 手动 回收，
});*/
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
