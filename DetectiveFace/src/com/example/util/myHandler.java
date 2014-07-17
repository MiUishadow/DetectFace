package com.example.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class myHandler extends Handler {

	public myHandler(Looper mLooper) {
		super(mLooper);
	}

	@Override
	public void handleMessage(Message msg) {
		Log.i("detect", "来自Handler的消息"+msg.obj.toString());
	}
}
