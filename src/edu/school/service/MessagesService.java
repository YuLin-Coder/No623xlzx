package edu.school.service;

import edu.school.entity.PageTool;
import edu.school.entity.Messages;

import java.util.List;

//留言业务层
public interface MessagesService {
    public int addMessages(Messages record);//保存信息
    public Messages findById(Integer id);//根据id查询
    public int updateMessages(Messages record);
    public List<Messages> getByPage(PageTool pageTool);//分页
    public List<Messages> findByMap(String content);//条件查询
    public int deleteMessages(Integer id);//根据id查询
    public int queryMessagesCount();//统计数量
    public List<Messages> findMyMessages(int sid);//条件查询

}
