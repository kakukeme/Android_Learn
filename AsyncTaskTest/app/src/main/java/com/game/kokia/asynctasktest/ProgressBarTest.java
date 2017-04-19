package com.game.kokia.asynctasktest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

/**
 * Created by kokia on 2017/4/19.
 */

public class ProgressBarTest extends Activity {

    private ProgressBar mProgressBar;
    private ProgressAsyncTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);

        mProgressBar = (ProgressBar) findViewById(R.id.pg);

        mTask = new ProgressAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){

            // cancel方法只是将对应的AsyncTask，标记为cancel状态，并不是真正取消线程执行；
            // java中没办法粗暴的停止一个线程，必须等处理完之后；
            mTask.cancel(true);
        }
    }

    class ProgressAsyncTask extends AsyncTask<Void, Integer, Void> {

        // 模拟进度更新
        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 100; i++) {

                if (isCancelled()){
                    break;
                }

                publishProgress(i);

                try {
                    Thread.sleep(300); // 300ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (isCancelled()) {
                return;
            }

            // 获取进度更新值
            mProgressBar.setProgress(values[0]);    // 当前进度值
        }
    }
}