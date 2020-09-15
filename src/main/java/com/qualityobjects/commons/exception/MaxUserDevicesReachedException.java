package com.qualityobjects.commons.exception;

import org.springframework.http.HttpStatus;

public class MaxUserDevicesReachedException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public MaxUserDevicesReachedException(Integer max_users) {
		super(HttpStatus.FORBIDDEN.value(), ErrorCodes.MAX_USERS_DEVICES, "Excedido el número máximo de "+ max_users +" dispositivos");
	}

}