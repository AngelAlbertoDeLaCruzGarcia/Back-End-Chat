package com.backEnd.springboot.apirest.dto;

import javax.validation.constraints.AssertTrue;

public class NotificationsDto{
	
	@AssertTrue
	private boolean display;
	
	public NotificationsDto() {}
	
	public NotificationsDto(boolean display) {
		super();
		this.display = display;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}
	
}
