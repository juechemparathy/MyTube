package com.sjsu.mytube.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by jue on 10/11/15.
 */
public class ImageLoader extends AsyncTask {
    ImageView downloadedImage;

    public ImageLoader(ImageView image) {
        this.downloadedImage = image;
    }

    protected void onPostExecute(Bitmap result) {
        downloadedImage.setImageBitmap(result);
    }

    @Override
    protected Object doInBackground(Object[] urls) {
        String url = (String) urls[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return icon;
    }
}
