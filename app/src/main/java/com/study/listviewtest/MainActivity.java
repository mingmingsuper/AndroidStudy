package com.study.listviewtest;

import android.content.Context;
import android.content.Intent;
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