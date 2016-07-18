package com.excellence.observerlistdata.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ZhangWei on 2016/7/8.
 */
public class DBHelpUtil extends BaseSqLiteHelper
{
	public static final String DB_NAME = "Info.db";
	public static final int DB_VERSION = 2;

	private static DBHelpUtil mInstance = null;

	public static synchronized DBHelpUtil getInstance(Context context)
	{
		if (mInstance == null)
			mInstance = new DBHelpUtil(context.getApplicationContext());
		return mInstance;
	}

	private DBHelpUtil(Context context)
	{
		super(context, DB_NAME, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		/**
		 * 这个方法 1、在第一次打开数据库的时候才会走
		 * 2、在清除数据之后再次运行-->打开数据库，这个方法会走
		 * 3、没有清除数据，不会走这个方法
		 * 4、数据库升级的时候这个方法不会走
		 */
		 createTable(db, DBConstants.PERSON_INFO_TABLE, DBConstants.sPERSON_TABLE_ATTRS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		/**
		 * 数据库升级操作
		 * 1、第一次创建数据库的时候，这个方法不会走
		 * 2、清除数据后再次运行(相当于第一次创建)这个方法不会走
		 * 3、数据库已经存在，而且版本升高的时候，这个方法才会调用
		 */
		dropTable(db, DBConstants.PERSON_INFO_TABLE);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		/**
		 * 执行数据库的降级操作
		 * 1、只有新版本比旧版本低的时候才会执行
		 * 2、如果不执行降级操作，会抛出异常
		 */
		onUpgrade(db, oldVersion, newVersion);
	}
}
