package edu.school.service.impl;

import edu.school.dao.YyzxDao;
import edu.school.dao.impl.YyzxDaoImpl;
import edu.school.entity.PageTool;
import edu.school.entity.Yyzx;
import edu.school.service.YyzxService;

import java.util.List;
import java.util.Map;

public class YyzxServiceImpl implements YyzxService {
    private YyzxDao dao =new YyzxDaoImpl();
    @Override
    public int addYyzx(Yyzx record) {
        return dao.save(record);
    }

    @Override
    public Yyzx findById(Integer yid) {
        return dao.query(yid);
    }

    @Override
    public int updateYyzx(Yyzx record) {
        return dao.update(record);
    }

    @Override
    public List<Map<String, Object>> findByMap(String yysj) {
        return dao.findByMap(yysj);
    }


    @Override
    public int deleteYyzx(Integer yid) {
        return dao.delete(yid);
    }

    @Override
    public int queryCount() {
        return dao.queryCount();
    }

    @Override
    public List<Map<String, Object>> selectdoubleList(PageTool pageTool) {
        return dao.selectdoubleList(pageTool);
    }

    @Override
    public List<Yyzx> findMyYyzx(int s_id) {
        return dao.findMyYyzx(s_id);
    }

    @Override
    public int updateStaus(Yyzx record) {
        return dao.updateStaus(record);
    }

    @Override
    public List<Map<String, Object>> findMyTname(String tname) {
       return dao.findMyTname(tname);
    }


}
