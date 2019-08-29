package com.mohan.project.easytools.common;

import java.util.UUID;

/**
 * UUID相关工具类
 * @author mohan
 * @date 2019-07-08
 */
public class UUIDTools {
	
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}

	public static String getUUID32(){
		return UUID.randomUUID ().toString().replace("-", "");
	}

}
