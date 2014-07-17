package com.example.detectiveface;


import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.util.DetectImage;
import com.example.util.GetAccessToken;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	TextView textview = null;
	Button begin = null;
	Button detect = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		textview = (TextView) this.findViewById(R.id.TextLable);
		begin = (Button) this.findViewById(R.id.getAccess);
		begin.setOnClickListener(new BeginHandler());
		
		detect = (Button) this.findViewById(R.id.Detect);
		detect.setOnClickListener(new DetectImg());
	}
	
	private final class BeginHandler implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			GetAccessToken subTask = new GetAccessToken(textview);
			List<NameValuePair> pargams = new LinkedList<NameValuePair>();
			pargams.add(new BasicNameValuePair("grant_type", "client_credentials"));
			pargams.add(new BasicNameValuePair("client_id","uUGPHoGpZyUmZaR2hYxb7Yy8"));
			pargams.add(new BasicNameValuePair("client_secret","GWYmjuVg37zvM2akFrhe2hZAjtm8qCpB"));
			subTask.execute(pargams);
		}
		
	}
	private final class DetectImg implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String access_token = (String) textview.getText();
			JsonReader reader = new JsonReader(new StringReader(access_token));
			String name = null;
			try {
				reader.beginObject();
				while(reader.hasNext()){
					name = reader.nextName();
					if("access_token".equals(name)){
						access_token = reader.nextString();
					}else {
						reader.skipValue();
					}
				}
				reader.endObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DetectImage dm = new DetectImage();
			List<NameValuePair> pargams = new LinkedList<NameValuePair>();
			pargams.add(new BasicNameValuePair("access_token", access_token));
			pargams.add(new BasicNameValuePair("url","http://img.pconline.com.cn/images/upload/upc/tx/auto5/1304/20/c34/20070004_20070004_1366472084984_800x1200.jpg"));
			dm.execute(pargams);
		}
		
	}
}

