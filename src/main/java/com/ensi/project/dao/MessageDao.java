package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Message;
import com.ensi.project.model.User;

public interface MessageDao {
	public void send(Message message);

	public void send(List<Message> messages);

	public List<Message> findAllMessages(User user);
	
	public List<Message> findunSeenMessages(User user);

	public Message findMessageById(int id);

	public void hasSeenMessage(Message message);

}
