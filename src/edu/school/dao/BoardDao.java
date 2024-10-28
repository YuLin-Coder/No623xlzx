package edu.school.dao;

import edu.school.entity.Board;
import edu.school.entity.PageTool;
import edu.school.entity.Board;

import java.util.List;

//公告
public interface BoardDao {
    public int save(Board record);//保存信息
    public Board query(Integer id);//根据id查询
    public int update(Board record);
    public List<Board> findByMap(String title, String content);//条件查询
    public int delete(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Board> findAllByPage(PageTool pageTool);//分页

}
