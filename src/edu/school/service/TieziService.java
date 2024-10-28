package edu.school.service;

import edu.school.entity.PageTool;
import edu.school.entity.Tiezi;

import java.util.List;

//帖子业务层
public interface TieziService {
    public int addTiezi(Tiezi record);//保存信息
    public Tiezi findById(Integer id);//根据id查询
    public int updateTiezi(Tiezi record);
    public List<Tiezi> getByPage(PageTool pageTool);//分页
    public List<Tiezi> findByMap(String title,String content);//条件查询
    public int deleteTiezi(Integer id);//根据id查询
    public int queryTieziCount();//统计数量
    public List<Tiezi> findMyTiezi(int sid);//条件查询
    public List<Tiezi> fidAll();

}
