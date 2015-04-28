package co.olivemedia.olivemediasampleproject.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import co.olivemedia.olivemediasampleproject.R;
import co.olivemedia.olivemediasampleproject.constants.ApiConstants;
import co.olivemedia.olivemediasampleproject.dao.MeetingsDao;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.holders.MeetingsBaseHolder;
import co.olivemedia.olivemediasampleproject.managers.DbManager;
import co.olivemedia.olivemediasampleproject.managers.MeetingsManager;
import co.olivemedia.olivemediasampleproject.utils.NetChecker;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;
import co.olivemedia.olivemediasampleproject.webservice.AsyncTaskCallBack;

public class SplashscreenActivity extends Activity {
	private final static String TAG = SplashscreenActivity.class.getName();
	private int mProgressStatus = 0;
	private Handler mHandler = new Handler();
	private ProgressBar mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		initViews();
		initManagers();

		// Start lengthy operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (mProgressStatus < 100) {

					mProgressStatus = mProgressStatus + 1;

					// sleep 2 seconds, so that you can see the 100%
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {

					}

					// Update the progress bar
					mHandler.post(new Runnable() {
						public void run() {

							mProgress.setProgress(mProgressStatus);

						}
					});
				}

			}
		}).start();
		
		if (!(NetChecker.isConnected(SplashscreenActivity.this))) {

			if (!(NetChecker.isConnectedWifi(SplashscreenActivity.this) && NetChecker
					.isConnectedMobile(SplashscreenActivity.this))) {
				if (MeetingsDao.getInstance().isAnyMeetingInCurrentTable()) {

					Intent i = new Intent(SplashscreenActivity.this,
							MeetingsActivity.class);
					startActivity(i);
					finish();
				} else {
					Toast.makeText(SplashscreenActivity.this,
							"Please check your internet connection...",
							Toast.LENGTH_LONG).show();
					Handler handler = new Handler(); 
				    handler.postDelayed(new Runnable() { 
				         public void run() { 
				        		Toast.makeText(SplashscreenActivity.this,
										"Please check your internet connection...",
										Toast.LENGTH_LONG).show();
				        	    finish();
								
				         } 
				    }, 2000); 
				
				
				}
			}
		}
		else
		{
			Intent i = new Intent(SplashscreenActivity.this,
					MeetingsActivity.class);
			startActivity(i);
			finish();
			
		}
	}

	private void initManagers() {
		// TODO Auto-generated method stub

	}

	private void initViews() {
		// TODO Auto-generated method stub

		mProgress = (ProgressBar) findViewById(R.id.roundProgress);

	}

}