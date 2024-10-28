<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>心理咨询平台</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 
  <link rel="stylesheet" href="<%=path %>/res/layui/css/layui.css">
  <link rel="stylesheet" href="<%=path %>/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="index.jsp">
     <h3 style="color:#FFF">心理咨询预约平台</h3>
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
     <li class="layui-nav-item layui-this">
        <a href="index.jsp"><i class="iconfont icon-home"></i>首页</a>
      </li>
      <li class="layui-nav-item layui-this">
        <a href="IndexServlet?action=tieziList"><i class="iconfont icon-jiaoliu"></i>交流</a>
      </li>
       <li class="layui-nav-item">
        <a href="IndexServlet?action=messagesList"><i class="iconfont icon-iconmingxinganli"></i>留言板</a>
      </li>
     <%-- <li class="layui-nav-item">
        <a href="JuanServlet?action=IndexJuanList"><i class="iconfont icon-iconmingxinganli"></i>捐赠栏</a>
      </li>
      --%>
       <li class="layui-nav-item">
        <a href="IndexServlet?action=boardList"><i class="iconfont icon-iconmingxinganli"></i>公告栏</a>
      </li>
      <li class="layui-nav-item ">
        <a href="LoginServlet?action=toLogin">后台登录</a>
      </li>
      
    </ul>
    
    
    <ul class="layui-nav fly-nav-user">
      
      <!-- 未登入的状态 -->
       <c:if test="${empty student}">
      <li class="layui-nav-item">
        <a class="iconfont icon-touxiang layui-hide-xs" href="LoginServlet?action=toLogin"></a>
      </li>
      <li class="layui-nav-item">
        <a href="LoginServlet?action=toLogin">登入</a>
      </li>
      <li class="layui-nav-item">
        <a href="StudentServlet?action=toRegiste">注册</a>
      </li>
      </c:if>
    
      
      <!-- 登入后的状态 -->
     <c:if test="${sessionScope.type==1}">
         <li class="layui-nav-item">
             <a class="fly-nav-avatar" href="javascript:;">
             <cite class="layui-hide-xs"> ${student.realname }</cite>
             <img src="<%=path%>/upload/${student.photo }">
             </a>
         </li>
      <li class="layui-nav-item">
        <a class="fly-nav-avatar" href="StudentServlet?action=findMyInfo">
          <cite class="layui-hide-xs">个人中心</cite>
      <!--     <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i> -->
          

        </a>
        <dl class="layui-nav-child">
          <dd><a href="StudentServlet?action=findMyInfo"><i class="layui-icon">&#xe620;</i>个人信息</a></dd>
      

          <hr style="margin: 5px 0;">
          <dd><a href="LoginServlet?action=loginOut" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
      </c:if>
     
    </ul>
  </div>
</div>


<div class="fly-panel fly-column">
<div class="layui-container">
    <ul class="layui-clear">
          <li class="layui-hide-xs"><a href="IndexServlet?action=toIndex">首页</a></li>

    <%--  <form class="layui-form layui-col-md4" action="TieziServlet?action=IndexList" method="post">
            <div class="layui-inline">

                <div class="layui-input-inline">
                    <input type="text" name="keyword" placeholder="请输入标题关键词" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn layuiadmin-btn-comm" type="submit">
                    搜索
                </button>
            </div>
        </form>--%>

        <!-- 用户登入后显示 -->
        <c:if test="${sessionScope.type==1}">
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="TieziServlet?action=myTieziList">我发表的贴</a></li>
        </c:if>

    </ul>
    <c:if test="${sessionScope.type==1}">
        <div class="fly-column-right layui-hide-xs">
            <!--  <span ><i class="layui-icon"></i></span>  -->
            <a href="TieziServlet?action=toAddTiezi" class="layui-btn">发表新帖</a>
        </div>
    </c:if>
    <!--  <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
       <a href="add.html" class="layui-btn">发表新帖</a>
     </div>  -->
</div>
</div>


