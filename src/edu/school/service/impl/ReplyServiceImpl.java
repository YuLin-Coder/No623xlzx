package edu.school.service.impl;

import edu.school.dao.ReplyDao;
import edu.school.dao.impl.ReplyDaoImpl;
import edu.school.entity.Reply;
import edu.school.entity.PageTool;
import edu.school.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao dao=new ReplyDaoImpl();
    @Override
    public int addReply(Reply record) {
        return dao.save(record);
    }

    @Override
    public Reply findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateReply(Reply record) {
        return dao.update(record);
    }

    @Override
    public List<Reply> getByPage(PageTool pageTool) {
        return dao.getByPage(pageTool);
    }

    @Override
    public List<Reply> findByMap(String content) {
        return dao.findByMap(content);
    }


    @Override
    public int deleteReply(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryReplyCount() {
        return dao.queryCount();
    }

    @Override
    public List<Reply> findMyReply(int sid) {
        return dao.findMyReply(sid);
    }

    @Override
    public List<Reply> findTeacherReply(int teacher_id) {
        return dao.findTeacherReply(teacher_id);
    }


}
