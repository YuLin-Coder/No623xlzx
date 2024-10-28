package edu.school.service;

import edu.school.entity.Board;
import edu.school.entity.PageTool;

import java.util.List;

//公告
public interface BoardService {
    public int addBoard(Board record);//保存信息
    public Board findById(Integer id);//根据id查询
    public int updateBoard(Board record);
    public List<Board> findByMap(String title, String content);//条件查询
    public int deleteBoard(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Board> findAllByPage(PageTool pageTool);//分页

}
