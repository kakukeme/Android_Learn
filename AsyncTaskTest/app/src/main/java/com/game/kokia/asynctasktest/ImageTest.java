package com.game.kokia.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kokia on 2017/4/19.
 */

public class ImageTest extends Activity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private static String URL = "http://cc.cocimg.com/api/uploads/20170419/1492571527107060.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image);

        mImageView = (ImageView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        // 设置传递进去的参数
        new ImageAsyncTask().execute(URL);
    }

    class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        // 1、开始初始化工作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // 显示进度条
            mProgressBar.setVisibility(View.VISIBLE);

        }

        // 3、
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            // 设置UI
            mProgressBar.setVisibility(View.GONE);  // 隐藏进度条
            mImageView.setImageBitmap(bitmap);      // 设置image
        }

        // 2、
        @Override
        protected Bitmap doInBackground(String... params) {

            // 1、获取传递进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;

            try {
                connection = new URL(url).openConnection(); // 获取网络连接对象

                is = connection.getInputStream();   // 获取输入流

                BufferedInputStream bis = new BufferedInputStream(is); // 输入流包装下

                Thread.sleep(3000);

                // 2、通过decodeStream解析输入流
                bitmap = BitmapFactory.decodeStream(is);    // 将输入流解析成bitmap

                is.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 3、将Bitmap作为返回值
            return bitmap;
        }
    }
}
