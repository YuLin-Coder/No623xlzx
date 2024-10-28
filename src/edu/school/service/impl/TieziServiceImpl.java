package edu.school.service.impl;

import edu.school.dao.TieziDao;
import edu.school.dao.impl.TieziDaoImpl;
import edu.school.entity.Tiezi;
import edu.school.entity.PageTool;
import edu.school.service.TieziService;

import java.util.List;

public class TieziServiceImpl implements TieziService {
    private TieziDao dao=new TieziDaoImpl();
    @Override
    public int addTiezi(Tiezi record) {
        return dao.save(record);
    }

    @Override
    public Tiezi findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateTiezi(Tiezi record) {
        return dao.update(record);
    }

    @Override
    public List<Tiezi> getByPage(PageTool pageTool) {
        return dao.getByPage(pageTool);
    }

    @Override
    public List<Tiezi> findByMap(String title,String content) {
        return dao.findByMap(title,content);
    }


    @Override
    public int deleteTiezi(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryTieziCount() {
        return dao.queryCount();
    }

    @Override
    public List<Tiezi> findMyTiezi(int sid) {
        return dao.findMyTiezi(sid);
    }

    @Override
    public List<Tiezi> fidAll() {
        return dao.fidAll();
    }


}
