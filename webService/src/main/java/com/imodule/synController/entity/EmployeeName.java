package com.imodule.synController.entity;

/**
 * Created by E1041 on 2019/12/24.
 */
public class EmployeeName {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeName{" +
				"name='" + name + '\'' +
				'}';
	}
}
