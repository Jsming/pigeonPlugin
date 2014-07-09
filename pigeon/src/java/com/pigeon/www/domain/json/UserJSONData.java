package com.pigeon.www.domain.json;

import com.pigeon.www.common.IQType;
import com.pigeon.www.domain.UserInfo;


public class UserJSONData {

	private IQType type;
	private UserInfo user;
	
	public IQType getType() {
		return type;
	}
	public void setType(IQType type) {
		this.type = type;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	
}
