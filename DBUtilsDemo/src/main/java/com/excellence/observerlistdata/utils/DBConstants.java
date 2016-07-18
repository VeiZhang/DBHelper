package com.excellence.observerlistdata.utils;

/**
 * Created by ZhangWei on 2016/7/11.
 */
public class DBConstants
{
	/************************* 自增ID ****************************/
	public static final String ID = "id";

	/************************* 联系人 ***************************/
	public static final String PERSON_INFO_TABLE = "person_info";
	public static final String PERSON_NAME = "person_name";
	public static final String PERSON_NUMBER = "person_number";
	public static final TableAttrs sPERSON_TABLE_ATTRS = new TableAttrs()
	{
		{
			addAttr(ID, "int primary key");
			addAttr(PERSON_NAME, "text");
			addAttr(PERSON_NUMBER, "text");
		}
	};
}
