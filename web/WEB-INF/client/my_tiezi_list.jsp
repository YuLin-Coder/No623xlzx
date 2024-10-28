<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
   
      <div class="fly-panel" style="margin-bottom: 0;">
        
       

        <ul class="fly-list">    
        <c:forEach items="${tieziList}" var="t">
          <li>
            <a href="user/home.html" class="fly-avatar">
              <img src="<%=path%>/upload/${t.student.photo}"  >
            </a>
            <h2>
              <a class="layui-badge">帖子</a>
              <a href="TieziServlet?action=detail&id&id=${t.id}">${t.title }</a>
            </h2>
            <div class="fly-list-info">
              <a href="TieziServlet?action=detail&id&id=${t.id}" link>
                <cite>发帖人：${t.student.realname }</cite>
                <!--
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP3</i>
                -->
              </a>
              <span>发帖时间：<fmt:formatDate value="${t.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
              
            <%--  <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
              <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
              <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="回答"></i>${t.counts }
              </span> --%>
            </div>
            <div class="fly-list-badge">
              <span class="layui-badge layui-bg-black">置顶</span>
                <span class="layui-badge layui-bg-red"><a href="TieziServlet?action=query&id=${t.id}">编辑</a></span>
            </div>
          </li>
          
          </c:forEach>   
        
        </ul>
        
        <!-- <div class="fly-none">没有相关数据</div> -->
       <%--   共${tsnum}条记录，当前${cpage}/${tpage}页,  <a href="BannerServlet?action=list&cp=1${searchParams}">首页</a>
                     
                      <a  href="BannerServlet?action=list&cp=${cpage-1<1?1:cpage-1}${searchParams}">上一页</a>
                    
                           <a  href="BannerServlet?action=list&cp=${cpage+1>tpage?tpage:cpage+1}${searchParams}">下一页</a>
                             <a  href="BannerServlet?action=list&cp=${tpage}${searchParams}">尾页</a> --%>
        
<%--    <div style="text-align: center" >
        <div class="laypage-main">
            <a  href='IndexServlet?action=tieziList&currentPage=1' >首页</a>

            <a  href='IndexServlet?action=tieziList&currentPage=${pageTool.lastPage}'>上一页</a>

            <a href='IndexServlet?action=tieziList&currentPage=${pageTool.nextPage}'>下一页</a>

            <a  href='IndexServlet?action=tieziList&currentPage=${pageTool.pageCount}'>尾页</a>

            &nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
            <span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页


        </div>
                    
                </div> --%>
			
			
    <!--     <div style="text-align: center">
          <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
        </div> -->

      </div>
    </div>
    
   <%--     <div class="layui-col-md4">
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

<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>

<script src="<%=path %>/res/layui/layui.js"></script>
<script>
layui.use(['form', 'jquery'], function(args){
	  var form = layui.form;
	  $=layui.jquery;
	  form.render()
	  
	  //……
	  
	});    
layui.config({
  version: "3.0.0"
  ,base: 'res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>