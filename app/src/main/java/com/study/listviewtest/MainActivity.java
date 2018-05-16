package com.study.listviewtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hh.mysdk.TestActivity;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.mixpush.MixPushConfig;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Almond", "Apple", "Apricot", "Arbutus", "Avocado", "Bagasse",
            "Banana", "Bennet", "Bergamot", "Berry", "Betelnut", "Bilberry", "Bitter orange", "Blackberry",
            "Berry", "Betelnut", "Bilberry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestActivity.this,android.R.layout.simple_list_item_1,data);
        CusAdapter adapter = new CusAdapter(this,R.layout.row_item,data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("you click",String.format("%s",data[i]));
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        MainActivity.init(this);
        RongIMClient.init(getApplicationContext());
    }


    public static void init(Context context)
    {

        NIMClient.init(context, null, options(context));
    }


    // 如果返回值为 null，则全部使用默认参数。
    private static SDKOptions options(Context context)
    {
        SDKOptions options = new SDKOptions();
        options.appKey = "e0fc60d858fb19925dcef8bc82ce66af";

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
//        config.notificationEntrance = WelcomeActivity.class; // 点击通知栏跳转到该Activity
//        config.notificationSmallIconId = R.drawable.hp_push_icon;
        config.titleOnlyShowAppName = true;
//        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        config.showBadge = true;
//
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
//        options.sdkStorageRootPath = HHCacheFile.getCacheDir(context,"nim");;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        options.databaseEncryptKey = "hh_patient";

        // 在线多端同步未读数
        options.sessionReadAck = true;

        options.checkManifestConfig = true;

        options.mixPushConfig = mixPushConfig();


        return options;
    }




    private static MixPushConfig mixPushConfig()
    {
        MixPushConfig config = new MixPushConfig();
        config.xmAppId = "2882303761517753355";
        config.xmAppKey = "5101775386355";
        config.xmCertificateName = "PushXiaomiForXiangjian";
        config.hwCertificateName = "PushHWForXiangjian";

        return config;
    }

}
 class CusAdapter extends BaseAdapter {

    private String [] mList;
    private Context mContext;
    private int mResourceId;

    public  CusAdapter(Context context, int resourceId, String [] data) {
        mContext = context;
        mResourceId = resourceId;
        mList = data;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        View view;
        ViewHolder holder;
        if (contentView == null) {
            view = LayoutInflater.from(mContext).inflate(mResourceId,viewGroup,false);
            holder = new ViewHolder();
            holder.mTextView = view.findViewById(R.id.lbl_title);
            view.setTag(holder);
        } else {
            view = contentView;
            holder = (ViewHolder) view.getTag();
        }
        holder.mTextView.setText((String)getItem(i));
        return view;
    }

    @Override
    public Object getItem(int i) {
        return mList[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    public class ViewHolder {
        public TextView mTextView;
    }
}