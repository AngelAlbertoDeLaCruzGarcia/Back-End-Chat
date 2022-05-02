package com.backEnd.springboot.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "messages")
public class Messages implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Lob
	@Column(name = "message", nullable = false)
	private String message;
	@Column(name = "url", length = 2000, nullable = true)
	private String url;
	

	@Column(name = "create_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	@Column(name = "delete_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deleteAt;

	@NotNull(message = "El tipo de mensaje no puede ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_msgs_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TypeMsgs typeMsg;

	@NotNull(message = "El chat no debe pueder ser nulo")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "chats_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Chats chat;

	@NotNull(message = "El usuario no debe pueder ser nulo")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "persons_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Persons person;

	public Messages() {}

	public Messages(String message, String url, Date createAt, Date updateAt, Date deleteAt, TypeMsgs typeMsg, Chats chat,
			Persons person) {
		super();
		this.message = message;
		this.url = url;
		this.typeMsg = typeMsg;
		this.chat = chat;
		this.person = person;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public TypeMsgs getTypeMsg() {
		return typeMsg;
	}

	public void setTypeMsg(TypeMsgs typeMsg) {
		this.typeMsg = typeMsg;
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

	private final static long serialVersionUID = 1L;
}
