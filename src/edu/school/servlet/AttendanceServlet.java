package edu.school.servlet;

import edu.school.entity.PageTool;
import edu.school.entity.Attendance;
import edu.school.entity.Teacher;
import edu.school.service.AttendanceService;
import edu.school.service.TeacherService;
import edu.school.service.impl.AttendanceServiceImpl;
import edu.school.service.impl.TeacherServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AttendanceServlet extends BaseServlet {
    private AttendanceService service=new AttendanceServiceImpl();
    private TeacherService ts=new TeacherServiceImpl();




    //分页查询心理老师打卡信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);

        List<Attendance> list=service.findAllAttendance(pageTool);
        for(Attendance a:list){
            Teacher teacher=ts.findById(a.getT_id());
            a.setTeacher(teacher);
        }
        //2.存储到域对象中
        request.setAttribute("attendanceList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来

        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/attendance/attendance_list.jsp").forward(request, response);


    }
    //我的考勤记录
    protected void my_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
        List<Attendance> list=service.findByMap(teacher.getId());

        //2.存储到域对象中
        request.setAttribute("list", list);

        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/attendance/my_attendance_list.jsp").forward(request, response);


    }

		
	//添加心理老师打卡信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
            int t_id=Integer.parseInt(request.getParameter("t_id"));
            Attendance record=new Attendance();
            record.setT_id(t_id);
            record.setStatus(1);
            service.addAttendance(record);
        response.sendRedirect(request.getContextPath()+"/AttendanceServlet?action=list");//重定向防止重复提交哦


    }

    protected void wydk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
        if(teacher!=null){
            Attendance record=new Attendance();
            record.setT_id(teacher.getId());
            record.setStatus(1);
            service.addAttendance(record);
        }
        response.sendRedirect(request.getContextPath()+"/AttendanceServlet?action=my_list");//重定向防止重复提交哦


    }

    	//根据id查询
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);

         Attendance a=service.findById(id);
         request.setAttribute("attendance",a);
       
         request.getRequestDispatcher("/WEB-INF/attendance/edit_attendance.jsp").forward(request,response);
    }



    //编辑心理老师打卡信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Attendance record=new Attendance();
        int status=Integer.parseInt(request.getParameter("status"));
        record.setStatus(status);
        service.update(new Attendance(id,null,status,null));
            response.sendRedirect(request.getContextPath()+"/AttendanceServlet?action=list");//重定向防止重复提交哦
    }
    //删除心理老师打卡信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteAttendance(id);
        response.sendRedirect(request.getContextPath()+"/AttendanceServlet?action=list"); //重定向防止重复提交哦
    }

    //根据学号和姓名查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
       // int id= WebUtils.parseInt(request.getParameter("id"),0);
        String tname=request.getParameter("tname");
        Teacher teacher=ts.findByTname(tname);
        List<Attendance> list=service.findByMap(teacher.getId());
        if(null == list || list.size() ==0 ){
            request.setAttribute("msg" ,"无此信息");
            request.getRequestDispatcher("/WEB-INF/attendance/list_attendance.jsp").forward(request, response);
        }else {
            request.removeAttribute("msg");
            request.setAttribute("list" ,list);
            request.getRequestDispatcher("/WEB-INF/attendance/list_attendance.jsp").forward(request, response);
        }
    }

  

  //跳转到修改心理老师打卡界面
    protected void toAddAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        List<Teacher> teachers=ts.findAll();
        request.setAttribute("teachers" ,teachers);
        request.getRequestDispatcher("/WEB-INF/attendance/add_attendance.jsp").forward(request,response);
    }

    //教师考勤
    protected void toWydk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/attendance/wydk.jsp").forward(request,response);
    }

    //跳转到修改心理老师打卡信息修改
    protected void toEditAttendanceInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/Attendance/edit_myinfo.jsp").forward(request,response);
    }


    
  
}
