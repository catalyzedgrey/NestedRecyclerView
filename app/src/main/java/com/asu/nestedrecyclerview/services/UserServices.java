package com.asu.nestedrecyclerview.services;

import android.os.AsyncTask;
import android.util.Log;

import com.asu.nestedrecyclerview.activities.main.IAsyncTaskCallback;
import com.asu.nestedrecyclerview.models.Course;
import com.asu.nestedrecyclerview.models.Result;
import com.asu.nestedrecyclerview.models.Track;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserServices {


    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    public class LoadTrackList extends AsyncTask<String, Void, List<Track>> {

        IAsyncTaskCallback asyncTaskCallback;
        public LoadTrackList(IAsyncTaskCallback asyncTaskCallback){
            this.asyncTaskCallback = asyncTaskCallback;
        }

        @Override
        protected List<Track> doInBackground(String... strings) {
            java.net.URL url;
            HttpURLConnection urlConnection = null;
            List<Track> trackList = new ArrayList<>();
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //HTTP header
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("JsonStub-User-Key", "a5a3a085-c9bd-462a-83c5-77620f963745");
                urlConnection.setRequestProperty("JsonStub-Project-Key", "296b28d6-dd31-45c9-810d-cef77decfe75");

                int responseCode = urlConnection.getResponseCode();
                String responseMessage = urlConnection.getResponseMessage();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String responseString = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient-Response", responseString);

                    Gson gson = new Gson();
                    Type type = new TypeToken<Result>() {
                    }.getType();
                    Result result = (Result) gson.fromJson(responseString, type);
                    trackList = result.getTrackList();
//                    trackList = (List<Track>)



                } else {
                    Log.v("CatalogClient", "Response code:" + responseCode);
                    Log.v("CatalogClient", "Response message:" + responseMessage);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }


            return trackList;
        }



        @Override
        protected void onPostExecute(List<Track> trackList) {
            super.onPostExecute(trackList);

            asyncTaskCallback.onAsyncTaskComplete(trackList);
        }
    }

    public class LoadCourseList extends AsyncTask<String, String, List<Course>>{
        IAsyncTaskCallback asyncTaskCallback;
        public LoadCourseList(IAsyncTaskCallback asyncTaskCallback){
            this.asyncTaskCallback = asyncTaskCallback;
        }

        @Override
        protected List<Course> doInBackground(String... strings) {
            java.net.URL url;
            HttpURLConnection urlConnection = null;
            List<Course> courseList = new ArrayList<>();
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //HTTP header
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("JsonStub-User-Key", "a5a3a085-c9bd-462a-83c5-77620f963745");
                urlConnection.setRequestProperty("JsonStub-Project-Key", "296b28d6-dd31-45c9-810d-cef77decfe75");

                int responseCode = urlConnection.getResponseCode();
                String responseMessage = urlConnection.getResponseMessage();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String responseString = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient-Response", responseString);

                    Gson gson = new Gson();
                    Type type = new TypeToken<Result>() {
                    }.getType();
                    Result result = (Result) gson.fromJson(responseString, type);
                    courseList = result.getCourseList();
//                    trackList = (List<Track>)



                } else {
                    Log.v("CatalogClient", "Response code:" + responseCode);
                    Log.v("CatalogClient", "Response message:" + responseMessage);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }


            return courseList;
        }

        @Override
        protected void onPostExecute(List<Course> courseList) {
            super.onPostExecute(courseList);
            asyncTaskCallback.onAsyncTaskComplete(courseList);
        }
    }
}
