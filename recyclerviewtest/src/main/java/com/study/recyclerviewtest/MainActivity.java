package com.study.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Almond", "Apple", "Apricot", "Arbutus", "Avocado", "Bagasse",
            "Banana", "Bennet", "Bergamot", "Berry", "Betelnut", "Bilberry", "Bitter orange", "Blackberry",
            "Berry", "Betelnut", "Bilberry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrAdapter adapter = new StrAdapter(data);
        RecyclerView listView = (RecyclerView)findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this)); //在使用的时候一定要设置LayoutManager来控制布局方式
        listView.setAdapter(adapter);
    }
}

class StrAdapter extends RecyclerView.Adapter<StrAdapter.ViewHolder> {

    private String[] mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.lbl_title);
        }
    }

    public StrAdapter(String[] data) {
        mList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mList[position]);
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }
}
