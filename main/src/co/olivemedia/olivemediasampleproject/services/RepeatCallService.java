package co.olivemedia.olivemediasampleproject.services;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import co.olivemedia.olivemediasampleproject.activities.MeetingsActivity;
import co.olivemedia.olivemediasampleproject.activities.MeetingsActivity.AsyncMeetingsCallBack;
import co.olivemedia.olivemediasampleproject.adapters.MeetingsAdapter;
import co.olivemedia.olivemediasampleproject.app.OliveMediaApp;
import co.olivemedia.olivemediasampleproject.constants.ApiConstants;
import co.olivemedia.olivemediasampleproject.dao.MeetingsDao;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.holders.MeetingsBaseHolder;
import co.olivemedia.olivemediasampleproject.managers.DbManager;
import co.olivemedia.olivemediasampleproject.managers.MeetingsManager;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;
import co.olivemedia.olivemediasampleproject.webservice.AsyncTaskCallBack;

public class RepeatCallService  extends Service {
	
	private AsyncMeetingsCallBack asyncMeetingsCallBack;
	Activity activity;
	private int REQUEST_CODE = 0;

	 @Override
	 public IBinder onBind(Intent arg0) {
	  // TODO Auto-generated method stub
	  return null;
	 }
	 
	 @Override
	 public int onStartCommand(Intent intent, int flags, int startId) {
		 asyncMeetingsCallBack=new AsyncMeetingsCallBack();
		 Log.e("", "API CALL IN SERVICE");
		 MeetingsManager.getInstance().getMeetings(activity,
					asyncMeetingsCallBack, REQUEST_CODE);
	  
	  return  Service.START_NOT_STICKY;
	 }
	 
		public class AsyncMeetingsCallBack implements AsyncTaskCallBack {
			Meetings meetings = new Meetings();

			@Override
			public void onFinish(int responseCode, Object result) {

				MeetingsBaseHolder meetingsBaseHolder = (MeetingsBaseHolder) result;

				if (UtilValidate.isNotNull(meetingsBaseHolder)) {

					MeetingsDao.getInstance().deleteMeetingsTable();
					
					
					for (Meetings meetings : meetingsBaseHolder.getList()) {
						
						
						DbManager.getInstance().insertSingleEssentialExercises(
								meetings);
						
					
					
					}
					Intent i = new Intent("android.intent.action.MAIN").putExtra("fromIntent", meetingsBaseHolder);
					sendBroadcast(i);
					


				}
			}

			@Override
			public void onFinish(int responseCode, String result) {

		
				
				if (result.equals(ApiConstants.NO_INTERNET)) {
					
				}

			}

		}

	}