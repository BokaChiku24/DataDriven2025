package com.kunal.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHostAddress {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		String messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDriven2025/Extent_20Report/";
		System.out.println(messageBody);
	}

}
