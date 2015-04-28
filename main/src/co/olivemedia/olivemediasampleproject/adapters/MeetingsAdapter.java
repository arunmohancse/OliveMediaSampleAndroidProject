package co.olivemedia.olivemediasampleproject.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import co.olivemedia.olivemediasampleproject.R;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;

public class MeetingsAdapter extends BaseAdapter {
	private final static String TAG = MeetingsAdapter.class.getName();

	private List<Meetings> meetingList;

	private LayoutInflater inflator;

	private Activity activity;

	public MeetingsAdapter(Activity activity, List<Meetings> meetingList) {

		this.meetingList = meetingList;
		if (activity != null)
			inflator = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.activity = activity;
	}

	@Override
	public int getCount() {
		Log.e(TAG, "" + meetingList.size());
		return meetingList.size();

	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		MeetingsHolder meetingsHolder = null;
		if (convertView == null) {
			meetingsHolder = new MeetingsHolder();

			convertView = inflator.inflate(R.layout.meetings_list_item, null);

			meetingsHolder.placeTextview = (TextView) convertView
					.findViewById(R.id.placeTextview);
			meetingsHolder.titleTextview = (TextView) convertView
					.findViewById(R.id.titleTextview);
			meetingsHolder.timeTextview = (TextView) convertView
					.findViewById(R.id.timeTextview);

			meetingsHolder.relative_parent = (RelativeLayout) convertView
					.findViewById(R.id.relative_parent);

			convertView.setTag(meetingsHolder);

		} else {
			meetingsHolder = (MeetingsHolder) convertView.getTag();
		}
		if (position % 2 == 0) {
			meetingsHolder.relative_parent.setBackgroundColor(activity
					.getResources().getColor(R.color.white));
		} else {
			meetingsHolder.relative_parent.setBackgroundColor(activity
					.getResources().getColor(R.color.light_grey));
		}

		if (UtilValidate.isNotNull(meetingList.get(position).getTitle())) {
			meetingsHolder.titleTextview.setText(meetingList.get(position)
					.getTitle());
		}

		if (UtilValidate.isNotNull(meetingList.get(position).getLocation())) {
			meetingsHolder.placeTextview.setText(meetingList.get(position)
					.getLocation());
		}

		if (UtilValidate.isNotNull(meetingList.get(position).getTime())) {
			meetingsHolder.timeTextview.setText(meetingList.get(position)
					.getTime());
		}

		return convertView;
	}

	static class MeetingsHolder {

		public TextView placeTextview;

		public TextView titleTextview;

		public TextView timeTextview;

		public RelativeLayout relative_parent;

	}

}
