package edu.school.service.impl;

import edu.school.dao.ArchiveDao;
import edu.school.dao.impl.ArchiveDaoImpl;
import edu.school.entity.PageTool;
import edu.school.entity.Archive;
import edu.school.service.ArchiveService;

import java.util.List;

public class ArchiveServiceImpl implements ArchiveService {
    private ArchiveDao dao=new ArchiveDaoImpl();
    @Override
    public int addArchive(Archive record) {
        return dao.save(record);
    }

    @Override
    public Archive findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public Archive findBySid(Integer sid) {
        return dao.query2(sid);
    }

    @Override
    public int updateArchive(Archive record) {
        return dao.update(record);
    }

    @Override
    public List<Archive> getByPage(PageTool pageTool) {
        return dao.getByPage(pageTool);
    }

    @Override
    public List<Archive> findByMap(String content) {
        return dao.findByMap(content);
    }


    @Override
    public int deleteArchive(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryArchiveCount() {
        return dao.queryCount();
    }

    @Override
    public List<Archive> findMyArchive(int sid) {
        return dao.findMyArchive(sid);
    }


}
