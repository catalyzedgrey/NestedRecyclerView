package com.asu.nestedrecyclerview.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static final String trackURL = "http://jsonstub.com/load-tracks";
    public static final String coursesURL = "http://jsonstub.com/load-courses";

    public static void MakeToast(String msg, Context context){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
