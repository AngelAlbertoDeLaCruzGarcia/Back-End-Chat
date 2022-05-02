package com.backEnd.springboot.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Notifications")
public class Notifications implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "El titulo no puede ser nulo")
	@Column(name = "title", length = 50, nullable = false, updatable = false)
	private String title;
	
	@Column(name = "description", length = 100, nullable = true, updatable = false)
	private String description;
	
	@Column(name = "display")
	private boolean display;

	@Column(name = "create_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "chats_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Chats chat;

	@NotNull(message = "El usuario remitente no puede ser nulo")
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Persons receiver;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Persons sender;
	
	public Notifications() {}
	
	public Notifications(String title, String description, Date createAt, Date updateAt,
			boolean display, Chats chat, Persons receiver,Persons sender) {
		super();
		this.title = title;
		this.description = description;
		this.display = display;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.chat = chat;
		this.receiver = receiver;
		this.sender = sender;
	}
	
	public Chats getChat() {
		return chat;
	}

	public void setChat(Chats chat) {
		this.chat = chat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Persons getReceiver() {
		return receiver;
	}

	public void setReceiver(Persons receiver) {
		this.receiver = receiver;
	}

	public Persons getSender() {
		return sender;
	}

	public void setSender(Persons sender) {
		this.sender = sender;
	}

	private static final long serialVersionUID = 1L;
}
