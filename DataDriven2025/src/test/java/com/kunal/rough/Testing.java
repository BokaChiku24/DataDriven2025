package com.kunal.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Testing {
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		FileInputStream fs;
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/ObjectRepository.properties");
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("bmlButon"));
		
	}

}
