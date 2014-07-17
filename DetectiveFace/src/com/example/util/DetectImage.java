package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import com.example.detectiveface.HttpClientFactory;

import android.os.AsyncTask;
import android.util.Log;

public class DetectImage extends AsyncTask<List<NameValuePair>, Integer, String> {

	@Override
	protected String doInBackground(List<NameValuePair>... arg0) {
		HttpClient client = HttpClientFactory.getNewHttpClient();
		HttpPost post = null;
		HttpResponse response = null;
		StringBuffer sb = new StringBuffer();
		try {
			post = new HttpPost("https://openapi.baidu.com/rest/2.0/media/v1/face/detect");
			post.setEntity(new UrlEncodedFormEntity(arg0[0],"UTF-8"));
			response = client.execute(post);
			InputStream in = response.getEntity().getContent();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = in.read(buffer))>0){
				sb.append(new String(buffer,0,len));
			}
			return sb.toString();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		Log.i("detect", "ºÏ≤‚ÕÍ≥…");
		Log.i("detect",result);
	}
}
