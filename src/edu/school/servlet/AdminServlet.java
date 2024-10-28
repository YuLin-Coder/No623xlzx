package edu.school.servlet;

import edu.school.entity.Admin;
import edu.school.service.AdminService;
import edu.school.service.impl.AdminServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService as=new AdminServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //管理员修改个人信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
    	
         String username=request.getParameter("username");
         String pwd=request.getParameter("pwd");  
         String nickname=request.getParameter("nickname");  
         int b=as.updateAdmin(new Admin(null,username,pwd,nickname));
         if(b==1) {
        	  request.getRequestDispatcher("/WEB-INF/views/edit_success.jsp").forward(request,response);
         }
           
   
   }
    
    //跳转到管理员修改信息界面
    protected void toUpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        Admin admin= (Admin) request.getSession().getAttribute("admin");
        request.setAttribute("record",as.findById(admin.getId()));
        request.getRequestDispatcher("/WEB-INF/views/edit_admin.jsp").forward(request,response);
	}
    
	
	
	
	
}
