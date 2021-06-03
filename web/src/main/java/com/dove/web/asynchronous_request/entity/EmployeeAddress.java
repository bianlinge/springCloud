package com.dove.web.asynchronous_request.entity;

/**
 * Created by E1041 on 2019/12/24.
 */
public class EmployeeAddress {
	private String address;

	@Override
	public String toString() {
		return "EmployeeAddress{" +
				"address='" + address + '\'' +
				'}';
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
