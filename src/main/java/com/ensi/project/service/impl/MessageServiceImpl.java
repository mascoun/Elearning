package com.ensi.project.service.impl;

import java.util.List;

import com.ensi.project.dao.MessageDao;
import com.ensi.project.model.Message;
import com.ensi.project.model.User;
import com.ensi.project.service.MessageService;

public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;

	public void sendMessage(Message message) {
		messageDao.send(message);

	}

	public void sendMessage(List<Message> messages) {
		messageDao.send(messages);
	}

	public List<Message> getAllMessages(User user) {
		return messageDao.findAllMessages(user);
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
