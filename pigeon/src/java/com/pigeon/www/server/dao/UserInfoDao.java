package com.pigeon.www.server.dao;

import com.pigeon.www.domain.UserInfo;

/**
 * 用户信息DAO接口
 * @author Jsming
 * 
 */
public interface UserInfoDao {
	/**
	 * 添加用户信息
	 * @param entity
	 * @return
	 */
	public boolean save(UserInfo entity);
	
	/**
	 * 修改用户信息
	 * @param entity
	 * @return
	 */
	public boolean update(UserInfo entity);
	
	/**
	 * 冻结用户信息
	 * @param key
	 * @return
	 */
	public boolean frozen(String key);
}
