package com.backEnd.springboot.apirest.dto;


import javax.validation.constraints.NotBlank;


public class CompaniesDto {
	@NotBlank
	private String name;
	@NotBlank
	private String street;
	@NotBlank
	private String num_ext;
	@NotBlank
	private String num_int;
	@NotBlank
	private String size;
	@NotBlank
	private IndustriesDto industries;
	
	public CompaniesDto() {
		
	}

	public CompaniesDto(@NotBlank String name, @NotBlank String street, @NotBlank String num_ext,
			@NotBlank String num_int, @NotBlank String size, @NotBlank IndustriesDto industries) {
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

	public IndustriesDto getIndustries() {
		return industries;
	}

	public void setIndustries(IndustriesDto industries) {
		this.industries = industries;
	}
	
}
