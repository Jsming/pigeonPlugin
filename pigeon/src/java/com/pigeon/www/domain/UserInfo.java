package com.pigeon.www.domain;

import com.pigeon.www.common.UserStatus;
import com.pigeon.www.common.UserType;

/**
 * 用户详情实体
 * 
 * @author Jsming
 * 
 */
public class UserInfo {
	private String username;// 用户名
	private UserType type;//用户类型
	private UserStatus status;//用户状态
	private String head_img;// 头像
	private String sex;// 性别
	private long birthday;// 生日
	private String hobbies;// 兴趣
	private String signing;// 签名
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getSigning() {
		return signing;
	}
	public void setSigning(String signing) {
		this.signing = signing;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
