package com.pigeon.www.server.face.impl;

import com.pigeon.www.domain.UserInfo;
import com.pigeon.www.server.dao.UserInfoDao;
import com.pigeon.www.server.dao.impl.UserInfoDaoImpl;
import com.pigeon.www.server.face.UserInfoService;

/**
 * 用户信息service实现
 * @author Jsming
 *
 */
public class UserInfoServiceImpl implements UserInfoService{
	private final static UserInfoDao dao = new UserInfoDaoImpl();
	
	@Override
	public boolean save(UserInfo entity) {
		// TODO Auto-generated method stub
		return dao.save(entity);
	}

	@Override
	public boolean update(UserInfo entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public boolean frozen(String key) {
		// TODO Auto-generated method stub
		return dao.frozen(key);
	}
	
}
