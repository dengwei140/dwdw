package com.example.gobanggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gobanggame.bean.ChessTable;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);


    }

    private void setupHall() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://fivechess.tzchenyu.com/chess_table";
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {


            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String content=response.body().string();
                Gson gson=new Gson();
                ChessTable[] chessTables=gson.fromJson(content,ChessTable[].class);
                LinearLayout linearLayout=findViewById(R.id.hall);
                LinearLayout  oneLine=null;
                for (int i=0;i<chessTables.length;i++){
                        if (i%2==0){
                            oneLine=new LinearLayout(getApplicationContext());
                            oneLine.setOrientation(LinearLayoutCompat.HORIZONTAL);
                            linearLayout.addView(oneLine);
}
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(getApplicationContext(), "Error in get chess table", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
