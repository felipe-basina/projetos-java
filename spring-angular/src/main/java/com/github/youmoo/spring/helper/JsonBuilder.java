package com.github.youmoo.spring.helper;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 一个简单的json构造器
 * 
 * @autor youmoo
 * @since 2014-06-14 下午5:46
 */
public class JsonBuilder {
	Map<String, Object> json = ObjectFactory.newHashMap();

	public JsonBuilder put(String key, Object value) {
		json.put(key, value);
		return this;
	}

	public String build() {
		System.out.println("\n\n\t :: " + JSONObject.toJSONString(json) + "\n\n");
		return JSONObject.toJSONString(json);
	}
}
