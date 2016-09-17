package com.ensi.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	private int idMessage;
	private User from;
	private User to;
	private String object;
	private String body;
	private Date date;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	@OneToOne
	@JoinColumn(name = "from_id", nullable = false)
	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	@OneToOne
	@JoinColumn(name = "to_id", nullable = false)
	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	@Column(name = "object")
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Column(name = "body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
