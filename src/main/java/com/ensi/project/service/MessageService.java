package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Message;
import com.ensi.project.model.User;

public interface MessageService {
	public void sendMessage(Message message);

	public void sendMessage(List<Message> messages);

	public List<Message> getAllMessages(User user);

	public List<Message> getunSeenMessages(User user);

	public Message getMessageById(int id);

	public void seenMessage(Message message);
}
