package com.ensi.project.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ensi.project.dao.MessageDao;
import com.ensi.project.model.Message;
import com.ensi.project.model.User;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void send(Message message) {
		getSessionFactory().getCurrentSession().save(message);
	}

	@Transactional
	public void send(List<Message> messages) {
		for (Message message : messages) {
			send(message);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Message> findAllMessages(User user) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Message.class);
		criteria.add(Restrictions.eq("to.id", user.getId()));
		return criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findunSeenMessages(User user) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Message.class);
		criteria.add(Restrictions.eq("to.id", user.getId()));
		criteria.add(Restrictions.eq("seen", false));
		return criteria.list();
	}

	public Message findMessageById(int id) {
		Message message = (Message) getSessionFactory().getCurrentSession().get(Message.class, id);
		return message;
	}

	public void hasSeenMessage(Message message) {
		message.setSeen(true);
		getSessionFactory().getCurrentSession().merge(message);
	}

}
