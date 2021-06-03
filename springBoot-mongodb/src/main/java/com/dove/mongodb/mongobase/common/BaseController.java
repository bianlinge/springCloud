package com.dove.mongodb.mongobase.common;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by E1041 on 2020/3/16.
 */
public class BaseController {
	public static JSONObject isSuccess(Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("msg", "success");
		jsonObject.put("data", data);
		return jsonObject;
	}

	public static JSONObject isSuccess() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "200");
		jsonObject.put("msg", "success");
		return jsonObject;
	}

	public static JSONObject isError(Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "300");
		jsonObject.put("msg", "fail");
		jsonObject.put("data", data);
		return jsonObject;
	}

	public static JSONObject isError() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "300");
		jsonObject.put("msg", "fail");
		return jsonObject;
	}

}
