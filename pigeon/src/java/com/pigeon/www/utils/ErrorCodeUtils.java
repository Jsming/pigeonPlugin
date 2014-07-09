package com.pigeon.www.utils;

import org.xmpp.packet.IQ;

import com.google.gson.Gson;
import com.pigeon.www.common.ErrorCode;
public class ErrorCodeUtils {
	private static final Gson gson = new Gson();
	
	public static void addErrorCode(IQ iq, ErrorCode code){
		iq.getElement().addAttribute("error", gson.toJson(code));
	}
}
