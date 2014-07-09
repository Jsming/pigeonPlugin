package com.pigeon.www.common;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * IQType转换器
 * @author Jsming
 *
 */
public class EnumSerializer implements JsonSerializer<IQType>, JsonDeserializer<IQType> {

	@Override
	public IQType deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		if (json.getAsInt() < IQType.values().length)  
            return IQType.values()[json.getAsInt()];  
        return null; 
	}

	@Override
	public JsonElement serialize(IQType type, Type arg1,
			JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		 return new JsonPrimitive(type.ordinal());
	}

}
