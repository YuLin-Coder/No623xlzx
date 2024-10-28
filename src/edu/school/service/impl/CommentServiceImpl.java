package edu.school.service.impl;

import edu.school.dao.CommentDao;
import edu.school.dao.impl.CommentDaoImpl;
import edu.school.entity.Comment;
import edu.school.entity.PageTool;
import edu.school.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao dao=new CommentDaoImpl();
    @Override
    public int addComment(Comment record) {
        return dao.save(record);
    }

    @Override
    public Comment findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateComment(Comment record) {
        return dao.update(record);
    }

    @Override
    public List<Comment> getByPage(PageTool pageTool) {
        return dao.getByPage(pageTool);
    }

    @Override
    public List<Comment> findByMap(String content) {
        return dao.findByMap(content);
    }


    @Override
    public int deleteComment(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryCommentCount() {
        return dao.queryCount();
    }

    @Override
    public List<Comment> findMyComment(int sid) {
        return dao.findMyComment(sid);
    }

    @Override
    public List<Comment> findStudentComment(int teacher_id) {
        return dao.findStudentComment(teacher_id);
    }

    @Override
    public int queryTieziCommentCount(int tiezi_id) {
        return dao.queryTieziCommentCount(tiezi_id);
    }

    @Override
    public int queryTeacherCommentCount(int teacher_id) {
        return dao.queryTeacherCommentCount(teacher_id);
    }

    @Override
    public List<Comment> findStudentCommentByTieziId(int tiezi_id) {
        return dao.findStudentCommentByTieziId(tiezi_id);
    }


}
