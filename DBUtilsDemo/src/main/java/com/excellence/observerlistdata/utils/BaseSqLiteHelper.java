package com.excellence.observerlistdata.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Set;

/**
 * Created by ZhangWei on 2016/7/8.
 */
public abstract class BaseSqLiteHelper extends SQLiteOpenHelper
{
	protected BaseSqLiteHelper(Context context, String name, int version)
	{
		super(context, name, null, version);
	}

	protected boolean createTable(SQLiteDatabase db, String tableName, TableAttrs attrs)
	{
		if (attrs == null || attrs.keySet().size() <= 0)
			return false;
		Set<String> keys = attrs.keySet();
		StringBuilder builder = new StringBuilder("create table if not exists ");
		builder.append(tableName);
		builder.append(" (");
		for (String key : keys)
		{
			builder.append(key);
			builder.append(" ");
			builder.append(attrs.getAttr(key));
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		try
		{
			db.execSQL(builder.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized long insert(String tableName, ContentValues values)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.insert(tableName, null, values);
	}

	public synchronized int delete(String tableName, String whereClause, String[] whereArgs)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.delete(tableName, whereClause, whereArgs);
	}

	public synchronized int update(String tableName, ContentValues values, String whereClause, String[] whereArgs)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.update(tableName, values, whereClause, whereArgs);
	}

	public synchronized Cursor query(String tableName, String whereSql, String[] whereValue, String orderBy)
	{
		/**
		 * query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
		 * 方法各参数的含义：
		 * table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。
		 * columns：要查询出来的列名。相当于select语句select关键字后面的部分。
		 * selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符“?”
		 * selectionArgs ：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。
		 * groupBy：相当于select语句group by关键字后面的部分 having：相当于select语句having关键字后面的部分
		 * orderBy：相当于select语句order by关键字后面的部分，如：personid desc, age asc;
		 * limit：指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分。
		 */
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = null;
		cursor = db.query(tableName, null, whereSql, whereValue, null, null, orderBy);
		return cursor;
	}

	public synchronized Cursor query(String whereSql, String[] whereValue)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = null;
		cursor = db.rawQuery(whereSql, whereValue);
		return cursor;
	}

	public int getCount(String tableName)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(tableName,  null, null, null, null, null, null);
		if (cursor != null)
		{
			cursor.close();
			return cursor.getCount();
		}
		return 0;
	}

	public boolean dropTable(SQLiteDatabase db, String tableName)
	{
		try
		{
			db.execSQL("drop table if exists " + tableName);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void clearTable(String tableName)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("delete from " + tableName);
		revertSeq(tableName);
	}

	/**
	 * 还原自增
	 */
	public void revertSeq(String tableName)
	{
		String sql = "update sqlite_sequence set seq=0 where name='" + tableName + "'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	public boolean isTableExist(SQLiteDatabase db, String tableName)
	{
		Cursor cursor = null;
		try
		{
			String sql = "select count(*) from " + tableName;
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext())
			{
				if (cursor.getInt(0) > 0)
					return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cursor != null)
				cursor.close();
		}
		return false;
	}

	public boolean checkColumnExist(String tableName, String columnName)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = null;
		boolean result = false;
		cursor = db.rawQuery("select * from " + tableName + " limit 0", null);
		result = cursor != null && cursor.getColumnIndex(columnName) != -1;
		if (cursor != null)
			cursor.close();
		return result;
	}

	public void beginTransaction()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
	}

	public void endTransaction()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.setTransactionSuccessful();
		db.endTransaction();
	}
}
