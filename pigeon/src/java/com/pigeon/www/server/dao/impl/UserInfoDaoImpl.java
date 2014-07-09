package com.pigeon.www.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.jivesoftware.database.DbConnectionManager;
import org.jivesoftware.util.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pigeon.www.common.UserStatus;
import com.pigeon.www.common.UserType;
import com.pigeon.www.domain.UserInfo;
import com.pigeon.www.server.dao.UserInfoDao;
import com.pigeon.www.utils.PlginInfo;

/**
 * 用户信息DAO
 * @author Jsming
 *
 */
public class UserInfoDaoImpl implements UserInfoDao {

	private static final Logger Log = LoggerFactory.getLogger(UserInfoDaoImpl.class);
	// 添加用户相信信息
	private final static String ADD_USER_INFO = "INSERT INTO gOfUserInfo (username, sex, birthday, hobbies, signing, type, status) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	// 修改用户相信信息
	private final static String UPDATE_USER_INFO = "UPDATE gOfUserInfo SET sex = ?, birthday = ?, hobbies = ?, signing = ? "
			+ "WHERE (username= ?)";
	// 冻结用户
	private final static String FROZEN_USER = "UPDATE gOfUserInfo SET status = ? WHERE (username= ?)";
	
	@Override
	public boolean save(UserInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(ADD_USER_INFO);
			pstmt.setString(1, entity.getUsername());
			pstmt.setString(2, entity.getSex());
			pstmt.setLong(3, entity.getBirthday());
			pstmt.setString(4, entity.getHobbies());
			pstmt.setString(5, entity.getSigning());
			pstmt.setInt(6, UserType.USER.getIndex());
			pstmt.setInt(7, UserStatus.ACTIVE.getIndex());
			pstmt.executeUpdate();

			flag = true;
		} catch (Exception e) {
			Log.error(LocaleUtils.getLocalizedString("user.add.error", PlginInfo.PLGIN_NAME), e);
			
			return false;
		} finally {
            DbConnectionManager.closeConnection(pstmt, con);
        }
		
		return flag;
	}

	@Override
	public boolean update(UserInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(UPDATE_USER_INFO);
			pstmt.setString(1, entity.getSex());
			pstmt.setLong(2, entity.getBirthday());
			pstmt.setString(3, entity.getHobbies());
			pstmt.setString(4, entity.getSigning());
			pstmt.setString(5, entity.getUsername());
			pstmt.executeUpdate();

			flag = true;
		} catch (Exception e) {
			Log.error(LocaleUtils.getLocalizedString("user.update.error", PlginInfo.PLGIN_NAME), e);

			return false;
		} finally {
            DbConnectionManager.closeConnection(pstmt, con);
        }
		
		return flag;
	}

	@Override
	public boolean frozen(String key) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(FROZEN_USER);
			pstmt.setInt(1, UserStatus.FROZEN.getIndex());
			pstmt.setString(2, key);
			pstmt.executeUpdate();

			flag = true;
		} catch (Exception e) {
			Log.error(LocaleUtils.getLocalizedString("user.frozen.error", PlginInfo.PLGIN_NAME), e);

			return false;
		} finally {
            DbConnectionManager.closeConnection(pstmt, con);
        }
		
		return flag;
	}

}
