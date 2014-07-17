package com.example.util;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import com.example.detectiveface.HttpClientFactory;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class GetAccessToken extends AsyncTask<List<NameValuePair>, Integer, String> {
	
	private TextView textView;
	
	public GetAccessToken(){
		
	}
	public GetAccessToken(TextView textView){
		this.textView = textView;
	}
	@Override
	protected String doInBackground(List<NameValuePair>... arg0) {
		Log.i("detect", "子线程开始");
		HttpClient client = HttpClientFactory.getNewHttpClient();
		HttpClientParams.setCookiePolicy(client.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);   
		HttpParams params = null;
		HttpPost request = null;
		HttpResponse response = null;
		StringBuffer sb = null;
		try {
			request = new HttpPost("https://openapi.baidu.com/oauth/2.0/token");
			
			request.setEntity(new UrlEncodedFormEntity(arg0[0], "UTF-8"));
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			BufferedInputStream in = new BufferedInputStream(entity.getContent());
			sb = new StringBuffer();
			byte[] b = new byte[1024];
			int len = 0;
			while( (len = in.read(b)) > 0){
				sb.append(new String(b,0,len));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	@Override
	protected void onPostExecute(String result) {
		Log.i("detect", result);
		textView.setText(result);
		Looper mLooper = Looper.getMainLooper();
		Handler handler = new myHandler(mLooper);
		Message m = handler.obtainMessage(1, 1, 1, result);
		handler.sendMessage(m);
		Log.i("detect",""+mLooper);
	}
}
