package com.dove.web.asynchronous_request.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by E1041 on 2019/12/24.
 */
@Service
public class AsyncService {
	private static Logger log = LoggerFactory.getLogger(AsyncService.class);
	@Autowired
	private RestTemplate restTemplate;

	@Async("asyncExecutor")
	public CompletableFuture<List<String>> getEmployeeName() throws InterruptedException {
		log.info("getEmployeeName starts");

		List<String> employeeNameData = restTemplate.getForObject("http://localhost:5050/names", List.class);

		log.info("employeeNameData, {}", employeeNameData);
		Thread.sleep(2000L); // Intentional delay
		log.info("employeeNameData completed");
		return CompletableFuture.completedFuture(employeeNameData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<String>> getEmployeeAddress() throws InterruptedException {
		log.info("getEmployeeAddress starts");

		List<String> employeeAddressData = restTemplate.getForObject("http://localhost:5050/addresses", List.class);

		log.info("employeeAddressData, {}", employeeAddressData);
		Thread.sleep(1000L); // Intentional delay
		log.info("employeeAddressData completed");
		return CompletableFuture.completedFuture(employeeAddressData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<String>> getEmployeePhone() throws InterruptedException {
		log.info("getEmployeePhone starts");

		List<String> employeePhoneData = restTemplate.getForObject("http://localhost:5050/phones", List.class);

		log.info("employeePhoneData, {}", employeePhoneData);
		Thread.sleep(30000L); // Intentional delay
		log.info("employeePhoneData completed");
		return CompletableFuture.completedFuture(employeePhoneData);
	}

}
