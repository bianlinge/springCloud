package com.imodule.synController.controller;

import com.imodule.synController.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by E1041 on 2019/12/24.
 */
@RestController
@Api(tags = "异步请求测试")
public class AsyncController {
	private static Logger log = LoggerFactory.getLogger(AsyncController.class);
	@Autowired
	private AsyncService service;

	@ApiOperation("异步请求总接口")
	@RequestMapping(value = "/testAsynch", method = RequestMethod.GET)
	public List<String> testAsynch() throws InterruptedException, ExecutionException {
		log.info("testAsynch Start");

		CompletableFuture<List<String>> employeeAddress = service.getEmployeeAddress();
		CompletableFuture<List<String>> employeeName = service.getEmployeeName();
		CompletableFuture<List<String>> employeePhone = service.getEmployeePhone();

		// 等待每个异步调用都完成  allof等待所有异步线程任务结束
		CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();

		log.info("EmployeeAddress--> " + employeeAddress.get());
		log.info("EmployeeName--> " + employeeName.get());
		log.info("EmployeePhone--> " + employeePhone.get());
		ArrayList<String> resultList = new ArrayList<>();

		List<String> address = employeeAddress.get();
		List<String> names = employeeName.get();
		List<String> phones = employeePhone.get();
		resultList.addAll(address);
		resultList.addAll(names);
		resultList.addAll(phones);
		return resultList;
	}
}
