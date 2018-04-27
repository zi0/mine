package com.yedam.app.board;

import java.util.Date;

public class BindVO {

	String name;
	int age;
	Date birth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "BindVO [name=" + name + ", age=" + age + ", birth=" + birth + "]";
	}
	
	
}
