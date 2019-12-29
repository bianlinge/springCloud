package com.imodule.feign;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Configuration// Feign拦截器 在远程调用微服务时，自动给请求头上添加信息 例如
//访问服务时 要提供token验证
public class FeignInterceptor implements RequestInterceptor {

	private final String superToken = "ASDlove1234560";

	@Bean
	Request.Options feignOptions() {
		return new Request.Options(30000, 60000);
	}

	@Bean
	public ResponseEntityDecoder feignDecoder() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastConverter);
		return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		Map<String, String> headers = getHeaders(getHttpServletRequest());
		if (null == headers || !headers.keySet().contains("authorization") || StringUtils
				.isEmpty(headers.get("authorization"))) {
			requestTemplate.header("XRequestIDFilter.X_REQUEST_ID", UUID.randomUUID().toString());
			requestTemplate.header("across_auth", "true");
			requestTemplate.header("Authorization", superToken);
		} else {
			requestTemplate.header("Authorization", headers.get("authorization"));
			requestTemplate.header("XRequestIDFilter.X_REQUEST_ID", headers.get("x-request-id"));
		}

	}

	private HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	private Map<String, String> getHeaders(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		Map<String, String> map = new LinkedHashMap<>();
		Enumeration<String> enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement().toLowerCase();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
}
