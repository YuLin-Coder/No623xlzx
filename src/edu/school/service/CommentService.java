package edu.school.service;

import edu.school.entity.Comment;
import edu.school.entity.PageTool;

import java.util.List;

//评论业务层
public interface CommentService {
    public int addComment(Comment record);//保存信息
    public Comment findById(Integer id);//根据id查询
    public int updateComment(Comment record);
    public List<Comment> getByPage(PageTool pageTool);//分页
    public List<Comment> findByMap(String content);//条件查询
    public int deleteComment(Integer id);//根据id查询
    public int queryCommentCount();//统计数量
    public List<Comment> findMyComment(int sid);//条件查询
    public List<Comment> findStudentComment(int teacher_id);//条件查询
    public int queryTieziCommentCount(int tiezi_id);//统计数量
    public int queryTeacherCommentCount(int  teacher_id);//统计数量
    public List<Comment> findStudentCommentByTieziId(int tiezi_id);//根据帖子的id查询学生评论信息
}
