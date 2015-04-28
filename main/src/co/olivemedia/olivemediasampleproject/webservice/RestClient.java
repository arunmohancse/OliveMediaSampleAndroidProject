package co.olivemedia.olivemediasampleproject.webservice;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import android.content.Context;
import android.util.Log;
import co.olivemedia.olivemediasampleproject.utils.UtilValidate;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RestClient {

	private static final String Tag = RestClient.class.getName();

	private static AsyncHttpClient client = new AsyncHttpClient();

	/**
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params,
			Map<String, String> headers,
			AsyncHttpResponseHandler responseHandler) {

		if (UtilValidate.isNotEmpty(headers)) {
			for (Entry<String, String> header : headers.entrySet()) {
				client.addHeader(header.getKey(), header.getValue());
			}
		}
	

		/*
		 * client.setBasicAuth("worldcup", "coconut", new AuthScope(
		 * AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM));
		 */
		client.get(url, params, responseHandler);
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @param ctx
	 * @param headers
	 * @param responseHandler
	 */
	public static void post(String url, RequestParams params, Context ctx,
			Map<String, String> headers,
			AsyncHttpResponseHandler responseHandler) {

		if (UtilValidate.isNotEmpty(headers)) {
			for (Entry<String, String> header : headers.entrySet()) {
				client.addHeader(header.getKey(), header.getValue());
			}
		}

		/*
		 * client.setBasicAuth("worldcup", "coconut", new AuthScope(
		 * AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM));
		 */
		Log.i(Tag, "Request url : " + url);
		client.post(ctx, url, params, responseHandler);

	}

	/**
	 * 
	 * @param url
	 * @param multipartEntity
	 * @param mime
	 * @param ctx
	 * @param headers
	 * @param responseHandler
	 */
	public static void post(String url, HttpEntity multipartEntity,
			String mime, Context ctx, Map<String, String> headers,
			AsyncHttpResponseHandler responseHandler) {

		if (UtilValidate.isNotEmpty(headers)) {
			for (Entry<String, String> header : headers.entrySet()) {
				client.addHeader(header.getKey(), header.getValue());
			}
		}

		/*
		 * client.setBasicAuth("worldcup", "coconut", new AuthScope(
		 * AuthScope.ANY_HOST, 19680, AuthScope.ANY_REALM));
		 */
		Log.i(Tag, "Request url : " + url);
		client.post(ctx, url, multipartEntity, mime, responseHandler);

	}

	public static void post(String url, RequestParams params, Context ctx,
			AsyncHttpResponseHandler responseHandler) {

		/*
		 * UsernamePasswordCredentials credentials = new
		 * UsernamePasswordCredentials("worldcup","coconut"); Header header =
		 * BasicScheme.authenticate(credentials, "UTF-8", false); Header[]
		 * headers = {header};
		 */
		/*
		 * client.setBasicAuth("", "", new AuthScope( AuthScope.ANY_HOST,
		 * AuthScope.ANY_PORT, AuthScope.ANY_REALM));
		 */
		// url = "http://tvm.x-minds.info:19680/api/user/profileimageupdate";

		client.post(ctx, url, params, responseHandler);

		// client.post(ctx,getAbsoluteUrl(url),headers,
		// params,DEFAULT_CONTENT_TYPE, responseHandler);
	}

	public static void put(String url, RequestParams params, Context ctx,
			AsyncHttpResponseHandler responseHandler) {

		/*
		 * client.setBasicAuth("worldcup", "coconut", new AuthScope(
		 * AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM));
		 */
		client.put(ctx, url, params, responseHandler);

	}

	public static void delete(Context ctx, String url, Header[] headers,
			AsyncHttpResponseHandler responseHandler) {
		/*
		 * client.setBasicAuth("worldcup", "coconut", new AuthScope(
		 * AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM));
		 */

		client.delete(ctx, url, headers, responseHandler);

	}

}
