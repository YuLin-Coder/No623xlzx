<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="layui-container ">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8 content detail">
      <div class="fly-panel detail-box">
        <h1>心理老师详情</h1>
     
        <div class="detail-about">
          <a class="fly-avatar" href="javascript:;">
            <img src="upload/${teacher.imgUrl }" >
          </a>
          <div class="fly-detail-user">
            <a href="javascript:;" class="fly-link">
              <cite>${teacher.name }</cite>
             <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
              <i class="layui-badge fly-badge-vip">${teacher.sex}</i>
            </a>
            <span><%--发帖时间：--%><%--${tiezi.fbsj }--%></span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
          <span style="padding-right: 10px; color: #FF7200">工号：${teacher.tno }</span>
       <%--  <c:if test="${loginUser.id==tiezi.u_id }">
            <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="TieziServlet?action=query&id=${tiezi.id }">编辑此贴</a></span>
            </c:if>--%>
          </div>
        </div>
        
        <div class="detail-body photos">
          <table class="layui-table">

            <tbody>

            <tr><td width="70">教师姓名</td><td>${teacher.name }</td></tr>
            <tr><td>工号:</td><td>${teacher.tno }</td></tr>
            <tr><td>性别:</td><td>${teacher.sex }</td></tr>
            <tr> <td>专业:</td><td>${teacher.major }</td></tr>
            <tr> <td>联系方式:</td><td>${teacher.phone }</td></tr>

            <tr><td>详情:</td><td>${teacher.detail }</td>  </tr>
           <%-- <tr><td>发布时间:</td><td>${data.create_date }</td>  </tr>--%>
            <tr><td>操作:</td><td>  <button class="layui-btn layui-btn-norma" id="yuyue" onclick="location.href='YyzxServlet?action=toAddYyzx&tname=${teacher.name}'">预约咨询</button></td>  </tr>
            </tbody>
          </table>
         

   
        </div>
      </div>

      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
          <legend>评价</legend>
        </fieldset>
        <div class="layui-form layui-form-pane">


          <form action="CommentServlet?action=add" method="post">
            <!--  -->
            <input type="hidden" name="t_id" value="${tiezi.id }"/>
            <div class="layui-form-item layui-form-text">
              <a name="comment"></a>
              <div class="layui-input-block">
                <textarea id="content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <input type="hidden" name="teacher_id" value="${teacher.id}">
              <input type="hidden" name="flag" value="1">
              <button class="layui-btn" type="submit">提交</button>
            </div>
          </form>




        </div>
        <ul class="jieda" id="jieda">
        <c:forEach items="${commentList }" var="c">
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="">
                <img src="upload/${c.student.photo }" >
              </a>
              <div class="fly-detail-user">
                <a href="" class="fly-link">
                  <cite>${c.student.realname }</cite>
                  <!-- <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                  <i class="layui-badge fly-badge-vip">VIP3</i>      -->         
                </a>
                
                <span>(评论者)</span>
                <!--
                <span style="color:#5FB878">(管理员)</span>
                <span style="color:#FF9E3F">（社区之光）</span>
                <span style="color:#999">（该号已被封）</span>
                -->
              </div>

              <div class="detail-hits">
                <span>评论时间：<fmt:formatDate value="${c.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
              </div>

              <i class="iconfont icon-caina" title="最佳答案"></i>
            </div>
            <div class="detail-body jieda-body photos">
              <p>${c.content }</p>
            </div>
            <div class="jieda-reply">
             <!--  <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>66</em>
              </span>
              <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span> -->
              <div class="jieda-admin">
              <%-- <c:if test="${loginUser.id==c.u_id }">
                <span type="edit"><a href="CommentServlet?action=query&id=${c.id }&t_id=${tiezi.id}">编辑<!-- </a> --></span>
                <span type="del"><a href="CommentServlet?action=delete&id=${c.id }&t_id=${tiezi.id}">删除</a></span>
                </c:if>--%>
                <!-- <span class="jieda-accept" type="accept">采纳</span> -->
              </div>
            </div>
            </c:forEach>
          </li>
 
        </ul>
      <%--   ${empty param.id ?"确认添加":"确认修改"} --%>

      </div>
    </div>
    
    <div class="layui-col-md4">
      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title">最新帖子</dt>
        <c:forEach items="${tieziList }" var="b">
        <dd>
          <a href="">${b.title }</a>
          <span><i class="iconfont icon-pinglun1"></i> ${b.counts }</span>
        </dd>
       </c:forEach>
      </dl>

     
    </div>
  </div>
</div>

  </div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>

<script src="res/layui/layui.js"></script>
<script>


layui.config({
  version: "3.0.0"
  ,base: 'res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>


</body>
</html>