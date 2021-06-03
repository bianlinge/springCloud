package com.dove.mongodb.mongobase.common;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

/**
 * Created by E1041 on 2020/3/9.
 */
public class Person implements Serializable {

	/*原来是id这个字段的问题，在java class中增加一个id属性，它会自动映射成"_id"，
	如果数据库中本来就是id，那么就读不到了
		解决方法是在id字段上加上注解
		@Field("id") 显示的指定它对应的属性名*/
	@Id
	private String id;
	@Indexed
	private String name;
	private Integer age;
	private String sex;
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
