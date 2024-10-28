package edu.school.servlet;

import edu.school.entity.*;
import edu.school.service.*;
import edu.school.service.impl.*;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//处理回复信息的业务
public class ReplyServlet extends BaseServlet {
    private ReplyService service=new ReplyServiceImpl();
    private CommentService cs=new CommentServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    private MessagesService ms=new MessagesServiceImpl();
    private TeacherService ts=new TeacherServiceImpl();
    //分页查询回复信息信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryReplyCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Reply> list=service.getByPage(pageTool);
        for(Reply reply: list){
            Student student=studentService.queryById(reply.getSid());
            Comment comment=cs.findById(reply.getC_id());
            Messages messages=ms.findById(reply.getM_id());
            Teacher teacher=ts.findById(reply.getTeacher_id());
            reply.setTeacher(teacher);
            reply.setStudent(student);
            reply.setMessages(messages);
            reply.setComment(comment);
        }
        //2.存储到域对象中
        request.setAttribute("replyList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/reply/reply_list.jsp").forward(request, response);
    }

    //我发布的回复信息
    protected void myReplyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
        List<Reply> list=service.findMyReply(student.getId());
        for(Reply reply: list){
            Student s=studentService.queryById(reply.getSid());
            Comment comment=cs.findById(reply.getC_id());
            Teacher teacher=ts.findById(reply.getTeacher_id());
            reply.setTeacher(teacher);
            Messages messages=ms.findById(reply.getM_id());
            reply.setStudent(s);
            reply.setMessages(messages);
            reply.setComment(comment);
        }
        //2.存储到域对象中
        request.setAttribute("replyList", list);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/client/my_reply.jsp").forward(request, response);
    }


    protected void findTeacherReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面

        Teacher teacher=(Teacher) request.getSession().getAttribute("teacher");
        List<Reply> list=service.findTeacherReply(teacher.getId());
        for(Reply reply: list){
            Student s=studentService.queryById(reply.getSid());
            Comment comment=cs.findById(reply.getC_id());
            Messages messages=ms.findById(reply.getM_id());
            Teacher t=ts.findById(reply.getTeacher_id());
            reply.setTeacher(t);
            reply.setStudent(s);
            reply.setMessages(messages);
            reply.setComment(comment);
        }
        //2.存储到域对象中
        request.setAttribute("replyList", list);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/reply/my_reply.jsp").forward(request, response);
    }



   /* //添加帖子息信息
    protected void addTeiziReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
       Reply reply=WebUtils.copyParamToBean(request.getParameterMap(),new Reply());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.addReply(reply);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=findTeacherReply");//重定向防止重复提交哦

    }*/
//留言回复
    protected void addMessagesReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Reply reply=WebUtils.copyParamToBean(request.getParameterMap(),new Reply());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.addReply(reply);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=findTeacherReply");//重定向防止重复提交哦

    }
  //教师回复
    protected void addTeacherReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Reply reply=WebUtils.copyParamToBean(request.getParameterMap(),new Reply());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.addReply(reply);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=findTeacherReply");//重定向防止重复提交哦

    }

    	//根据id查询回复信息信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Reply reply=service.findById(id);
         request.setAttribute("reply",reply);
         request.getRequestDispatcher("/WEB-INF/reply/edit_reply.jsp").forward(request,response);
    }



    //编辑回复信息信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改

        Reply reply=WebUtils.copyParamToBean(request.getParameterMap(),new Reply());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.updateReply(reply);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=list");//重定向防止重复提交哦
    }

    //删除回复信息信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteReply(id);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=list"); //重定向防止重复提交哦
    }
   //删除我的回复
    protected void delete2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteReply(id);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=myReplyList"); //重定向防止重复提交哦
    }

    protected void delete3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteReply(id);
        response.sendRedirect(request.getContextPath()+"/ReplyServlet?action=findTeacherReply"); //重定向防止重复提交哦
    }

    //根据标题和回复信息内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询

        String content = request.getParameter("content");
        List<Reply> list = service.findByMap(content);

        for(Reply reply: list){
            Student s=studentService.queryById(reply.getSid());
            Comment comment=cs.findById(reply.getC_id());
            Messages messages=ms.findById(reply.getM_id());
            Teacher teacher=ts.findById(reply.getTeacher_id());
            reply.setTeacher(teacher);
            reply.setStudent(s);
            reply.setMessages(messages);
            reply.setComment(comment);
        }
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/reply/list_reply.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/reply/list_reply.jsp").forward(request, response);
        }
    }
  //跳转到添加回复信息界面
    protected void toAddMessagesReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        int sid=WebUtils.parseInt(request.getParameter("sid"),0);
        request.setAttribute("sid", sid);
        request.getRequestDispatcher("/WEB-INF/reply/messages_reply.jsp").forward(request,response);
    }

    protected void toAddTeacherReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        int sid=WebUtils.parseInt(request.getParameter("sid"),0);
        request.setAttribute("sid", sid);
        request.getRequestDispatcher("/WEB-INF/reply/teacher_reply.jsp").forward(request,response);
    }
    protected void toAddTieziReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        int sid=WebUtils.parseInt(request.getParameter("sid"),0);
        request.setAttribute("sid", sid);
        request.getRequestDispatcher("/WEB-INF/reply/tiezi_reply.jsp").forward(request,response);
    }
}
