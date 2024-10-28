package edu.school.service;

import edu.school.entity.Archive;
import edu.school.entity.PageTool;

import java.util.List;

//档案业务层
public interface ArchiveService {
    public int addArchive(Archive record);//保存信息
    public Archive findById(Integer id);//根据id查询
    public Archive findBySid(Integer sid);//根据sid查询
    public int updateArchive(Archive record);
    public List<Archive> getByPage(PageTool pageTool);//分页
    public List<Archive> findByMap(String content);//条件查询
    public int deleteArchive(Integer id);//根据id查询
    public int queryArchiveCount();//统计数量
    List<Archive> findMyArchive(int sid);//根据sid查询档案
}
