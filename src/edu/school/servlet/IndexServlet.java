package edu.school.servlet;

import edu.school.entity.*;
import edu.school.service.*;
import edu.school.service.impl.*;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;


/**
 * 处理登录得请求
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private StudentService service=  new StudentServiceImpl();
	private TeacherService ts=  new TeacherServiceImpl();
	private TieziService tieziService=new TieziServiceImpl();
	private AdminService as=new AdminServiceImpl();
	private BoardService bs=new BoardServiceImpl();
	private MessagesService ms=new MessagesServiceImpl();
	private CommentService cs=new CommentServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   //下载
   protected void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//设置ContentType字段值
		response.setContentType("text/html;charset=utf-8");
		//设置相应消息编码
		response.setCharacterEncoding("utf-8");
		//设置请求消息编码
		request.setCharacterEncoding("utf-8");
		//获取所要下载的文件名称
		String filename = request.getParameter("filename");
		//对文件名称编码
		filename = new String(filename.trim().getBytes("iso8859-1"),"UTF-8");
		//下载文件所在目录
		String folder = WebUtils.getPath();
		// 通知浏览器以下载的方式打开
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
		// 通过文件流读取文件
		InputStream in = getServletContext().getResourceAsStream(
				folder+filename);
		// 获取response对象的输出流
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		//循环取出流中的数据
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
	}


  //前台首页
  protected void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
	  int totalCount=ts.queryCount();
	  //2.获取的当前页码,这个是从页面获取的
	  String currentPage = request.getParameter("currentPage");
	  PageTool pageTool=new PageTool(totalCount, currentPage);
	  List<Teacher> TeacherList=ts.findAllByPage(pageTool);
	  //2.存储到域对象中
	  request.setAttribute("teacherList", TeacherList);
	  //将分页信息存储
	  request.setAttribute("pageTool", pageTool);
	  //将模糊查询的结构存储起来
	  //3.通过请求转发
	  request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(request, response);
  }
    //前台帖子
	protected void tieziList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
		int totalCount=tieziService.queryTieziCount();
		//2.获取的当前页码,这个是从页面获取的
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool=new PageTool(totalCount, currentPage);
		List<Tiezi> list=tieziService.getByPage(pageTool);
		for(Tiezi tiezi: list){
			Student student=service.queryById(tiezi.getSid());
			tiezi.setStudent(student);
			tiezi.setCounts(cs.queryTieziCommentCount(tiezi.getId()));
		}
		//2.存储到域对象中
		request.setAttribute("tieziList", list);

		//将分页信息存储
		request.setAttribute("pageTool", pageTool);
		//将模糊查询的结构存储起来
		//3.通过请求转发
		request.getRequestDispatcher("/WEB-INF/client/tiezi_list.jsp").forward(request, response);
	}

    //前台公告
	protected void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
		int totalCount=service.queryCount();
		//2.获取的当前页码,这个是从页面获取的
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool=new PageTool(totalCount, currentPage);
		List<Board> boardList=bs.findAllByPage(pageTool);
		//2.存储到域对象中
		request.setAttribute("boardList", boardList);
		//将分页信息存储
		request.setAttribute("pageTool", pageTool);
		//将模糊查询的结构存储起来
		//3.通过请求转发
		request.getRequestDispatcher("/WEB-INF/client/board_list.jsp").forward(request, response);
	}

  // 前台留言
	protected void messagesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
		int totalCount=ms.queryMessagesCount();
		//2.获取的当前页码,这个是从页面获取的
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool=new PageTool(totalCount, currentPage);
		List<Messages> list=ms.getByPage(pageTool);
		for(Messages messages: list){
			Student student=service.queryById(messages.getSid());
			messages.setStudent(student);
		}
		//2.存储到域对象中
		request.setAttribute("messagesList", list);
		//将分页信息存储
		request.setAttribute("pageTool", pageTool);
		//将模糊查询的结构存储起来
		//3.通过请求转发
		request.getRequestDispatcher("/WEB-INF/client/messages_list.jsp").forward(request, response);
	}

	//注销登录
  	 protected void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//退出
  	      request.getSession().invalidate();
  	      request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
  	  }
	//去登录
	protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//退出
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
	}
}
