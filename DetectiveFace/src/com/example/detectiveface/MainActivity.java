package com.example.detectiveface;


import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.util.GetAccessToken;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	TextView textview = null;
	Button begin = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		textview = (TextView) this.findViewById(R.id.TextLable);
		begin = (Button) this.findViewById(R.id.getAccess);
		begin.setOnClickListener(new BeginHandler());
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
}

