package edu.school.dao;

import edu.school.entity.PageTool;
import edu.school.entity.Messages;

import java.util.List;

//留言
public interface MessagesDao {
    public int save(Messages record);//保存信息
    public Messages query(Integer id);//根据id查询
    public int update(Messages record);
    public List<Messages> getByPage(PageTool pageTool);//分页
    public List<Messages> findByMap(String content);//条件查询
    public int delete(Integer id);//根据id查询
    public int queryCount();//统计数量
    public List<Messages> findMyMessages(int sid);//条件查询

}
