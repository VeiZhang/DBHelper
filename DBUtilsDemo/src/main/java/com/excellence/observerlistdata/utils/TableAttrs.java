package com.excellence.observerlistdata.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZhangWei on 2016/7/11.
 */
public class TableAttrs
{
	private Map<String, String> mValues = new LinkedHashMap<>();

	public void addAttr(String key, String value)
	{
		mValues.put(key, value);
	}

	public String getAttr(String key)
	{
		return mValues.get(key);
	}

	public Set<String> keySet()
	{
		return mValues.keySet();
	}
}
