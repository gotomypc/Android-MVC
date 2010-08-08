/**
 * ErrorActivity.java $version 1.0 Mar 11, 2010
 * 
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

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
