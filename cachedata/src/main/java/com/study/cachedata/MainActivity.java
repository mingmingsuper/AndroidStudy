package com.study.cachedata;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.study.cachedata.database.DatabaseHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mContentEdit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentEdit = (EditText)findViewById(R.id.content);
        String content = load();
        if (!TextUtils.isEmpty(content)) {
            mContentEdit.setText(content);
        }
        registerListener(R.id.create_database);
        registerListener(R.id.insert_data);
        registerListener(R.id.update_data);
        registerListener(R.id.delete_data);
        registerListener(R.id.query_data);
        dbHelper = new DatabaseHelper(this,"BookStore.db",null,2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.save(mContentEdit.getText().toString());
        this.saveSharedPreference(mContentEdit.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                dbHelper.getWritableDatabase();
                break;
            case R.id.insert_data:
                insertData();
                break;
            case R.id.update_data:
                updateData();
                break;
            case R.id.delete_data:
                deleteData();
                break;
            case R.id.query_data:
                queryData();
                break;
        }
    }

    public void registerListener(int resourceId) {
        findViewById(resourceId).setOnClickListener(this);
    }

    public void save(String content) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load() {
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  content.toString();
    }

    public void saveSharedPreference(String content) {
        SharedPreferences.Editor editor = getSharedPreferences("cache",MODE_PRIVATE).edit();
        editor.putString("name","Tony");
        editor.putInt("age",28);
        editor.putBoolean("married",false);
        editor.putString("msg",content);
        editor.apply();
    }

    public void readSharedPreference() {
        SharedPreferences preferences = getSharedPreferences("cache",MODE_PRIVATE);
        String name = preferences.getString("name","");
        int age = preferences.getInt("age",0);
        boolean married = preferences.getBoolean("marreid",false);
        String msg = preferences.getString("msg","");
    }

    //插入数据
    public void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","大话西游");
        values.put("author","刘先生");
        values.put("pages",120);
        values.put("price",35.6);
        db.insert("Book",null,values);
        values.clear();

        //插入第二条数据
        values.put("name","漂流记");
        values.put("author","刘先生");
        values.put("pages",560);
        values.put("price",15.6);
        db.insert("Book",null,values);
    }

    public void updateData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",75.9);
        db.update("Book",values,"name = ?",new String[]{ "大话西游" });
    }

    public void deleteData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Book","id = ?",new String[]{"1"});
    }

    public void queryData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d("book name is", "book name is " + name);
                Log.d("book pages is", "book pages is " + pages);
                Log.d("book author is", "book author is " + author);
                Log.d("book price is", "book price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
