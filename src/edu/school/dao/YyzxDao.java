package edu.school.dao;

import edu.school.entity.Yyzx;
import edu.school.entity.PageTool;

import java.util.List;
import java.util.Map;

//预约咨询
public interface YyzxDao {
    public int save(Yyzx record);//保存信息
    public Yyzx query(Integer yid);//根据id查询
    public int update(Yyzx record);
    public int updateStaus(Yyzx record);//更改状态
    List<Map<String, Object>> findByMap(String yysj);//条件查询
    public int delete(Integer yid);//根据id数量
    public int queryCount();//统计数量
    List<Map<String, Object>> selectdoubleList(PageTool pageTool);//多表查询
    public List<Yyzx> findMyYyzx(int s_id);//条件查询
    List<Map<String, Object>> findMyTname(String tname);//根据教师姓名查询预约的学生
}
