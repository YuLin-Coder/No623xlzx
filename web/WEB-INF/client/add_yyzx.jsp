<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/common/website_header.jsp"/>
  

<div class="layui-container fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
    <!--<div class="fly-none">没有权限</div>-->
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form action="YyzxServlet?action=add" method="post">
              <div class="layui-row layui-col-space15 layui-form-item">
               
                <div class="layui-form-item">
                  <label for="tname" class="layui-form-label">教师姓名</label>
                  <div class="layui-input-block">
                    <input type="text" id="tname" name="tname" value="${tname}" readonly autocomplete="off" class="layui-input">
                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                  </div>
                </div>
              </div>

              <div class="layui-form-item">
                <label for="yysj" class="layui-form-label">预约日期</label>
                <div class="layui-input-block">
                  <input type="text" class="layui-input" id="yysj" name="yysj" placeholder="yyyy-MM-dd">
                </div>
              </div>

              <div class="layui-form-item">
                <label for="bz" class="layui-form-label">备注：</label>
                <div class="layui-input-block">
                  <textarea type="text" name="bz" id="bz" placeholder="备注" class="layui-textarea"></textarea>

                </div>
              </div>




              <div class="layui-form-item">
                <button class="layui-btn" type="submit">立即预约</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>


<%-- <script src="<%=path%>/res/layui/layui.js"></script> --%>
<%-- <script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
 <script src="<%=path%>/js/ueditor/ueditor.config.js"></script>
    <script src="<%=path%>/js/ueditor/ueditor.all.min.js"></script>
    <script src="<%=path%>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
       <script type="text/javascript">
       var ue = UE.getEditor('container',{
    	   initialFrameWidth:1000,
    	//   initialFrameHeight:400,
       });
   </script>--%>

<script src="<%=path %>/res/layui/layui.js"></script>
<script>
  layui.use([ 'form','jquery','layer','upload','layedit','laydate' ], function() {
    var form = layui.form,
            layer = layui.layer,
            $= layui.jquery,
            layedit=layui.layedit,
            laydate=layui.laydate,
            upload=layui.upload;
    form.render();//这句一定要加，占坑
    laydate.render({
      elem: '#yysj'
      ,type: 'date'
    });

    //建立编辑器
    var index = layedit.build('content',{
      tool: ['strong','italic','underline','del' ,'|','left','center','right','link','unlink','face']
    });





    /*
    $("#tx").change(function () {
        //创建blob对象，浏览器将文件放入内存中，并生成标识
        var img_src = URL.createObjectURL($(this)[0].files[0]);
        //给img标检的src赋值
        document.getElementById("preview_img").src=img_src;
        //URL.revokeObjectURL(img_src);// 手动 回收，
    });*/
  });
  /*layui.config({
    version: "3.0.0"
    ,base: 'res/mods/'
  }).extend({
    fly: 'index'
  }).use('fly');*/
</script>
<!-- <script>

layui.config({
  version: "3.0.0"
  ,base: 'res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script> -->

</body>
</html>