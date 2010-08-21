package com.mastergaurav.android.common.view;

import android.os.Bundle;
import android.widget.TextView;

public class ErrorActivity extends BaseActivity
{
	@Override
	protected void onCreateContent(Bundle savedInstanceState)
	{
		TextView tv = new TextView(this);
		tv.setText("This is error...");

		setContentView(tv);
	}

	@Override
	protected int getID()
	{
		return 23456;
	}
}
