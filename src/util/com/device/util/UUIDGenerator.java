package com.device.util;

import org.hibernate.id.UUIDHexGenerator;

public class UUIDGenerator {
	private static UUIDHexGenerator uuid = new UUIDHexGenerator();
	public static String generate(){
		return uuid.generate(null,null).toString();
	}
}
