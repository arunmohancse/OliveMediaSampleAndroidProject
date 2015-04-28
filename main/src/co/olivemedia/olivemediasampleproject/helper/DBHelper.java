package co.olivemedia.olivemediasampleproject.helper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import co.olivemedia.olivemediasampleproject.app.OliveMediaApp;
import co.olivemedia.olivemediasampleproject.constants.MeetingsConstants;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = DBHelper.class.getSimpleName();

	private static final String DB_NAME = "olive.db";

	private static final int DB_VERSION_NO = 1;

	private static DBHelper mInstance = null;

	public static final Object lock = new Object();

	private DBHelper() {

		super(OliveMediaApp.getCurrentcontext(), DB_NAME, null, DB_VERSION_NO);

	}

	public static DBHelper getInstance() {

		if (mInstance == null) {
			mInstance = new DBHelper();
			Log.e(TAG, "##################################"+OliveMediaApp.getCurrentcontext());
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(TAG, "in onCreate for creating db");

		db.execSQL(new StringBuilder(" CREATE TABLE ")
				.append(MeetingsConstants.DB_TABLE_NAME).append(" (")
				.append(MeetingsConstants.ID)
				.append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
				.append(MeetingsConstants.PLACE).append(" Varchar(20),")
				.append(MeetingsConstants.TITLE).append(" Varchar(20),")
				.append(MeetingsConstants.TIME)
				.append(" Varchar(20) ").append(");").toString());
		
		
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL(new StringBuilder(" DROP TABLE IF EXISTS ").append(
				MeetingsConstants.DB_TABLE_NAME).toString());


		onCreate(db);

	}
}
