package edu.school.dao;

import edu.school.entity.PageTool;

import edu.school.entity.Tiezi;

import java.util.List;
import java.util.Map;

//帖子
public interface TieziDao {
    public int save(Tiezi record);//保存信息
    public Tiezi query(Integer id);//根据id查询
    public int update(Tiezi record);
    public List<Tiezi> getByPage(PageTool pageTool);//分页
    public List<Tiezi> findByMap( String title,String content);//条件查询
    public int delete(Integer id);//根据id查询
    public int queryCount();//统计数量
    public List<Tiezi> findMyTiezi(int sid);//条件查询
    public List<Tiezi> fidAll();

}
