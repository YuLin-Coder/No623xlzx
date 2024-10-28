package edu.school.servlet;

import com.sun.javafx.scene.shape.PathUtils;
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
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

//处理教师的业务
public class TeacherServlet extends BaseServlet {
    private TeacherService service=new TeacherServiceImpl();
    private TieziService ts=new TieziServiceImpl();
    private CommentService cs=new CommentServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    //分页查询教师信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Teacher> TeacherList=service.findAllByPage(pageTool);
        //2.存储到域对象中
        request.setAttribute("teacherList", TeacherList);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/teacher/teacher_list.jsp").forward(request, response);
    }


    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            Teacher teacher = fileUpload(request);
             service.addTeacher(teacher);
             response.sendRedirect(request.getContextPath()+"/TeacherServlet?action=list");//重定向防止重复提交哦
        } catch (Exception e) {
            e.printStackTrace();

        }
        return;
    }

    //上传头像
    private Teacher fileUpload(HttpServletRequest request) {

        Teacher teacher=new Teacher();
        try {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(teacher, name, value);
                }else{
                    String filename = item.getName();
                    /*	String savefilename = makeFileName(filename);*/
                    String  savepath= WebUtils.getPath();
                    //String savepath= this.getServletContext().getRealPath("/upload");//Tomcat虚拟路径
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + filename);
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    while((len = in.read(buffer)) > 0){
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    teacher.setImgUrl(filename);
                    System.out.println(teacher.getImgUrl()+savepath);
                }
            }
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    //根据id查询教师信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Teacher Teacher=service.findById(id);
         request.setAttribute("teacher",Teacher);
         request.getRequestDispatcher("/WEB-INF/teacher/edit_teacher.jsp").forward(request,response);
    }

    //教师详情
    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        System.out.println("教师id"+id);
        Teacher Teacher=service.findById(id);
        request.setAttribute("teacher",Teacher);
        List<Tiezi> list=ts.fidAll();
        for(Tiezi tiezi: list){
            Student student=studentService.queryById(tiezi.getSid());
            tiezi.setStudent(student);
            tiezi.setCounts(cs.queryTieziCommentCount(tiezi.getId()));
        }

        List<Comment> list2=cs.findStudentComment(id);
        for(Comment comment: list2){
            Student s=studentService.queryById(comment.getSid());
        /*    Tiezi tiezi=ts.findById(comment.getTiezi_id());*/
            Teacher t=service.findById(comment.getTeacher_id());
            comment.setStudent(s);
        /*    comment.setTiezi(tiezi);*/
            comment.setTeacher(t);
        }
        //2.存储到域对象中
        request.setAttribute("commentList", list2);
        request.setAttribute("tieziList", list.size() > 8 ? list.subList(0, 8) : list); // 绑定参数
        request.getRequestDispatcher("/WEB-INF/client/teacher_detail.jsp").forward(request,response);
    }

    //修改个人信息
    protected void toEditTeacherInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
       Teacher teacher= (Teacher) request.getSession().getAttribute("teacher");
        request.setAttribute("teacher",service.findById(teacher.getId()));
        request.getRequestDispatcher("/WEB-INF/teacher/edit_myinfo.jsp").forward(request,response);
    }
    //修改教师照片
    protected void toEditTeacherTx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        Teacher teacher= (Teacher) request.getSession().getAttribute("teacher");
        request.setAttribute("teacher",service.findById(teacher.getId()));
        request.getRequestDispatcher("/WEB-INF/teacher/edit_myTx.jsp").forward(request,response);
    }



    //编辑教师信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            Teacher teacher = fileUpload(request);
            service.updateTeacher(teacher);
            response.sendRedirect(request.getContextPath()+"/TeacherServlet?action=list");//重定向防止重复提交哦
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

//教师自己修改
    protected void updateTx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            Teacher teacher = fileUpload(request);

            service.updateTx(teacher);
            request.getRequestDispatcher("/WEB-INF/teacher/edittx_success.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return;
    }

    protected void update1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String tno=request.getParameter("tno");
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        String sex=request.getParameter("sex");
        String major=request.getParameter("major");
        String detail=request.getParameter("detail");
        String phone=request.getParameter("phone");

            service.updateTeacher(new Teacher(id, tno, pwd, name, sex, major, detail, phone, null));
            request.getRequestDispatcher("/WEB-INF/teacher/edit_success.jsp").forward(request, response);



    }
    //删除学生信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteTeacher(id);
        response.sendRedirect(request.getContextPath()+"/TeacherServlet?action=list"); //重定向防止重复提交哦
    }

    //根据工号和教师姓名查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String tno=request.getParameter("tno");
        String name=request.getParameter("name");
        List<Teacher> list=service.findByMap(tno,name);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/teacher/list_teacher.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/teacher/list_teacher.jsp").forward(request, response);
        }
    }

    
  //跳转到添加教师界面
    protected void toAddTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/teacher/add_teacher.jsp").forward(request,response);
    }



    
  
}
