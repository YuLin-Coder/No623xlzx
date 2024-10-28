package edu.school.servlet;

import edu.school.entity.Student;
import edu.school.entity.Yyzx;
import edu.school.entity.PageTool;
import edu.school.service.YyzxService;
import edu.school.service.impl.YyzxServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

//处理预约咨询的业务
public class YyzxServlet extends BaseServlet {
    private YyzxService service=new YyzxServiceImpl();

    //分页查询预约咨询信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Map<String, Object>> list=service.selectdoubleList(pageTool);
        //2.存储到域对象中
        request.setAttribute("yyzxList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/yyzx/yyzx_list.jsp").forward(request, response);
    }

    //添加预约咨询信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
        System.out.println(student.getId());
        String tname=request.getParameter("tname");
        String bz=request.getParameter("bz");
        String yysj=request.getParameter("yysj");
        service.addYyzx(new Yyzx(null,student.getId(),1,tname,bz,yysj));
        response.sendRedirect(request.getContextPath()+"/YyzxServlet?action=findMyYyzx&s_id="+student.getId());//重定向防止重复提交哦

    }

    	//根据id查询预约咨询信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int yid=WebUtils.parseInt(request.getParameter("yid"),0);
         Yyzx yyzx=service.findById(yid);
         request.setAttribute("yyzx",yyzx);
         request.getRequestDispatcher("/WEB-INF/yyzx/edit_yyzx.jsp").forward(request,response);
    }



    //编辑预约咨询信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int yid= WebUtils.parseInt(request.getParameter("yid"),0);
        int s_id=WebUtils.parseInt(request.getParameter("s_id"),0);
        String tname=request.getParameter("tname");
        String bz=request.getParameter("bz");
        String yysj=request.getParameter("yysj");
        String type=request.getParameter("type");
        service.updateYyzx(new Yyzx(yid,s_id,null,tname,bz,yysj));
        if(type.equals("1")){
            response.sendRedirect(request.getContextPath()+"/YyzxServlet?action=findMyYyzx&s_id="+s_id);//重定向防止重复提交哦
        }else{
            response.sendRedirect(request.getContextPath()+"/YyzxServlet?action=list");//重定向防止重复提交哦
        }

    }
    //同意预约
    protected void ok(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int yid= WebUtils.parseInt(request.getParameter("yid"),0);
        System.out.println(yid+"*****");
        String tname=request.getParameter("tname");
        service.updateStaus(new Yyzx(yid,null,2,null,null,null));
        List<Map<String, Object>> list = service.findMyTname(tname);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/teacher/teacher_yyzxlist.jsp").forward(request, response);
    }
    //拒绝预约
    protected void refuse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int yid= WebUtils.parseInt(request.getParameter("yid"),0);
        String tname=request.getParameter("tname");
        service.updateStaus(new Yyzx(yid,null,0,null,null,null));
        List<Map<String, Object>> list = service.findMyTname(tname);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/teacher/teacher_yyzxlist.jsp").forward(request, response);

    }

    //删除预约咨询信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("yid"),0);
        int s_id=WebUtils.parseInt(request.getParameter("s_id"),0);
        String type= String.valueOf( request.getSession().getAttribute("type"));
        service.deleteYyzx(id);
        if(type.equals("1")){
            response.sendRedirect(request.getContextPath()+"/YyzxServlet?action=findMyYyzx&s_id="+s_id);//重定向防止重复提交哦
        }else{
            response.sendRedirect(request.getContextPath()+"/YyzxServlet?action=list");//重定向防止重复提交哦
        }

    }

    //根据预约咨询日期查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String yysj = request.getParameter("yysj");
        List<Map<String, Object>> list = service.findByMap(yysj);
        if (null == list || list.size() == 0) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/yyzx/list_yyzx.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/yyzx/list_yyzx.jsp").forward(request, response);
        }
    }
  //跳转到添加预约咨询界面
    protected void toAddYyzx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        String tname = request.getParameter("tname");
        request.setAttribute("tname", tname);
        request.getRequestDispatcher("/WEB-INF/client/add_yyzx.jsp").forward(request,response);
    }

    //查看我的预约咨询
    protected void findMyYyzx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        int s_id=WebUtils.parseInt(request.getParameter("s_id"),0);
        List<Yyzx> list = service.findMyYyzx(s_id);
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/client/my_yyzx.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/client/my_yyzx.jsp").forward(request, response);
        }
    }
    //医生查看预约心理咨询的学生
    protected void findByTname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String tname = request.getParameter("tname");
        List<Map<String, Object>> list = service.findMyTname(tname);
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "没有预约信息");
            request.getRequestDispatcher("/WEB-INF/teacher/teacher_yyzxlist.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/teacher/teacher_yyzxlist.jsp").forward(request, response);
        }
    }


}
