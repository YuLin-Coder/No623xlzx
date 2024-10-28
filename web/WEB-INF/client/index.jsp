<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>



<%--<div class="fly-case-header">
    <p class="fly-case-year">2017</p>
    <a href="/case/{{ year }}/">
        <img class="fly-case-banner" src="../../res/images/case.png" alt="发现 Layui 年度最佳案例">
    </a>
    <div class="fly-case-btn">
        <a href="javascript:;" class="layui-btn layui-btn-big fly-case-active" data-type="push">提交案例</a>
        <a href="" class="layui-btn layui-btn-primary layui-btn-big">我的案例</a>

        <a href="http://fly.layui.com/jie/11996/" target="_blank" style="padding: 0 15px; text-decoration: underline">案例要求</a>
    </div>
</div>--%>

<div class="fly-main" style="overflow: hidden;">
    <form action="/client/toIndex.action" method="post" id="searchForm">
        <input type="hidden" name="pageNum" id="pageNum">
   <%-- <div class="fly-tab-border fly-case-tab">
    <span>
      <a href="" class="tab-this">2017年度</a>
      <a href="">2016年度</a>
    </span>
    </div>--%>
    </form>
    <div class="layui-tab layui-tab-brief">
        <ul class="layui-tab-title">
            <li class="layui-this"><a href="">心理老师列表</a></li>
          <%--  <li><a href="">按点赞排行</a></li>--%>
        </ul>
    </div>

    <ul class="fly-case-list">
        <c:if test="${requestScope.teacherList!=null}">
            <c:forEach items="${requestScope.teacherList}" var="row">
        <li data-id="123">
            <a class="fly-case-img" href="<%=path%>/TeacherServlet?action=detail&id=${row.id}" target="_blank" >
                <img src="${pageContext.request.contextPath}/upload/${row.imgUrl}" style="width: 240px;height: 260px;" >
                <%--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>--%>
            </a>
         <%--   <h2><a href="http://fly.layui.com/" target="_blank">${c.name}</a></h2>--%>
            <p class="fly-case-desc">${row.detail}</p>
          <%--  <p>${row.detail}</p>--%>
            <div class="fly-case-info">
                <a href="<%=path%>/client/toPetDetail.action/${c.id}" class="fly-case-user" target="_blank"><img src="${pageContext.request.contextPath}/upload/${row.imgUrl}"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">${row.name}</span>&emsp;性别：${row.sex} </p>
                <p>${row.phone}</p>
                <button class="layui-btn layui-btn-primary fly-case-active" onclick="location.href='<%=path%>/TeacherServlet?action=detail&id=${row.id}'">详情</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> -->
            </div>
        </li>

</c:forEach>
        </c:if>

    </ul>

     <%--  <li>
           <a href="javascript:doPage(1)" aria-label="Previous">
               <span aria-hidden="true">首页</span>
           </a>
       </li>
       <c:if test="${requestScope.pageInfo.pageNum>1}">
           <li><a href="javascript:doPage(${requestScope.pageInfo.pageNum -1})">上一页</a></li>
       </c:if>
       <c:if test="${requestScope.pageInfo.pageNum<requestScope.pageInfo.pages}">
           <li><a href="javascript:doPage(${requestScope.pageInfo.pageNum +1})">下一页</a></li>
       </c:if>
       <li><a href="#" disabled>${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页</a></li>
       &lt;%&ndash;<li><a href="#">4</a></li>
       <li><a href="#">5</a></li>&ndash;%&gt;
       <li>
           <a href="javascript:doPage(${requestScope.pageInfo.pages})" aria-label="Next">
               <span aria-hidden="true">末页</span>
           </a>
       </li>--%>

    <div style="text-align: center;">
        <div class="laypage-main">
            <a  href='TeacherServlet?action=toIndex&currentPage=1' >首页</a>

            <a  href='TeacherServlet?action=toIndex&currentPage=${pageTool.lastPage}'>上一页</a>

            <a href='TeacherServlet?action=toIndex&currentPage=${pageTool.nextPage}'>下一页</a>

            <a  href='TeacherServlet?action=toIndex&currentPage=${pageTool.pageCount}'>尾页</a>

            &nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
            <span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页

        <%--  <span class="laypage-curr"></span>--%>
            <%--  <a href="javascript:doPage(1)">首页</a>
        <c:if test="${requestScope.pageInfo.pageNum>1}">
                    <a href="javascript:doPage(${requestScope.pageInfo.pageNum -1})">上一页</a>
        </c:if>
            <c:if test="${requestScope.pageInfo.pageNum<requestScope.pageInfo.pages}">
                        <a href="javascript:doPage(${requestScope.pageInfo.pageNum +1})" class="laypage-next">下一页</a>
            </c:if>
              <a href="javascript:doPage(${requestScope.pageInfo.pages})" class="laypage-last" title="尾页">尾页</a>--%>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>

<script src="<%=path %>/res/layui/layui.js"></script>
<script>

    function doPage(pageNo) {
        document.getElementById("pageNum").value = pageNo;
        document.getElementById("searchForm").submit();
    }
    layui.use([ 'form','jquery','layer','upload' ], function() {
        var form = layui.form,
            layer = layui.layer,
            $= layui.jquery,
            upload=layui.upload;
            form.render();//这句一定要加，占坑



        });
layui.config({
  version: "3.0.0"
  ,base: 'resouce/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>