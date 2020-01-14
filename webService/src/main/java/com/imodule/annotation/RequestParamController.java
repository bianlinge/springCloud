package com.imodule.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by E1041 on 2019/12/27.
 */
@RestController
public class RequestParamController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/deliver@RequestParam", method = RequestMethod.GET)
	public String requestParamdelivery(@RequestParam String encoding) throws IOException {
		String token = request.getHeader("token");
		Properties properties = new Properties();
		InputStream is = RequestParamController.class.getClassLoader().getResourceAsStream("requstHeader.properties");
		//字节流无法读取中文  会乱码 将字节流转成字符流
		InputStreamReader inputStreamReader = new InputStreamReader(is,"GBK");
		BufferedReader bf = new BufferedReader(inputStreamReader);
		properties.load(bf);
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerKey = headerNames.nextElement();
			String headerValue = request.getHeader(headerKey);
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				if (entry.getKey().equals(headerKey)) {
					headerKey = headerKey+"/"+entry.getValue();
				}
			}

			System.out.println(headerKey+"=="+headerValue);
		}
		String encodingString = restTemplate.getForObject("http://127.0.0.1:5050/deliver@RequestHeader?encoding=" + encoding, String.class);
		return encodingString;
	}
}
