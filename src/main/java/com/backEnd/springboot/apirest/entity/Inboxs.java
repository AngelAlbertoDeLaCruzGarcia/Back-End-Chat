package com.backEnd.springboot.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "inboxs")
public class Inboxs implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "create_at", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date updateAt;

	@Column(name = "delete_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deleteAt;

	@ManyToOne
	@JoinColumn(name = "chats_id", referencedColumnName = "id")
	private Chats chat;

	@ManyToOne
	@JoinColumn(name = "persons_id", referencedColumnName = "id")
	Persons person;
	
	public Inboxs () {}

	public Inboxs(Date createAt, Date updateAt, Date deleteAt, Chats chat, Persons person) {
		super();
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
		this.chat = chat;
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}

	public Chats getChat() {
		return chat;
	}

	public void setChat(Chats chat) {
		this.chat = chat;
	}

	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	private static final long serialVersionUID = 1L;
}
