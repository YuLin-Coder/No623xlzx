package edu.school.service;

import edu.school.entity.Reply;
import edu.school.entity.PageTool;

import java.util.List;

//回复业务层
public interface ReplyService {
    public int addReply(Reply record);//保存信息
    public Reply findById(Integer id);//根据id查询
    public int updateReply(Reply record);
    public List<Reply> getByPage(PageTool pageTool);//分页
    public List<Reply> findByMap(String content);//条件查询
    public int deleteReply(Integer id);//根据id查询
    public int queryReplyCount();//统计数量
    public List<Reply> findMyReply(int sid);//条件查询
    public List<Reply> findTeacherReply(int teacher_id);//条件查询

}
