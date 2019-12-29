package com.imodule.synController.entity;

/**
 * Created by E1041 on 2019/12/24.
 */
public class EmployeePhone {
	private String pbnone;

	public String getPbnone() {
		return pbnone;
	}

	public void setPbnone(String pbnone) {
		this.pbnone = pbnone;
	}

	@Override
	public String toString() {
		return "EmployeePhone{" +
				"pbnone='" + pbnone + '\'' +
				'}';
	}
}
