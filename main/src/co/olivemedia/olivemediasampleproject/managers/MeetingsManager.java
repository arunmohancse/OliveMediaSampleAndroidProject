package co.olivemedia.olivemediasampleproject.managers;

import java.io.ByteArrayInputStream;

import org.apache.http.Header;

import android.app.Activity;
import co.olivemedia.olivemediasampleproject.constants.ApiConstants;
import co.olivemedia.olivemediasampleproject.holders.MeetingsBaseHolder;
import co.olivemedia.olivemediasampleproject.utils.NetChecker;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;
import co.olivemedia.olivemediasampleproject.webservice.AsyncTaskCallBack;
import co.olivemedia.olivemediasampleproject.webservice.RestClient;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MeetingsManager {

	private static final String TAG = MeetingsManager.class.getSimpleName();

	private static MeetingsManager mInstance = null;

	private MeetingsBaseHolder meetingsBaseHolder;

	public static MeetingsManager getInstance() {

		if (mInstance == null) {
			mInstance = new MeetingsManager();
		}
		return mInstance;
	}

	public void getMeetings(final Activity activity,
			final AsyncTaskCallBack asyncTaskCallBack, final int requestCode) {

		meetingsBaseHolder = new MeetingsBaseHolder();

		RestClient.get(ApiConstants.BASE_URL, null, null,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1,
							byte[] response) {

						UtilValidate
								.getStringFromInputStream(new ByteArrayInputStream(
										response));

						MeetingsBaseHolder meetingsBaseHolder = new MeetingsBaseHolder();
						Gson gson = new Gson();
						String responseBody = UtilValidate
								.getStringFromInputStream(new ByteArrayInputStream(
										response));
						meetingsBaseHolder = gson.fromJson(responseBody,
								MeetingsBaseHolder.class);
						if (UtilValidate.isNotNull(asyncTaskCallBack))

						{
							asyncTaskCallBack.onFinish(requestCode,
									meetingsBaseHolder);
						}
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {

                        if (!(NetChecker.isConnected(activity))) {

                            if (!(NetChecker.isConnectedWifi(activity) && NetChecker
                                    .isConnectedMobile(activity))) {
                        		if (UtilValidate.isNotNull(asyncTaskCallBack))

        						{

                            	asyncTaskCallBack.onFinish(requestCode, ApiConstants.NO_INTERNET);
        						}
                            }

                        } else {
                    		if (UtilValidate.isNotNull(asyncTaskCallBack))

    						{

                        	asyncTaskCallBack.onFinish(requestCode, ApiConstants.FAILED);
    						}
                        }

                    }
                });
    }
}
