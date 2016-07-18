package com.excellence.observerlistdata.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ZhangWei on 2016/7/11.
 */
public abstract class BaseGridAdapter<T> extends BaseAdapter
{
	private Context mContext = null;
	private List<T> mData = null;
	private int mLayoutId;

	public BaseGridAdapter(Context context, List<T> data, int layoutId)
	{
		mContext = context;
		mData = data;
		mLayoutId = layoutId;
	}

	@Override
	public int getCount()
	{
		return mData == null ? 0 : mData.size();
	}

	@Override
	public T getItem(int position)
	{
		return mData == null ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId);
		convert(viewHolder, mData.get(position), position);
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder viewHolder, T item, int position);

	public void setData(List<T> data)
	{
		mData = data;
		notifyDataSetChanged();
	}
}
