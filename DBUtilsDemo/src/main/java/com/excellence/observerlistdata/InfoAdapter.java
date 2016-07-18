package com.excellence.observerlistdata;

import android.content.Context;

import com.excellence.observerlistdata.utils.BaseGridAdapter;
import com.excellence.observerlistdata.utils.ViewHolder;

import java.util.List;

/**
 * Created by ZhangWei on 2016/7/11.
 */
public class InfoAdapter extends BaseGridAdapter<MainActivity.Person>
{
	public InfoAdapter(Context context, List<MainActivity.Person> data, int layoutId)
	{
		super(context, data, layoutId);
	}

	@Override
	public void convert(ViewHolder viewHolder, MainActivity.Person item, int position)
	{
		viewHolder.setText(R.id.name_text, item.name);
		viewHolder.setText(R.id.phone_text, item.phone);
	}

}
