package edu.school.servlet;

import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.entity.Messages;
import edu.school.service.StudentService;
import edu.school.service.MessagesService;
import edu.school.service.impl.StudentServiceImpl;
import edu.school.service.impl.MessagesServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//处理留言信息的业务
public class MessagesServlet extends BaseServlet {
    private MessagesService service=new MessagesServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    //分页查询留言信息信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryMessagesCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Messages> list=service.getByPage(pageTool);
        for(Messages messages: list){
            Student student=studentService.queryById(messages.getSid());
            messages.setStudent(student);
        }
        //2.存储到域对象中
        request.setAttribute("messagesList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/messages/messages_list.jsp").forward(request, response);
    }

    //我发布的留言信息
    protected void myMessagesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }
        List<Messages> list=service.findMyMessages(student.getId());
        for(Messages messages: list){
            Student s=studentService.queryById(messages.getSid());
            messages.setStudent(s);
        }
        //2.存储到域对象中
        request.setAttribute("messagesList", list);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/client/my_messages_list.jsp").forward(request, response);
    }


    //添加留言信息信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }

       Messages messages=WebUtils.copyParamToBean(request.getParameterMap(),new Messages());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
       messages.setSid(student.getId());
        service.addMessages(messages);
        response.sendRedirect(request.getContextPath()+"/MessagesServlet?action=myMessagesList");//重定向防止重复提交哦

    }

    	//根据id查询留言信息信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Messages messages=service.findById(id);
         request.setAttribute("messages",messages);
         request.getRequestDispatcher("/WEB-INF/messages/edit_messages.jsp").forward(request,response);
    }



    //编辑留言信息信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改

        Messages messages=WebUtils.copyParamToBean(request.getParameterMap(),new Messages());//使用泛型就不用强制类型转换了。属于最优的写法// 相当于免去各个字段的request.getParameter的方法。
        service.updateMessages(messages);
        response.sendRedirect(request.getContextPath()+"/MessagesServlet?action=list");//重定向防止重复提交哦
    }

    //删除留言信息信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteMessages(id);
        response.sendRedirect(request.getContextPath()+"/MessagesServlet?action=list"); //重定向防止重复提交哦
    }

    protected void deleteMyMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteMessages(id);
        response.sendRedirect(request.getContextPath()+"/MessagesServlet?action=myMessagesList"); //重定向防止重复提交哦
    }

    //根据标题和留言信息内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询

        String content = request.getParameter("content");
        List<Messages> list = service.findByMap(content);

        for(Messages messages: list){
            Student student=studentService.queryById(messages.getSid());
            messages.setStudent(student);
        }
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/messages/list_messages.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/messages/list_messages.jsp").forward(request, response);
        }
    }
  //跳转到添加留言信息界面
    protected void toAddMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/messages/edit_messages.jsp").forward(request,response);
    }
}
