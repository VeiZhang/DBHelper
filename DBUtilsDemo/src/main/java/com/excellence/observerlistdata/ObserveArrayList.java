package com.excellence.observerlistdata;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ZhangWei on 2016/6/12.
 */
public class ObserveArrayList<E> extends ArrayList<E>
{
	private OnEmptyListener mOnEmptyListener = null;

	public ObserveArrayList()
	{
		super();
		isEmpty();
	}

	public ObserveArrayList(int capacity)
	{
		super(capacity);
		isEmpty();
	}

	public ObserveArrayList(Collection collection)
	{
		super(collection);
		isEmpty();
	}

	@Override
	public boolean add(E object)
	{
		boolean isSuccess = super.add(object);
		isEmpty();
		return isSuccess;
	}

	@Override
	public void add(int index, E object)
	{
		super.add(index, object);
		isEmpty();
	}

	@Override
	public boolean addAll(Collection collection)
	{
		boolean isSuccess = super.addAll(collection);
		isEmpty();
		return isSuccess;
	}

	@Override
	public boolean addAll(int index, Collection collection)
	{
		boolean isSuccess = super.addAll(index, collection);
		isEmpty();
		return isSuccess;
	}

	@Override
	public void clear()
	{
		super.clear();
		isEmpty();
	}

	@Override
	public E remove(int index)
	{
		E object = super.remove(index);
		isEmpty();
		return object;
	}

	@Override
	public boolean remove(Object object)
	{
		boolean isSuccess = super.remove(object);
		isEmpty();
		return isSuccess;
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex)
	{
		super.removeRange(fromIndex, toIndex);
		isEmpty();
	}

	@Override
	public boolean removeAll(Collection collection)
	{
		boolean isSuccess = super.removeAll(collection);
		isEmpty();
		return isSuccess;
	}

	@Override
	public boolean isEmpty()
	{
		boolean isEmpty = super.isEmpty();
		if (mOnEmptyListener != null)
			mOnEmptyListener.isEmpty(isEmpty);
		return isEmpty;
	}

	public void setOnEmptyListener(OnEmptyListener onEmptyListener)
	{
		this.mOnEmptyListener = onEmptyListener;
	}

	public interface OnEmptyListener
	{
		void isEmpty(boolean isEmpty);
	}
}
