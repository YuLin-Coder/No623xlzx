<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
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
    
      <div class="fly-panel" style="margin-bottom: 0;">

        
      <%--     <td>${vo.noticeName}</td>
                <td>${vo.noticeType}</td>
                <td>${vo.createDate}</td> --%>

        <ul class="fly-list">    
        <c:forEach items="${boardList}" var="data">
          <li>
             <a href="javascript:;" class="fly-avatar">
              <img src="<%=path%>/upload/board.png" />

            </a>
            <h2>
              <a class="layui-badge">公告</a>
              <a href="javascript:;">${data.title }</a>
            </h2>
            <div class="fly-list-info">
              <a href="javascript;" link>
                <cite>${data.content}</cite>
                <!--
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP3</i>
                -->
              </a>
              <span>发布时间：${data.fbsj}</span>
              
             <!--  <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
              <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
             <%--  <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="回答"></i>${t.counts }
              </span>  --%>
            </div>
           <!--  <div class="fly-list-badge">
              <span class="layui-badge layui-bg-black">置顶</span>
              <span class="layui-badge layui-bg-red">精帖</span>
            </div> -->
          </li>
          
          </c:forEach>   
        
        </ul>
        
        <!-- <div class="fly-none">没有相关数据</div> -->
    <div style="text-align: center" >
                    <div class="laypage-main">

                        <a  href='IndexServlet?action=boardList&currentPage=1' >首页</a>

                        <a  href='IndexServlet?action=boardList&currentPage=${pageTool.lastPage}'>上一页</a>

                        <a href='IndexServlet?action=boardList&currentPage=${pageTool.nextPage}'>下一页</a>

                        <a  href='IndexServlet?action=boardList&currentPage=${pageTool.pageCount}'>尾页</a>

                        &nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
                        <span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页
</div>
                    
                </div> 
			
			
    <!--     <div style="text-align: center">
          <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
        </div> -->

      </div>
    </div>
    
<%--        <div class="layui-col-md4">
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