package edu.school.dao;

import edu.school.entity.Reply;
import edu.school.entity.PageTool;

import java.util.List;

//回复
public interface ReplyDao {
    public int save(Reply record);//保存信息
    public Reply query(Integer id);//根据id查询
    public int update(Reply record);
    public List<Reply> getByPage(PageTool pageTool);//分页
    public List<Reply> findByMap(String content);//条件查询
    public int delete(Integer id);//根据id查询
    public int queryCount();//统计数量
    public List<Reply> findMyReply(int sid);//条件查询
    public List<Reply> findTeacherReply(int teacher_id);//条件查询
}
