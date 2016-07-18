package com.excellence.observerlistdata.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ZhangWei on 2016/7/11.
 */
public class ViewHolder
{
	private Context mContext = null;
	private View mConvertView = null;
	private SparseArray<View> mViews = null;

	public ViewHolder(Context context, ViewGroup parent, int layoutId)
	{
		mContext = context;
		mViews = new SparseArray<>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId)
	{
		if (convertView == null)
		{
			return new ViewHolder(context, parent, layoutId);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView()
	{
		return mConvertView;
	}

	public <T extends View> T getView(int viewId)
	{
		View view = mViews.get(viewId);
		if (view == null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public ViewHolder setText(int viewId, String str)
	{
		TextView textView = getView(viewId);
		textView.setText(str);
		return this;
	}
}
