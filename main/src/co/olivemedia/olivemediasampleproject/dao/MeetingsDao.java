package co.olivemedia.olivemediasampleproject.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.olivemedia.olivemediasampleproject.constants.MeetingsConstants;
import co.olivemedia.olivemediasampleproject.helper.DBHelper;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;

public class MeetingsDao implements MeetingsConstants {

	private static final String TAG = MeetingsDao.class.getName();

	private static MeetingsDao essentialClassDao;

	private DBHelper dataBaseHelper;

	private String SELECT_ALL_TABLE_DETAILS = new StringBuffer(" select *")
			.append(" from ").append(MeetingsConstants.DB_TABLE_NAME)
			.toString();

	private String SELECT_ROWS_COUNT = new StringBuffer(" select count(*)")
			.append(" from ").append(DB_TABLE_NAME).toString();

	public static MeetingsDao getInstance() {

		if (essentialClassDao == null) {

			essentialClassDao = new MeetingsDao();
		}

		return essentialClassDao;
	}

	public MeetingsDao() {

		dataBaseHelper = DBHelper.getInstance();

	}

	public void deleteMeetingsTable() {

		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		try {
			db.beginTransaction();

			db.delete(DB_TABLE_NAME, null, null);

			Log.d(TAG, "ROW DELETED SUCCESSFULLY");

			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG, "Error in  table delete");
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public List<Meetings> getAllMeetingsFromDb() {

		synchronized (DBHelper.lock) {

			SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

			List<Meetings> classModelObjectList = new ArrayList<Meetings>();

			Cursor cursor = db.rawQuery(new StringBuffer(
					SELECT_ALL_TABLE_DETAILS).toString(), null);

			try {

				db.beginTransaction();

				if (cursor != null) {

					if (cursor.moveToFirst()) {
						do {

							Meetings object = new Meetings();

							object.setTitle(cursor.getString(cursor
									.getColumnIndex(TITLE)));
							object.setTime(cursor.getString(cursor
									.getColumnIndex(TIME)));
							object.setLocation(cursor.getString(cursor
									.getColumnIndex(PLACE)));

							// Adding ads to list
							classModelObjectList.add(object);

						} while (cursor.moveToNext());
					}

				}

				db.setTransactionSuccessful();

			} catch (Exception e) {

				Log.e(TAG, "Exception while fetching from db", e);

			} finally {

				db.endTransaction();
				cursor.close();
				db.close();
			}

			return classModelObjectList;
		}

	}

	public void insertSingleMeetings(Meetings meetings) {

		synchronized (DBHelper.lock) {

			SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

			try {

				db.beginTransaction();
				ContentValues values = new ContentValues();

				if (UtilValidate.isNotNull(meetings.getLocation())) {
					values.put(PLACE, meetings.getLocation());
				}

				if (UtilValidate.isNotNull(meetings.getTitle())) {
					values.put(TITLE, meetings.getTitle());
				}

				if (UtilValidate.isNotNull(meetings.getTime())) {
					values.put(TIME, meetings.getTime());
				}

				// Inserting Row
				db.insert(DB_TABLE_NAME, null, values);

				db.setTransactionSuccessful();

			} catch (Exception e) {
				Log.e(TAG,
						"Exception while inserting in to EssentialClasses Table",
						e);
			} finally {
				db.endTransaction();
				db.close();
			}
		}

	}

	/**
	 * 
	 * @return
	 */
	public boolean isAnyMeetingInCurrentTable() {

		synchronized (DBHelper.lock) {
			SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
			boolean isExisting = false;
			Cursor cursor = null;

			try {
				cursor = db.rawQuery(
						new StringBuffer(SELECT_ROWS_COUNT).toString(), null);

				if (cursor != null) {

					Log.e(TAG, "cursor " + cursor);

					cursor.moveToFirst();

					if (cursor.getInt(0) == 0) {

						isExisting = false;
					} else {

						isExisting = true;

					}
				}

			} catch (Exception e) {
				// TODO: handle exception

				Log.e(TAG, "Exception in dao", e);

			} finally {

				cursor.close();
				db.close();

			}

			return isExisting;
		}
	}

}