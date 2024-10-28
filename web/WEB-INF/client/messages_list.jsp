<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
<%--<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
    <!--   <li class="layui-hide-xs"><a href="index.jsp">首页</a></li>  -->
    
       <form class="layui-form layui-col-md4" action="NoticeServlet?action=IndexNoticeList" method="post">
      <div class="layui-inline">
      
            <div class="layui-input-inline">
              <input type="text" name="keyword" placeholder="请输入公告名称关键词" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-comm" type="submit">
              搜索
            </button>
     </div>
     </form>
    
      <!-- 用户登入后显示 -->
      <c:if test="${loginUser.userType==1}">
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="TieziServlet?action=myTieziList">我发表的贴</a></li> 
      </c:if>

    </ul> 
         <c:if test="${loginUser.userType==1}">
   <div class="fly-column-right layui-hide-xs"> 
     <!--  <span ><i class="layui-icon"></i></span>  -->
      <a href="addTiezi.jsp" class="layui-btn">发表新帖</a> 
    </div>
    </c:if>  
   <!--  <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;"> 
      <a href="add.html" class="layui-btn">发表新帖</a> 
    </div>  -->
  </div>
</div>--%>

<div class="layui-container">
  <div class="layui-row layui-col-space15">

    <div class="layui-col-md12">
        <div class="fly-panel detail-box" id="flyReply">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                <legend>留言板</legend>
            </fieldset>

            <div class="layui-form layui-form-pane">
                <form action="MessagesServlet?action=add" method="post">
                    <div class="layui-form-item layui-form-text">
                        <a name="comment"></a>
                        <div class="layui-input-block">
                            <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                      <%--  <input type="hidden" name="jid" value="123">--%>
                        <button class="layui-btn" type="submit">留言</button>
                    </div>
                </form>
            </div>

            <ul class="jieda" id="jieda">

<c:forEach items="${messagesList }" var="row">
                <li data-id="111">
                    <a name="item-1111111111"></a>
                    <div class="detail-about detail-about-reply">
                        <a class="fly-avatar" href="">
                            <img src="<%=path%>/upload/${row.student.photo}" alt=" ">
                        </a>
                        <div class="fly-detail-user">
                            <a href="" class="fly-link">
                                <cite>${row.student.realname}</cite>
                            </a>
                        </div>
                        <div class="detail-hits">
                            <span><fmt:formatDate value="${row.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        </div>
                    </div>
                    <div class="detail-body jieda-body photos">
                        <p>${row.content}</p>
                    </div>
                    <div class="jieda-reply">
             <%-- <span class="jieda-zan" type="zan">
               &lt;%&ndash; <i class="iconfont icon-zan"></i>&ndash;%&gt;
               &lt;%&ndash; <em>0</em>&ndash;%&gt;
              </span>--%>
                        <span type="reply">
              <%--  <i class="iconfont icon-svgmoban53"></i>--%>
              <%--  回复--%>
              </span>
                        <div class="jieda-admin">
                            <span type="edit"><%--编辑--%></span>
                            <span type="del"><%--删除--%></span>
                            <span class="jieda-accept" type="accept"><%--采纳--%></span>
                        </div>
                    </div>
                </li>
</c:forEach>
                <!-- 无数据时 -->
                <!-- <li class="fly-none">消灭零回复</li> -->
            </ul>


        </div>
    </div>

    </div>
        

        
        <!-- <div class="fly-none">没有相关数据</div> -->
    <div style="text-align: center" >
                    <div class="laypage-main">

                        <a  href='IndexServlet?action=messagesList&currentPage=1' >首页</a>

                        <a  href='IndexServlet?action=messagesList&currentPage=${pageTool.lastPage}'>上一页</a>

                        <a href='IndexServlet?action=messagesList&currentPage=${pageTool.nextPage}'>下一页</a>

                        <a  href='IndexServlet?action=messagesList&currentPage=${pageTool.pageCount}'>尾页</a>

                        &nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
                        <span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页
</div>
                    
                </div> 
			
			
    <!--     <div style="text-align: center">
          <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
        </div> -->

      </div>
    </div>
    
 <%--       <div class="layui-col-md4">
      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title">本周热议帖子</dt>
        <c:forEach items="${bzryList }" var="b">
        <dd>
          <a href="">${b.title }</a>
          <span><i class="iconfont icon-pinglun1"></i> ${b.counts }</span>
        </dd>
       </c:forEach>
      </dl>

     
    </div>--%>

  </div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp"/>

<script src="<%=path %>/res/layui/layui.js"></script>
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