package co.olivemedia.olivemediasampleproject.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import co.olivemedia.olivemediasampleproject.R;
import co.olivemedia.olivemediasampleproject.adapters.MeetingsAdapter;
import co.olivemedia.olivemediasampleproject.constants.ApiConstants;
import co.olivemedia.olivemediasampleproject.dao.MeetingsDao;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.holders.MeetingsBaseHolder;
import co.olivemedia.olivemediasampleproject.managers.DbManager;
import co.olivemedia.olivemediasampleproject.services.RepeatCallService;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;
import co.olivemedia.olivemediasampleproject.webservice.AsyncTaskCallBack;

public class MeetingsActivity  extends Activity {
	private final static String TAG = MeetingsActivity.class.getName();
	private List<Meetings>meetingsList;
	private MeetingsAdapter meetingsAdapter;
	private ListView meetings_listview;
	private AsyncMeetingsCallBack asyncMeetingsCallBack;
	MeetingsBaseHolder meetingsBaseHolder;
	private int REQUEST_CODE = 0;
	//Timer myTimer = new Timer();
	//MyTimerTask myTimerTask= new MyTimerTask();
	private BroadcastReceiver mReceiver;

 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meetings_page);
		
		
		
		initViews();
		initManagers();
		Calendar cal = Calendar.getInstance();
		AlarmManager alarmManager=(AlarmManager) MeetingsActivity.this.getSystemService(Context.ALARM_SERVICE);
		
		Intent intent = new Intent(this, RepeatCallService.class);
		  PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
		  startService(intent);

		  // Start every 5 minutes
		  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),
		    10000, pintent);
		
		//myTimer.scheduleAtFixedRate(myTimerTask, 0, 10000); //(timertask,delay,period)
		
	/*	AlarmManager alarmManager=(AlarmManager) MeetingsActivity.this.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(MeetingsActivity.this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(MeetingsActivity.this, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),10000,
		                                                                      pendingIntent);
*/
		/*MeetingsManager.getInstance().getMeetings(MeetingsActivity.this,
				asyncMeetingsCallBack, REQUEST_CODE);*/
  
		meetingsList=DbManager.getInstance().getAllMeetingsFromDb();
		if(UtilValidate.isNotNull(meetingsList))
		{
		
		meetingsAdapter = new MeetingsAdapter(MeetingsActivity.this, meetingsList);
		meetings_listview.setAdapter(meetingsAdapter);
		meetingsAdapter.notifyDataSetChanged();
		
		
		}
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		
		IntentFilter intentFilter = new IntentFilter(
				"android.intent.action.MAIN");
 
		mReceiver = new BroadcastReceiver() {
 
			@Override
			public void onReceive(Context context, Intent intent) {
				//extract our message from intent
				meetingsBaseHolder= (MeetingsBaseHolder) intent.getSerializableExtra("fromIntent");
				//log our message value
				if(UtilValidate.isNotNull(meetingsBaseHolder))
				{
					if(UtilValidate.isNotNull(meetingsBaseHolder.getList()))
					{
						meetingsAdapter = new MeetingsAdapter(MeetingsActivity.this, meetingsBaseHolder.getList());
						meetings_listview.setAdapter(meetingsAdapter);
						meetingsAdapter.notifyDataSetChanged();
					}
				}
				Log.e("","IN ONRESUME"+meetingsBaseHolder.getList().size());
 
			}
		};
		//registering our receiver
		this.registerReceiver(mReceiver, intentFilter);
	}
 

		
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//unregister our receiver
		this.unregisterReceiver(this.mReceiver);
	}
		

	private void initManagers() {
		// TODO Auto-generated method stub
		meetingsList=new ArrayList<Meetings>();
		asyncMeetingsCallBack = new AsyncMeetingsCallBack();
		meetingsAdapter = new MeetingsAdapter(MeetingsActivity.this, meetingsList);
		meetings_listview.setAdapter(meetingsAdapter);
		meetingsBaseHolder=new MeetingsBaseHolder();
	
	}

	private void initViews() {
		// TODO Auto-generated method stub
		meetings_listview=(ListView)findViewById(R.id.meetings_listview);
		
	}
	
		 

/*	public  class AlarmReceiver extends BroadcastReceiver
	{   
	    public AlarmReceiver() {
	    	super();
	    }


	 @Override
	 public void onReceive(Context context, Intent intent)
	  {   
	    //get and send location information
		 Log.e(TAG, "Api call>>>>");
		 MeetingsManager.getInstance().getMeetings(MeetingsActivity.this,
					asyncMeetingsCallBack, REQUEST_CODE);
	  }
	}
	*/
/*	private class MyTimerTask extends TimerTask {
	    @Override
	    public void run() {           
	      //get and send location information 
	    	MeetingsManager.getInstance().getMeetings(MeetingsActivity.this,
					asyncMeetingsCallBack, REQUEST_CODE);
	    }
	}*/
	
	/**
	 * AsyncMeetingsCallBack api call
	 */
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
				
				meetingsAdapter = new MeetingsAdapter(MeetingsActivity.this,  meetingsBaseHolder.getList());
				meetings_listview.setAdapter(meetingsAdapter);
				meetingsAdapter.notifyDataSetChanged();


			}
		}

		@Override
		public void onFinish(int responseCode, String result) {

	
			
			if (result.equals(ApiConstants.NO_INTERNET)) {
				
			}

		}

	}
}