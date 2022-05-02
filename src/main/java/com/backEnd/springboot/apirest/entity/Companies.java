package com.backEnd.springboot.apirest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "companies")
public class Companies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	@Column(name = "street", length = 60, nullable = false)
	private String street;
	@Column(name = "num_ext", length = 60, nullable = false)
	private String num_ext;
	@Column(name = "num_int", length = 60, nullable = false)
	private String num_int;
	@Column(name = "size", length = 25, nullable = false)
	private String size;
	@NotNull(message="la región no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Industries_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Industries industries;
	
	public Companies() {
		
	}
	

	public Companies(String name, String street, String num_ext, String num_int, String size, Industries industries) {
		super();
		this.name = name;
		this.street = street;
		this.num_ext = num_ext;
		this.num_int = num_int;
		this.size = size;
		this.industries = industries;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNum_ext() {
		return num_ext;
	}

	public void setNum_ext(String num_ext) {
		this.num_ext = num_ext;
	}

	public String getNum_int() {
		return num_int;
	}

	public void setNum_int(String num_int) {
		this.num_int = num_int;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Industries getIndustries() {
		return industries;
	}

	public void setIndustries(Industries industries) {
		this.industries = industries;
	}
	

}
