package com.pigeon.www.handler.iq;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.jivesoftware.util.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pigeon.www.common.EnumSerializer;
import com.pigeon.www.common.ErrorCode;
import com.pigeon.www.common.IQType;
import com.pigeon.www.domain.UserInfo;
import com.pigeon.www.domain.json.UserJSONData;
import com.pigeon.www.server.face.UserInfoService;
import com.pigeon.www.server.face.impl.UserInfoServiceImpl;
import com.pigeon.www.utils.ErrorCodeUtils;
import com.pigeon.www.utils.PlginInfo;

public class UserInfoHandler extends IQHandler {
	private static final Logger Log = LoggerFactory.getLogger(UserInfoHandler.class);
	private IQHandlerInfo info;
	private static UserInfoService service = new UserInfoServiceImpl();
	private final static String NAMESPACE = "pigeon:user:info";
	
	public UserInfoHandler() {
		super("UserInfoHandler");
		info = new IQHandlerInfo("query", NAMESPACE);
	}
	
	@Override
	@SuppressWarnings("static-access")
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		// TODO Auto-generated method stub
		IQ result = packet.createResultIQ(packet);
		String message = packet.getChildElement().element("body")
				.getText();
		
		if(StringUtils.isEmpty(message)){
			result.setType(IQ.Type.error);
			ErrorCodeUtils.addErrorCode(result, new ErrorCode());
			
			return result;
		}
		
		UserInfo info = null;
		IQType handlerType = null;
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(IQType.class, new EnumSerializer());
			Gson gson = gsonBuilder.create();  
			UserJSONData data = gson.fromJson(message, UserJSONData.class);
			handlerType = data.getType();
			info = data.getUser();
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("");
			result.setType(IQ.Type.error);
			
			return result;
		}

		// 兴趣不能为空，为空直接返回结果
		if (info != null && StringUtils.isBlank(info.getHobbies())) {
			result.getElement().addAttribute(
					"alert",
					LocaleUtils.getLocalizedString("message.hobbies.isNotNull",
							PlginInfo.PLGIN_NAME));
			result.setType(IQ.Type.error);

			return result;
		}
		
		boolean flag = false;
		//处理
		if(IQType.ADD.equals(handlerType)){
			flag = addInfo(info);
		}else if(IQType.UPDATE.equals(handlerType)){
			flag = updateInfo(info);
		}else if(IQType.FROZEN.equals(handlerType)){
			flag = frozenInfo(info.getUsername());
		}
		
		if (flag) {
			result.setType(IQ.Type.result);
		}else {
			result.setType(IQ.Type.error);
		}
		
		return result;
	}
	
	/**
	 * 添加用户信息
	 * @return
	 */
	private boolean addInfo(UserInfo entity) {
		return service.save(entity);
	}
	
	/**
	 * 更新用户信息
	 * @return
	 */
	private boolean updateInfo(UserInfo entity){
		return service.update(entity);
	}
	
	/**
	 * 冻结用户
	 * @return
	 */
	private boolean frozenInfo(String key){
		return service.frozen(key);
	}

	@Override
	public IQHandlerInfo getInfo() {
		// TODO Auto-generated method stub
		return info;
	}
}
