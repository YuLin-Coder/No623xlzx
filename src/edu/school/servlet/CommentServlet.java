package edu.school.servlet;

import edu.school.entity.*;
import edu.school.service.CommentService;
import edu.school.service.StudentService;
import edu.school.service.TeacherService;
import edu.school.service.TieziService;
import edu.school.service.impl.CommentServiceImpl;
import edu.school.service.impl.StudentServiceImpl;
import edu.school.service.impl.TeacherServiceImpl;
import edu.school.service.impl.TieziServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//处理评论信息的业务
public class CommentServlet extends BaseServlet {
    private CommentService service=new CommentServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    private TieziService tieziService=new TieziServiceImpl();
    private TeacherService ts=new TeacherServiceImpl();
    //分页查询评论信息信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCommentCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Comment> list=service.getByPage(pageTool);
        for(Comment comment: list){
            Student student=studentService.queryById(comment.getSid());
            Tiezi tiezi=tieziService.findById(comment.getTiezi_id());
            Teacher teacher=ts.findById(comment.getTeacher_id());
            comment.setStudent(student);
            comment.setTiezi(tiezi);
            comment.setTeacher(teacher);
        }
        //2.存储到域对象中
        request.setAttribute("commentList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/comment/comment_list.jsp").forward(request, response);
    }

    //我发布的评论信息
    protected void myCommentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
        List<Comment> list=service.findMyComment(student.getId());
        for(Comment comment: list){
            Student s=studentService.queryById(comment.getSid());
            Tiezi tiezi=tieziService.findById(comment.getTiezi_id());
            Teacher teacher=ts.findById(comment.getTeacher_id());
            comment.setStudent(s);
            comment.setTiezi(tiezi);
            comment.setTeacher(teacher);
        }
        //2.存储到域对象中
        request.setAttribute("commentList", list);
        request.setAttribute("counts", list.size());
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/client/my_comment_list.jsp").forward(request, response);
    }

    //学生的评论信息
    protected void studentCommentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");

        List<Comment> list=service.findStudentComment(teacher.getId());
        for(Comment comment: list){
            Student s=studentService.queryById(comment.getSid());
            Tiezi tiezi=tieziService.findById(comment.getTiezi_id());
            Teacher t=ts.findById(comment.getTeacher_id());
            comment.setStudent(s);
            comment.setTiezi(tiezi);
            comment.setTeacher(t);
        }
        //2.存储到域对象中
        request.setAttribute("commentList", list);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/comment/my_comment_list.jsp").forward(request, response);
    }


    //添加评论信息信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
       Comment comment=WebUtils.copyParamToBean(request.getParameterMap(),new Comment());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
       comment.setSid(student.getId());
        service.addComment(comment);
        response.sendRedirect(request.getContextPath()+"/CommentServlet?action=myCommentList");//重定向防止重复提交哦

    }

    	//根据id查询评论信息信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Comment comment=service.findById(id);
         request.setAttribute("comment",comment);
         request.getRequestDispatcher("/WEB-INF/comment/edit_comment.jsp").forward(request,response);
    }



    //编辑评论信息信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改

        Comment comment=WebUtils.copyParamToBean(request.getParameterMap(),new Comment());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.updateComment(comment);
        response.sendRedirect(request.getContextPath()+"/CommentServlet?action=list");//重定向防止重复提交哦
    }

    //删除评论信息信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteComment(id);
        response.sendRedirect(request.getContextPath()+"/CommentServlet?action=list"); //重定向防止重复提交哦
    }
   //删除u我的评论
    protected void deleteMyComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteComment(id);
        response.sendRedirect(request.getContextPath()+"/CommentServlet?action=myCommentList"); //重定向防止重复提交哦
    }
  //老师删除学生对自己评价
    protected void delete2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteComment(id);
        response.sendRedirect(request.getContextPath()+"/CommentServlet?action=studentCommentList"); //重定向防止重复提交哦
    }


    //根据标题和评论信息内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询

        String content = request.getParameter("content");
        List<Comment> list = service.findByMap(content);
        for(Comment comment: list){
            Student student=studentService.queryById(comment.getSid());
            Tiezi tiezi=tieziService.findById(comment.getTiezi_id());
            Teacher teacher=ts.findById(comment.getTeacher_id());
            comment.setStudent(student);
            comment.setTiezi(tiezi);
            comment.setTeacher(teacher);
        }
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/comment/list_comment.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/comment/list_comment.jsp").forward(request, response);
        }
    }
  //跳转到添加评论信息界面
    protected void toAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/comment/edit_comment.jsp").forward(request,response);
    }
}
