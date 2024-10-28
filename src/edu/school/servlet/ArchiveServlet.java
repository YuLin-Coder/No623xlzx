package edu.school.servlet;

import edu.school.entity.PageTool;
import edu.school.entity.Student;
import edu.school.entity.Archive;
import edu.school.entity.Archive;
import edu.school.service.StudentService;
import edu.school.service.ArchiveService;
import edu.school.service.impl.StudentServiceImpl;
import edu.school.service.impl.ArchiveServiceImpl;
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
import java.io.PrintWriter;
import java.util.List;

//处理档案的业务
public class ArchiveServlet extends BaseServlet {
    private ArchiveService service=new ArchiveServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    //分页查询档案信息
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        int totalCount=service.queryArchiveCount();
        //2.获取的当前页码,这个是从页面获取的
        String currentPage = request.getParameter("currentPage");
        PageTool pageTool=new PageTool(totalCount, currentPage);
        List<Archive> list=service.getByPage(pageTool);
        for(Archive archive: list){
            Student student=studentService.queryById(archive.getSid());
            archive.setStudent(student);
        }
        //2.存储到域对象中
        request.setAttribute("archiveList", list);
        //将分页信息存储
        request.setAttribute("pageTool", pageTool);
        //将模糊查询的结构存储起来
        //3.通过请求转发
        request.getRequestDispatcher("/WEB-INF/archive/archive_list.jsp").forward(request, response);
    }

    //我发布的档案
    protected void myArchiveList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到注册界面
        Student student=(Student) request.getSession().getAttribute("student");
        if(student==null) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('please login!');location.href='LoginServlet?action=loginOut'");
            out.write("</script>");
            out.close();
        }

        Student s=studentService.queryById(student.getId());
        Archive archive=service.findBySid(student.getId());


        //2.存储到域对象中
        request.setAttribute("archive", archive);
        request.setAttribute("student", student);
        //将分页信息存储
        request.getRequestDispatcher("/WEB-INF/client/my_archive_list.jsp").forward(request, response);
    }


    //添加档案信息
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            Archive archive = fileUpload(request);
            service.addArchive(archive);
            response.sendRedirect(request.getContextPath()+"/ArchiveServlet?action=myArchiveList");//重定向防止重复提交哦
        } catch (Exception e) {
            e.printStackTrace();

        }
        return;
    }

    //上传附件
    private Archive fileUpload(HttpServletRequest request) {

        Archive archive=new Archive();
        try {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    BeanUtils.setProperty(archive, name, value);
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
                    archive.setFj(filename);
                    archive.setFilename(filename.substring(0,filename.lastIndexOf(".")));
                    System.out.println(archive.getFj()+savepath+filename.substring(0,filename.indexOf(".")));
                }
            }
            return archive;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    //根据id查询档案信息
     protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询数据并回显
         int id=WebUtils.parseInt(request.getParameter("id"),0);
         Archive archive=service.findById(id);
         request.setAttribute("archive",archive);
         request.getRequestDispatcher("/WEB-INF/archive/edit_archive.jsp").forward(request,response);
    }



    //编辑档案信息
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");


            Archive archive = fileUpload(request);

            service.updateArchive(archive);
            response.sendRedirect(request.getContextPath()+"/ArchiveServlet?action=list");//重定向防止重复提交哦

        } catch (Exception e) {
            e.printStackTrace();

        }
        return;
    }

    //删除档案信息
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteArchive(id);
        response.sendRedirect(request.getContextPath()+"/ArchiveServlet?action=list"); //重定向防止重复提交哦
    }
    ///删除我的档案
    protected void delete2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//根据id删除
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        service.deleteArchive(id);
        response.sendRedirect(request.getContextPath()+"/ArchiveServlet?action=myArchiveList"); //重定向防止重复提交哦
    }

    //根据档案内容查询
    protected void findByMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//条件查询

        String jg = request.getParameter("jg");
        List<Archive> list = service.findByMap(jg);

        for(Archive archive: list){
            Student student=studentService.queryById(archive.getSid());
            archive.setStudent(student);
        }
        if (null == list || list.size() == 0) {
            request.setAttribute("msg", "无此信息");
            request.getRequestDispatcher("/WEB-INF/archive/list_archive.jsp").forward(request, response);
        } else {
            request.removeAttribute("msg");
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/archive/list_archive.jsp").forward(request, response);
        }
    }
  //跳转到添加档案界面
    protected void toAddArchive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到添加用户界面
        request.getRequestDispatcher("/WEB-INF/archive/edit_archive.jsp").forward(request,response);
    }
}
