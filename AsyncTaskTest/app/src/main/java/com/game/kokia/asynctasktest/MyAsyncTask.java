package com.game.kokia.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by kokia on 2017/4/19.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("xys", "doInBackground");


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("xys", "onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.d("xys", "onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        Log.d("xys", "onProgressUpdate");
    }
}
