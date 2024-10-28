package edu.school.servlet;

import edu.school.entity.PageTool;
import edu.school.entity.Board;
import edu.school.service.BoardService;
import edu.school.service.impl.BoardServiceImpl;
import edu.school.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//处理公告的业务
public class BoardServlet extends BaseServlet {
    private BoardService service=new BoardServiceImpl();

    //分页查询公告信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Board> boardList=service.findAllByPage(pageTool);
        //2.存储到域对象中
        request.setAttribute("boardList", boardList);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/board/board_list.jsp").forward(request, response);
    }

    //添加公告信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//添加
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String editor=request.getParameter("editor");

        try {
            Date nowtime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fbsj = simpleDateFormat.format(nowtime);
            service.addBoard(new Board(null,title,content,fbsj,editor));
        }catch (Exception e){}
        response.sendRedirect(request.getContextPath()+"/BoardServlet?action=list");//重定向防止重复提交哦


    }

    	//根据id查询公告信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Board board=service.findById(id);
         request.setAttribute("board",board);
         request.getRequestDispatcher("/WEB-INF/board/edit_board.jsp").forward(request,response);
    }



    //编辑公告信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String editor=request.getParameter("editor");
        service.updateBoard(new Board(id,title,content,null,editor));
        response.sendRedirect(request.getContextPath()+"/BoardServlet?action=list");//重定向防止重复提交哦
    }

    //删除公告信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteBoard(id);
        response.sendRedirect(request.getContextPath()+"/BoardServlet?action=list"); //重定向防止重复提交哦
    }

    //根据标题和公告内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        List<Board> list = service.findByMap(title, content);
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/board/list_board.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/board/list_board.jsp").forward(request, response);
        }
    }
  //跳转到添加公告界面
    protected void toAddBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/board/edit_board.jsp").forward(request,response);
    }
}
