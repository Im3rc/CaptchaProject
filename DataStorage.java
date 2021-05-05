package com.CaptchaProject.app;

import java.util.HashMap;

public class DataStorage {
	  private static HashMap<String, String> captcha = new HashMap<String, String>();

	  public void putDataMap(String s) {
	         captcha.put("key",s);
	    }  
	  
	  public static HashMap<String, String> getDataMap() {
	         return captcha;
	    }
}
