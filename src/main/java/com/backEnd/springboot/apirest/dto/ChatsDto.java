package com.backEnd.springboot.apirest.dto;

import java.util.Date;

public class ChatsDto {

	
	private String name;
	private Date createAt;
	private Date updateAt;
	private Date deleteAt;

	public ChatsDto() {}

	public ChatsDto(String name, Date createAt, Date updateAt, Date deleteAt) {
		super();
		this.name = name;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
