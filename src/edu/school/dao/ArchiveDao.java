package edu.school.dao;

import edu.school.entity.Archive;
import edu.school.entity.Comment;
import edu.school.entity.PageTool;

import java.util.List;

//档案
public interface ArchiveDao {
    public int save(Archive record);//保存信息
    public Archive query(Integer id);//根据id查询
    public Archive query2(Integer sid);//根据id查询
    public int update(Archive record);
    public List<Archive> findByMap(String jg);//条件查询
    public int delete(Integer id);//根据id数量
    public int queryCount();//统计数量
    public List<Archive> getByPage(PageTool pageTool);//分页
    public List<Archive> findMyArchive(int sid);//根据sid查询档案
}
