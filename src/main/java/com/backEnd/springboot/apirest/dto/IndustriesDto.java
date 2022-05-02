package com.backEnd.springboot.apirest.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;


public class IndustriesDto {

	@NotBlank
	private String industrie;
	@NotBlank
	private Date createAt;
	@NotBlank
	private Date updateAt;
	@NotBlank
	private Date deleteAt;
	public IndustriesDto() {

	}


	public IndustriesDto(@NotBlank String industrie, @NotBlank Date createAt, @NotBlank Date updateAt,
			@NotBlank Date deleteAt) {
		super();
		this.industrie = industrie;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
	}


	public String getIndustrie() {
		return industrie;
	}

	public void setIndustrie(String industrie) {
		this.industrie = industrie;
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
