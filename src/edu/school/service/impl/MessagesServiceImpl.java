package edu.school.service.impl;

import edu.school.dao.MessagesDao;
import edu.school.dao.impl.MessagesDaoImpl;
import edu.school.entity.PageTool;
import edu.school.entity.Messages;
import edu.school.service.MessagesService;

import java.util.List;

public class MessagesServiceImpl implements MessagesService {
    private MessagesDao dao=new MessagesDaoImpl();
    @Override
    public int addMessages(Messages record) {
        return dao.save(record);
    }

    @Override
    public Messages findById(Integer id) {
        return dao.query(id);
    }

    @Override
    public int updateMessages(Messages record) {
        return dao.update(record);
    }

    @Override
    public List<Messages> getByPage(PageTool pageTool) {
        return dao.getByPage(pageTool);
    }

    @Override
    public List<Messages> findByMap(String content) {
        return dao.findByMap(content);
    }


    @Override
    public int deleteMessages(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int queryMessagesCount() {
        return dao.queryCount();
    }

    @Override
    public List<Messages> findMyMessages(int sid) {
        return dao.findMyMessages(sid);
    }


}
