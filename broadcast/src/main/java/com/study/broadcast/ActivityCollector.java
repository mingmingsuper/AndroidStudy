package com.study.broadcast;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumingming on 2017/11/8.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        activities.stream().forEach(activity -> {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        });
    }
}
