package com.mastergaurav.android.common.view;

import com.mastergaurav.android.mvc.controller.Controller;

import android.os.Bundle;
import android.widget.TextView;

public class ProgressActivity extends BaseActivity
{
	@Override
	protected void onCreateContent(Bundle savedInstanceState)
	{
		TextView tv = new TextView(this);
		tv.setText("This is progress...");

		setContentView(tv);
	}

	@Override
	protected int getID()
	{
		return Controller.ACTIVITY_ID_PROGRESS;
	}
}
