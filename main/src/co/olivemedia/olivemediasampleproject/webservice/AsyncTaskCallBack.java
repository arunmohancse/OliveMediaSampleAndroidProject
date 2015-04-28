package co.olivemedia.olivemediasampleproject.webservice;

import android.app.Activity;

public interface AsyncTaskCallBack {

	public void onFinish(int responseCode, Object result);

	public void onFinish(int responseCode, String result);
	


	
}
