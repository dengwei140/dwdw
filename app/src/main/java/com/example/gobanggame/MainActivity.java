package com.example.gobanggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private  final static String TAG=MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dologin(View view) {
        String mobile= ((EditText) findViewById(R.id.mobile)).getText().toString();
        String username= ((EditText) findViewById(R.id.username)).getText().toString();

        if (mobile==null||mobile.trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"手机号码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (username==null||username.trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (mobile.trim().length()!=11||!mobile.startsWith("1"))
        {
            Toast.makeText(getApplicationContext(),"手机号码不规范",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient okHttpClient=new OkHttpClient();
        String url= MessageFormat.format("http://fivechess.tzchenyu.com/authenticate?username={0}&mobile{1}",username,mobile);
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull  Call call,@NotNull   IOException e) {
                Toast.makeText(getApplicationContext(),"Failed on Login",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull  Call call,@NotNull  Response response) throws IOException {
                String content=response.body().string();
                Log.i(TAG,content);
                Gson gson=new Gson();
                Map<String,Object> result=gson.fromJson(content, Map.class);
                String status=(String) result.get("status");
                if ("success".equals(status)){
                    Map<String,Object>user=(Map<String, Object>)result.get("user");
                    int id=(int)(double)user.get("id");
                    String mobile=(String)user.get("mobile");
                    String username=(String)user.get("username");
                    SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("id",id);
                    editor.putString("mobile",mobile);
                    editor.putString("username",username);
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(),HallActivity.class));

                }else {
                    String message= (String) result.get("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}