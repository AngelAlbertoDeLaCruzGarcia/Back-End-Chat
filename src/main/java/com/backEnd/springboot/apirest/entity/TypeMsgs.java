package com.backEnd.springboot.apirest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name = "type_msgs")
public class TypeMsgs implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;
	@Column(name = "create_at", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	@Column(name = "delete_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deleteAt;

	public TypeMsgs() {}

	public TypeMsgs(String name, Date createAt, Date updateAt, Date deleteAt) {
		super();
		this.name = name;
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

	private static final long serialVersionUID = 1L;
}
