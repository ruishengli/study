package com.inlook.or.study.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.BitmapFactory;

public class BitmapRequest {

    public void downLoadBitmap(String url) {
       HttpURLConnection connection = null;
       
      try {
          final URL httpUrl = new URL(url);
        connection = (HttpURLConnection) httpUrl.openConnection();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
