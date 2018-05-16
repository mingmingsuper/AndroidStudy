package com.hh.mysdk.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public class SDCardUtil {

    public static File getCacheDicrectory(Context context, boolean preferExternal) {
        File appCacheDir = null;

        String externalStorageState = "";

        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {

        } catch (IncompatibleClassChangeError e) {

        }

        if (preferExternal && Environment.MEDIA_MOUNTED == externalStorageState && hasExternalStoragePermission(context)){
            appCacheDir = getExternalCacheDir(context);
        }

        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }

        if (appCacheDir == null) {
            String cacheDirPath = "data/data/" + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
        }

        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"),"data");
        File appCacheDir = new File(new File(dataDir,context.getPackageName()),"cache");
        if (appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }

            try {
                (new File(appCacheDir,".nomedia")).createNewFile();
            }catch (IOException e) {

            }
        }
        return appCacheDir;
    }

    public static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
