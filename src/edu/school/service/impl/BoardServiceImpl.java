package edu.school.service.impl;

import edu.school.dao.BoardDao;
import edu.school.dao.impl.BoardDaoImpl;
import edu.school.entity.Board;
import edu.school.entity.PageTool;
import edu.school.service.BoardService;

import java.util.List;

public class BoardServiceImpl implements BoardService {
    private BoardDao dao=new BoardDaoImpl();
    @Override
    public int addBoard(Board record) {
        return dao.save(record);
    }

    @Override
    public Board findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateBoard(Board record) {
        return dao.update(record);
    }

    @Override
    public List<Board> findByMap(String title, String content) {
        return dao.findByMap(title,content);
    }

    @Override
    public int deleteBoard(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryCount() {
        return dao.queryCount();
    }

    @Override
    public List<Board> findAllByPage(PageTool pageTool) {
        return dao.findAllByPage(pageTool);
    }
}
