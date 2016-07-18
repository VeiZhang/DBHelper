package com.excellence.observerlistdata;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.excellence.observerlistdata.utils.DBConstants;
import com.excellence.observerlistdata.utils.DBHelpUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	private Button mAddBtn = null;
	private Button mDeleteBtn = null;
	private EditText mNameEditText = null;
	private EditText mPhoneEditText = null;
	private GridView mGridView = null;
	private List<Person> mData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initData();
		setOnListener();
	}

	private void initView()
	{
		mAddBtn = (Button) findViewById(R.id.add_btn);
		mDeleteBtn = (Button) findViewById(R.id.del_btn);
		mNameEditText = (EditText) findViewById(R.id.edit_name);
		mPhoneEditText = (EditText) findViewById(R.id.edit_phone);
		mGridView = (GridView) findViewById(R.id.gridView);
	}

	private void initData()
	{
		mData = new ArrayList<>();
		getDBData();
		mGridView.setAdapter(new InfoAdapter(this, mData, R.layout.info_item));
	}

	private void getDBData()
	{
		Cursor cursor = DBHelpUtil.getInstance(this).query(DBConstants.PERSON_INFO_TABLE, null, null, null);
		if (cursor == null)
			return;
		while (cursor.moveToNext())
		{
			Person person = new Person(cursor.getString(cursor.getColumnIndex(DBConstants.PERSON_NAME)), cursor.getString(cursor.getColumnIndex(DBConstants.PERSON_NUMBER)));
			mData.add(person);
		}
		cursor.close();
	}

	private void setOnListener()
	{
		mAddBtn.setOnClickListener(this);
		mDeleteBtn.setOnClickListener(this);
	}

	private void delData()
	{
		if (mNameEditText.getText().toString().trim().length() <= 0)
			return;
		DBHelpUtil.getInstance(this).delete(DBConstants.PERSON_INFO_TABLE, DBConstants.PERSON_NAME + "=?", new String[] { mNameEditText.getText().toString() });
		getDBData();
		((InfoAdapter) mGridView.getAdapter()).setData(mData);
	}

	private void addData()
	{
		if (mNameEditText.getText().toString().trim().length() <= 0 || mPhoneEditText.getText().toString().trim().length() <= 0)
			return;
		ContentValues values = new ContentValues();
		values.put(DBConstants.PERSON_NAME, mNameEditText.getText().toString());
		values.put(DBConstants.PERSON_NUMBER, mPhoneEditText.getText().toString());
		DBHelpUtil.getInstance(this).insert(DBConstants.PERSON_INFO_TABLE, values);
		getDBData();
		((InfoAdapter) mGridView.getAdapter()).setData(mData);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.add_btn:
			addData();
			break;

		case R.id.del_btn:
			delData();
			break;
		}
	}

	class Person
	{
		String name;
		String phone;

		public Person(String name, String phone)
		{
			this.name = name;
			this.phone = phone;
		}
	}
}
