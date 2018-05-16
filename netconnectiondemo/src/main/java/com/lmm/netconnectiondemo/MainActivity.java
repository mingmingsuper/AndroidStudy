package com.lmm.netconnectiondemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hh.mysdk.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = findViewById(R.id.list_view);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,
                LinearLayoutManager.VERTICAL, 10,
                getResources().getColor(R.color.divide_gray_color, null)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HomeAdapter());
    }

    private void initData() {
        mDatas.add("URL Demo");
        mDatas.add("分割线测试");
    }

    public void checkNetConnectInfo(){
        Context context = this.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networkInfo = connectivityManager.getAllNetworks();

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

        @NonNull
        @Override
        public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.home_item_layout, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public HomeViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.home_item_title);
            }
        }
    }
}
