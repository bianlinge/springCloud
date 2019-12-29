package com.imodule.synController.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E1041 on 2019/12/24.
 */
@RestController
public class EmployeeDataController {
	private static Logger log = LoggerFactory.getLogger(EmployeeDataController.class);

	@ApiOperation("获取雇员地址列表")
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public List<String> getAddresses() {
		log.info("get addresses Start");

		ArrayList<String> employeeAddresses = new ArrayList<String>();
		employeeAddresses.add("addr1");
		employeeAddresses.add("addr2");
		employeeAddresses.add("addr3");

		return employeeAddresses;
	}
	@ApiOperation("获取雇员电话列表")
	@RequestMapping(value = "/phones", method = RequestMethod.GET)
	public List<String> getPhoneNumbers() {
		log.info("get phones Start");

		ArrayList<String> phoneNumberList = new ArrayList<String>();

		phoneNumberList.add("100000");
		phoneNumberList.add("200000");

		return phoneNumberList;
	}
	@ApiOperation("获取雇员姓名列表")
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public List<String> getEmployeeName() {
		log.info("get names Start");

		List<String> employeeList = new ArrayList<String>();

		employeeList.add("Santa");
		employeeList.add("Banta");

		return employeeList;
	}
}
