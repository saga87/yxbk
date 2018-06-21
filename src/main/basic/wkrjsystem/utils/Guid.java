package wkrjsystem.utils;

import java.util.UUID;

public class Guid {

	public static String getGuid() {
		return UUID.randomUUID().toString();
	}
	
}
