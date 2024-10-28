package edu.school.servlet;

import edu.school.entity.*;
import edu.school.service.CommentService;
import edu.school.service.StudentService;
import edu.school.service.TieziService;
import edu.school.service.impl.CommentServiceImpl;
import edu.school.service.impl.StudentServiceImpl;
import edu.school.service.impl.TieziServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//处理帖子的业务
public class TieziServlet extends BaseServlet {
    private TieziService service=new TieziServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    private CommentService cs=new CommentServiceImpl();
    //分页查询帖子信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryTieziCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Tiezi> list=service.getByPage(pageTool);
        for(Tiezi tiezi: list){
            Student student=studentService.queryById(tiezi.getSid());
            tiezi.setStudent(student);
        }
        //2.存储到域对象中
        request.setAttribute("tieziList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/tiezi/tiezi_list.jsp").forward(request, response);
    }



    //我发布的帖子
    protected void myTieziList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
        List<Tiezi> list=service.findMyTiezi(student.getId());
        for(Tiezi tiezi: list){
            Student s=studentService.queryById(tiezi.getSid());
            tiezi.setStudent(s);
        }

        List<Tiezi> list2=service.fidAll();
        for(Tiezi tiezi: list2){
            Student s=studentService.queryById(tiezi.getSid());
            tiezi.setStudent(s);
            tiezi.setCounts(cs.queryTieziCommentCount(tiezi.getId()));
        }
        request.setAttribute("lastList", list2.size() > 8 ? list2.subList(0, 8) : list2); // 绑定参数
        //2.存储到域对象中
        request.setAttribute("tieziList", list);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/client/my_tiezi_list.jsp").forward(request, response);
    }


    //添加帖子信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
       Tiezi tiezi=WebUtils.copyParamToBean(request.getParameterMap(),new Tiezi());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        Student s= (Student) request.getSession().getAttribute("student");
        if(s==null) {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('please Login');");
            out.write("location.href='LoginServlet?action=toLogin'");
            out.write("</script>");
        }
        tiezi.setSid(s.getId());
        service.addTiezi(tiezi);
        response.sendRedirect(request.getContextPath()+"/TieziServlet?action=myTieziList");//重定向防止重复提交哦

    }

    	//根据id查询帖子信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Tiezi tiezi=service.findById(id);
         request.setAttribute("tiezi",tiezi);
         request.getRequestDispatcher("/WEB-INF/client/edit_tiezi.jsp").forward(request,response);
    }

    //帖子详情
    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        Tiezi tiezi=service.findById(id);
        request.setAttribute("tiezi",tiezi);
        List<Tiezi> list2=service.fidAll();
        for(Tiezi tiezis: list2){
            Student s=studentService.queryById(tiezis.getSid());
            tiezi.setStudent(s);
            tiezi.setCounts(cs.queryTieziCommentCount(tiezis.getId()));
        }
        List<Comment> list3=cs.findStudentCommentByTieziId(id);
        for(Comment comment: list3){
            Student s=studentService.queryById(comment.getSid());
            Tiezi t=service.findById(comment.getTiezi_id());
            comment.setStudent(s);
             comment.setTiezi(t);

        }
        //2.存储到域对象中
        request.setAttribute("commentList", list3);
        request.setAttribute("lastList", list2.size() > 8 ? list2.subList(0, 8) : list2); // 绑定参数
        request.getRequestDispatcher("/WEB-INF/client/tiezi_detail.jsp").forward(request,response);
    }



    //编辑帖子信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改

        Tiezi tiezi=WebUtils.copyParamToBean(request.getParameterMap(),new Tiezi());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.updateTiezi(tiezi);
        response.sendRedirect(request.getContextPath()+"/TieziServlet?action=list");//重定向防止重复提交哦
    }

    //删除帖子信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteTiezi(id);
        response.sendRedirect(request.getContextPath()+"/TieziServlet?action=list"); //重定向防止重复提交哦
    }

    //根据标题和帖子内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        List<Tiezi> list = service.findByMap(title, content);

        for(Tiezi tiezi: list){
            Student student=studentService.queryById(tiezi.getSid());
            tiezi.setStudent(student);
        }
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/tiezi/list_tiezi.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/tiezi/list_tiezi.jsp").forward(request, response);
        }
    }
  //跳转到添加帖子界面
    protected void toAddTiezi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/client/add_tiezi.jsp").forward(request,response);
    }
}
