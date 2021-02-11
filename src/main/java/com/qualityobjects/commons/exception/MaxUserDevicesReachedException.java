package com.qualityobjects.commons.exception;

public class MaxUserDevicesReachedException extends QOException {

	private static final long serialVersionUID = 5207795630755628143L;

	public MaxUserDevicesReachedException(Integer maxUsers) {
		super(403 /* FORBIDDEN */, ErrorCodes.MAX_USERS_DEVICES, "Excedido el número máximo de "+ maxUsers +" dispositivos");
	}

}
