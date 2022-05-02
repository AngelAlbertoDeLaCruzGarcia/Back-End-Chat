package com.backEnd.springboot.apirest.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.springframework.lang.Nullable;

import javax.validation.constraints.PastOrPresent;


public class MessagesDto {
	@NotBlank
	private String message;
	
	private String url;
	
	@PastOrPresent
	private Date createAt;

	@PastOrPresent
	private Date updateAt;
	
	@PastOrPresent
	private Date deleteAt;	
	
	@Nullable
	private int chat;

	@Positive
	private int receiver;
	
	@Positive
	private int sender;
	
	@NotBlank
	private String typeMsg;
	
	public MessagesDto() {}

	public MessagesDto(String message, String url, Date createAt, Date updateAt,
		 Date deleteAt, int chat, int receiver,int sender, String typeMsg) {
		super();
		this.message = message;
		this.url = url;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
		this.chat = chat;
		this.receiver = receiver;
		this.sender = sender;
		this.typeMsg = typeMsg;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getChat() {
		return chat;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}


	public String getTypeMsg() {
		return typeMsg;
	}

	public void setTypeMsg(String typeMsg) {
		this.typeMsg = typeMsg;
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
}
