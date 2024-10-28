package edu.school.servlet;

import edu.school.entity.*;
import edu.school.entity.Student;
import edu.school.entity.Student;
import edu.school.service.StudentService;
import edu.school.service.impl.StudentServiceImpl;
import edu.school.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StudentServlet extends BaseServlet {
    private StudentService service=new StudentServiceImpl();


   
    //分页查询学生信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);

        List<Student> studentList=service.findAllByPage(pageTool);
        //2.存储到域对象中
        request.setAttribute("studentList", studentList);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来

        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/student/student_list.jsp").forward(request, response);


    }

		
	//添加学生信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String stuno=request.getParameter("stuno");
        String realname=request.getParameter("realname");
        String pwd=request.getParameter("pwd");
        String sex=request.getParameter("sex");
        String major=request.getParameter("major");
        String bj=request.getParameter("bj");
        Date nowtime=new Date();
        if(service.existsStuno(stuno)){
            request.setAttribute("msg","该学号已存在");
            request.setAttribute("stuno",stuno);
            request.setAttribute("realname",realname);
            request.setAttribute("major",major);
            request.setAttribute("bj",bj);
            request.setAttribute("pwd",pwd);
            request.getRequestDispatcher("/WEB-INF/student/edit_student.jsp").forward(request,response);//返回到登陆界面
        }else {
            try {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String createDate=simpleDateFormat.format(nowtime);
                System.out.println("已到达"+createDate);
              //  service.addStudent(new Student(null,stuno,realname,pwd,major,sex,bj,createDate));
            }catch(Exception e) {};
            response.sendRedirect(request.getContextPath()+"/StudentServlet?action=list");//重定向防止重复提交哦

        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            Student student = fileUpload(request);
            System.out.println(student.getPhoto());
            service.update(student);
        /*    Student s = (Student) request.getSession().getAttribute("student");
            Admin a = (Admin) request.getSession().getAttribute("admin");
            if (s!=null) {
                service.update(student);
                request.getRequestDispatcher("/WEB-INF/student/edit_success.jsp").forward(request,response);
                //response.sendRedirect(request.getContextPath()+"/StudentServlet?action=findMyInfo&stuno="+stuno);//重定向防止重复提交哦
            }
            if(a!=null) {
                service.update(student);
                response.sendRedirect(request.getContextPath()+"/StudentServlet?action=list");//重定向防止重复提交哦
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    private Student fileUpload(HttpServletRequest request) {

        Student student=new Student();
        try {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(student, name, value);
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
                    student.setPhoto(filename);
                    System.out.println(student.getPhoto()+savepath);
                }
            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    //学生注册
    protected void registe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        try {
            request.setCharacterEncoding("utf-8");
            Student student = fileUpload(request);
            Date nowtime=new Date();
            if(service.existsStuno(student.getStuno())){
                request.setAttribute("msg","该学号已存在");
                request.setAttribute("stuno",student.getStuno());
                request.setAttribute("realname",student.getRealname());
                request.getRequestDispatcher("/WEB-INF/views/registe.jsp").forward(request,response);//返回到登陆界面
            }else {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String createDate = simpleDateFormat.format(nowtime);
                    System.out.println("已到达" + createDate);
                    service.addStudent(student);
                } catch (Exception e) {
                }
                ;
                request.getRequestDispatcher("/WEB-INF/views/registe_success.jsp").forward(request, response);//返回到登陆界面
            }
             /*   response.sendRedirect(request.getContextPath()+"/StudentServlet?action=list");//重定向防止重复提交哦*/
        } catch (Exception e) {
            e.printStackTrace();

        }
        return;
      /*  String stuno=request.getParameter("stuno");
        String realname=request.getParameter("realname");
        String pwd=request.getParameter("pwd");
        String sex=request.getParameter("sex");
        String major=request.getParameter("major");
        String bj=request.getParameter("bj");
        Date nowtime=new Date();
        if(service.existsStuno(stuno)){
            request.setAttribute("msg","该学号已存在");
            request.setAttribute("stuno",stuno);
            request.setAttribute("realname",realname);
            request.getRequestDispatcher("/WEB-INF/views/registe.jsp").forward(request,response);//返回到登陆界面
        }else {
            try {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String createDate=simpleDateFormat.format(nowtime);
                System.out.println("已到达"+createDate);
                service.addStudent(new Student(null,stuno,realname,pwd,major,sex,bj,createDate));
            }catch(Exception e) {};
            request.getRequestDispatcher("/WEB-INF/views/registe_success.jsp").forward(request,response);//返回到登陆界面

        }*/
    }

    	//根据id查询
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Student student=service.queryById(id);
         request.setAttribute("student",student);
       
         request.getRequestDispatcher("/WEB-INF/student/edit_student.jsp").forward(request,response);
    }
    //查看我的个人信息
    protected void findMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
        Student s= (Student) request.getSession().getAttribute("student");
        if(s==null) {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('please Login');");
            out.write("location.href='LoginServlet?action=toLogin'");
            out.write("</script>");
        }
        Student student=service.queryById(s.getId());
        request.setAttribute("student",student);
        request.getRequestDispatcher("/WEB-INF/client/set.jsp").forward(request,response);
    }


    //编辑学生信息
   /* protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String stuno=request.getParameter("stuno");
        String realname=request.getParameter("realname");
        String pwd=request.getParameter("pwd");
        String sex=request.getParameter("sex");
        String major=request.getParameter("major");
        String bj=request.getParameter("bj");
        String type=request.getParameter("type");
        if (type.equals("1")) {
            service.update(new Student(id,stuno,realname,pwd,major,sex,bj,null));
            request.getRequestDispatcher("/WEB-INF/student/edit_success.jsp").forward(request,response);
            //response.sendRedirect(request.getContextPath()+"/StudentServlet?action=findMyInfo&stuno="+stuno);//重定向防止重复提交哦
        }else {
            service.update(new Student(id,stuno,realname,pwd,major,sex,bj,null));
            response.sendRedirect(request.getContextPath()+"/StudentServlet?action=list");//重定向防止重复提交哦
        }



    }*/
    //删除学生信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.delete(id);
        response.sendRedirect(request.getContextPath()+"/StudentServlet?action=list"); //重定向防止重复提交哦
    }

    //根据学号和姓名查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String stuno=request.getParameter("stuno");
        String realname=request.getParameter("realname");
        List<Student> list=service.findByMap(stuno,realname);
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/student/list_student.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/student/list_student.jsp").forward(request, response);
        }
    }

  


   
    
  //跳转到添加学生界面
    protected void toAddStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/student/edit_student.jsp").forward(request,response);
    }
    //跳转到注册学生界面
    protected void toRegiste(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/views/registe.jsp").forward(request,response);
    }

    //跳转到学生信息修改
    protected void toEditStudentInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        Student student= (Student) request.getSession().getAttribute("student");
        request.setAttribute("student1",service.queryById(student.getId()));
        request.getRequestDispatcher("/WEB-INF/student/edit_myinfo.jsp").forward(request,response);
    }


    
  
}
