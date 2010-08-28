package com.mastergaurav.android.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.mastergaurav.android.common.log.Logger;
import com.mastergaurav.android.mvc.common.IResponseListener;
import com.mastergaurav.android.mvc.common.Request;
import com.mastergaurav.android.mvc.common.Response;
import com.mastergaurav.android.mvc.controller.Controller;

public abstract class BaseActivity extends Activity implements IResponseListener
{
	private static final String TAG = "BaseActivity";
	private View mainView;
	private MenuItem selectedItem = null;

	private static final int DIALOG_ID_PROGRESS_DEFAULT = 0x174980;

	@Override
	protected final void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		notifiyControllerActivityCreating();

		onBeforeCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		onCreateContent(savedInstanceState);

		notifiyControllerActivityCreated();

		onAfterCreate(savedInstanceState);
	}

	public Controller getController()
	{
		return (Controller) getApplication();
	}

	private void notifiyControllerActivityCreating()
	{
		getController().onActivityCreating(this);
	}

	private void notifiyControllerActivityCreated()
	{
		getController().onActivityCreated(this);
	}

	protected void onBeforeCreate(Bundle savedInstanceState)
	{
	}

	protected void onAfterCreate(Bundle savedInstanceState)
	{
	}

	protected void onCreateContent(Bundle savedInstanceState)
	{
		LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

		int rid = getContentViewID();
		if(rid != -1)
		{
			mainView = inflator.inflate(getContentViewID(), null, false);
		} else
		{
			mainView = createContentView();
		}
		setContentView(mainView);
	}

	protected View createContentView()
	{
		return null;
	}

	protected int getContentViewID()
	{
		return -1;
	}

	protected final View getMainView()
	{
		return mainView;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if(hasCustomOptionsMenu())
		{
			return createCustomOptionsMenu(menu);
		}
		return createDefaultOptionsMenu(menu);
	}

	@Override
	public final boolean onOptionsItemSelected(MenuItem item)
	{
		int itemId = item.getItemId();

		System.out.println("Context Item Selected... " + item.getTitle());

		boolean result = onOptionsItemSelected(itemId);
		if(result)
		{
			if(selectedItem != null)
			{
				selectedItem.setIcon(optionMenuIds[selectedItem.getItemId()][1]);
			}
			item.setIcon(optionMenuIds[itemId][2]);
			selectedItem = item;
		}

		return true;
	}

	protected final void deselectAllOptionMenuItems()
	{
		if(selectedItem != null)
		{
			selectedItem.setIcon(optionMenuIds[selectedItem.getItemId()][1]);
			selectedItem = null;
		}
	}

	protected boolean onOptionsItemSelected(int itemId)
	{
		return true;
	}

	private int[][] optionMenuIds = {
	/*
	 * { R.string.title_of_item_01, R.drawable.icon_deselected_item_01,
	 * R.drawable.icon_selected_item_01 }
	 */
	};

	private boolean createDefaultOptionsMenu(Menu menu)
	{
		MenuItem item;

		for(int i = 0; i < optionMenuIds.length; i++)
		{
			item = menu.add(0, i, i, optionMenuIds[i][0]);
			item.setIcon(optionMenuIds[i][1]);
		}

		return true;
	}

	protected boolean hasCustomOptionsMenu()
	{
		return false;
	}

	protected boolean createCustomOptionsMenu(Menu menu)
	{
		return false;
	}

	public void onError(Response response)
	{
	}

	public void onSuccess(Response response)
	{
	}

	public void preProcessData(Response response)
	{
		Logger.i(TAG, "LoginActivity//preProcessData");

		Logger.i(TAG, "Response: " + response);
		Logger.i(TAG, "Activity ID: " + response.getTargetActivityID());
		Logger.i(TAG, "Tag: " + response.getTag());
		Logger.i(TAG, "Data: " + response.getData());
	}

	public void processData(Response response)
	{
		Logger.i(TAG, "LoginActivity//processData");

		Logger.i(TAG, "Response: " + response);
		Logger.i(TAG, "Activity ID: " + response.getTargetActivityID());
		Logger.i(TAG, "Tag: " + response.getTag());
		Logger.i(TAG, "Data: " + response.getData());
	}

	protected void showProgress()
	{
		showDialog(DIALOG_ID_PROGRESS_DEFAULT);
	}

	protected void hideProgress()
	{
		try
		{
			removeDialog(DIALOG_ID_PROGRESS_DEFAULT);
		} catch(IllegalArgumentException iae)
		{
		}
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
			case DIALOG_ID_PROGRESS_DEFAULT:
				ProgressDialog dlg = new ProgressDialog(this);
				// dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dlg.setMessage("Working...");
				dlg.setCancelable(true);
				return dlg;
			default:
				return super.onCreateDialog(id);

		}
	}

	protected final void go(int commandID, Request request)
	{
		Logger.i(TAG, "go with cmdid=" + commandID);
		go(commandID, request, true);
	}

	protected final void go(int commandID, Request request, boolean showProgress)
	{
		Logger.i(TAG, "go with cmdid=" + commandID + ", request: " + request);
		go(commandID, request, true, true);
	}

	protected final void go(int commandID, Request request, boolean showProgress, boolean record)
	{
		Logger.i(TAG, "go with cmdid=" + commandID + ", record: " + record + ", request: " + request);
		go(commandID, request, true, true, false);
	}

	protected final void go(int commandID, Request request, boolean showProgress, boolean record, boolean resetStack)
	{
		if(showProgress)
		{
			showProgress();
		}
		Logger.i(TAG, "go with cmdid=" + commandID + ", record: " + record + ",rs: " + resetStack + ", request: "
				+ request);
		getController().go(commandID, request, this, record, resetStack);
	}

	protected final void back()
	{
		Logger.i(TAG, "Got a message to go back");
		getController().back();
	}

	@Override
	public void onBackPressed()
	{
		back();
	}
}
